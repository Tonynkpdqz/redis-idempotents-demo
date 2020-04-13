package com.nkpdqz.redisidempotentsdemo.interceptor;

import com.nkpdqz.redisidempotentsdemo.anno.AutoIdempotent;
import com.nkpdqz.redisidempotentsdemo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod method = (HandlerMethod) handler;
        AutoIdempotent methodAnnotation = method.getMethodAnnotation(AutoIdempotent.class);
        if (methodAnnotation!=null){
            return service.checkToken(request);
        }else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print("wrong!");
            writer.flush();
            writer.close();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
