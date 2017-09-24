package com.ofhi.common.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofhi.common.cache.shiro.ShiroSessionService;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.response.InterfaceResult;
import com.ofhi.common.util.StringHelper;


public  class BaseController {

    protected  final String produces = "application/json;charset=UTF-8";
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final Long session_time_out = 18000L;

	protected final String validation_code = "validationCode";

    @Autowired
    private ShiroSessionService shiroSessionService;

    protected void setSession(Object objKey, Object objVal) {
        shiroSessionService.setAttribute(objKey, objVal);
    }

    protected void setSession(Object objKey, Object objVal, Long timeOut) {
        shiroSessionService.setAttribute(objKey, objVal);
        shiroSessionService.setTimeout(timeOut);
    }

    protected Object getSessionValue(Object objKey) {
       return shiroSessionService.getAttribute(objKey);
    }

    protected void removeSession(Object objKey) {
        shiroSessionService.removeAttribute(objKey);
    }

    protected Object getCurrentSessionId() {
        return shiroSessionService.getId();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
		logger.error("exceptionHandler : \n {}", StringHelper.exceptionDetail(exception));
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            InterfaceResult  result = InterfaceResult.getErrorResult();
           if (exception instanceof RequestErrorException) {
        	   RequestErrorException rex = (RequestErrorException)exception;
            	result = result.getErrorResult(rex.getErrCode(),rex.getErrMsg());
            }
            String resultStr = result.toString();
            logger.info("ajax request,result:{}",resultStr);
           return resultStr;
        } else {
            String url = "/error/internalError.shtml";
            if (exception instanceof UnauthorizedException) {
                url = "/error/unAuthorization.shtml";
            }
            logger.info("web request,jump url:{}",url);
          return new ModelAndView(String.format("redirect:", url));
        }
    }
}
