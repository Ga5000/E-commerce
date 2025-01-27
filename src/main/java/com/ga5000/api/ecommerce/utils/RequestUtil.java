package com.ga5000.api.ecommerce.utils;

import org.springframework.beans.BeanUtils;

public class RequestUtil {
    private RequestUtil(){}

    public static <T> void mapRequest(T source, T target) {
        BeanUtils.copyProperties(source, target);
    }
}
