package com.ofhi.common.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.response.InterfaceResult;
import com.ofhi.common.util.StringHelper;


public  class BaseController {

    protected static final String produces = "application/json;charset=UTF-8";
	
	protected   final Logger logger = LoggerFactory.getLogger(getClass());

    protected Object getCurrentSessionId() {
        return  SecurityUtils.getSubject().getSession().getId();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
		logger.error("exceptionHandler : \n {}", StringHelper.exceptionDetail(exception));
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            InterfaceResult  result = InterfaceResult.getErrorResult();
           if (exception instanceof RequestErrorException) {
        	   RequestErrorException rex = (RequestErrorException)exception;
            	result = InterfaceResult.getErrorResult(rex.getErrCode(),rex.getErrMsg());
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
