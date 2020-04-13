package com.nkpdqz.redisidempotentsdemo.config;

import com.nkpdqz.redisidempotentsdemo.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new AutoIdempotentInterceptor());
        interceptorRegistration.addPathPatterns("/**");
    }
}
