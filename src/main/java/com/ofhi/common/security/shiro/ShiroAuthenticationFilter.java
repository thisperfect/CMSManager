package com.ofhi.common.security.shiro;

import com.ofhi.common.response.InterfaceResult;
import com.ofhi.common.response.ResponseCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ShiroAuthenticationFilter extends FormAuthenticationFilter {

	private Logger logger = LoggerFactory.getLogger(ShiroAuthenticationFilter.class);
	
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	logger.debug("===> shiro login validation begin");
        if (isLoginRequest(request, response)) {
        	logger.debug("===> login in");
            return true;
        } else {
        	logger.debug("===> on login");
            saveRequest(request);
            HttpServletRequest req = (HttpServletRequest)request;
            if ("XMLHttpRequest".equalsIgnoreCase(req.getHeader("x-requested-with"))) {
            	logger.debug("===> ajax request");
            	response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                InterfaceResult result = InterfaceResult.getErrorResult(ResponseCode.unauthenticated);
                PrintWriter writer = response.getWriter();
                writer.append(result.toString());
                writer.flush();
                writer.close();
            } else {
            	 String returnUrl = WebUtils.getRequestUri(req);
            	logger.debug("===> web request,url:{}" , returnUrl);
                ((HttpServletResponse) response).sendRedirect("/system/login.shtml?returnUrl=" + returnUrl);
            }
            return false;
        }
    }

}
