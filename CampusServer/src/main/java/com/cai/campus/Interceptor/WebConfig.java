package com.cai.campus.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String TOKEN = "Access-User-Token";
    public static final String TOKEN_VALUE = "EwPhXpFJ6hj*!VmJREkPL8U%Bqs3WrlGAIUKh!7n!#RYXzm%T5r2HMhgo2BhtFtt";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Api权限验证拦截器
//        registry.addInterceptor(new AuthInspectInterceptor()).addPathPatterns("/**");
    }

}