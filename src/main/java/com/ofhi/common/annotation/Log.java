package com.ofhi.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String module()  default "";  //模块名称
    String methods()  default "";
    String description()  default "";  //
}
