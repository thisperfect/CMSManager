package com.ofhi.common.exception;

/**
 * Created with IntelliJ IDEA
 * Created By laizuan
 * Date: 2018-1-14
 * Time: 11:37
 */
public class QuartzException extends RuntimeException {

    public QuartzException(String error, Exception e) {
        super(error, e);
    }
}
