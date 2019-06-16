package com.zhuxf.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TokenGenerateInterceptor tokenGenerateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenGenerateInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login").addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
