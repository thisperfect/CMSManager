package com.ofhi.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String internalError(Exception e, Model model) {
        logger.debug(e.getMessage());
        e.printStackTrace();
        model.addAttribute("error",e);
        return "error/500";
    }

    @RequestMapping("/unAuthorization")
    public String unAuthorization() {
        return "error/403";
    }

}
