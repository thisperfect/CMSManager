package com.ofhi.common.util;

import lombok.Data;


/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\9\19 0019
 * Time: 11:07
 */

@Data
public class SearchHelper<T> {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private T t;
}
