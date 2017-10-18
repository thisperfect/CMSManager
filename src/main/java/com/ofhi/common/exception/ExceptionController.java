package com.ofhi.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\7\26 0026
 * Time: 14:54
 */
@Controller
@RequestMapping("/error")
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping("/internalError")
    public ModelAndView internalError(HttpServletRequest request ) {
        ModelAndView view = new ModelAndView("error/500");
        Throwable t = (Throwable)request.getAttribute("javax.servlet.error.exception");
        String defaultMessage = "未知" ;
        if(null == t){
            view.addObject("line", defaultMessage);
            view.addObject("clazz", defaultMessage);
            view.addObject("methodName",defaultMessage);
            return view;
        }
        String message = t.getMessage() ;//错误信息
        StackTraceElement[] stack = t.getStackTrace();
        view.addObject("message", message);
        if(null != stack && stack.length != 0 ){
            StackTraceElement element = stack[0];
            int line = element.getLineNumber();//错误行号
            String clazz = element.getClassName();//错误java类
            String fileName = element.getFileName();

            String methodName = element.getMethodName() ;//错误方法
            view.addObject("line", line);
            view.addObject("clazz", clazz);
            view.addObject("methodName",methodName);
            logger.error(String.format("line:%s,clazz:%s,fileName:%s,methodName:%s()",
                    line,clazz,fileName,methodName) , t);
        }
        return view;
    }

    @RequestMapping("/unAuthorization")
    public String unAuthorization() {
        return "error/403";
    }

}
