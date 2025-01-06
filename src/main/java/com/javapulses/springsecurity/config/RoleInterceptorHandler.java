package com.javapulses.springsecurity.config;

import com.javapulses.springsecurity.interceptor.RoleInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
public class RoleInterceptorHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(RoleInterceptor.class)) {
                RoleInterceptor roleInterceptor = method.getAnnotation(RoleInterceptor.class);
                String role = roleInterceptor.role();

                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                if (userDetails != null && userDetails.getAuthorities() != null) {
                    boolean hasRole = userDetails.getAuthorities().stream().anyMatch(
                            a -> {
                                String[] parts = a.getAuthority().split(",");
                                if (parts.length > 1) {
                                    String[] roleParts = parts[1].split("=");
                                    return roleParts.length > 1 && roleParts[1].equals(role);
                                }
                                return false;
                            }
                    );
                    if (hasRole) {
                        return true;
                    } else {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return false;
                    }
                }

                else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }

            }
        }
        return true;
    }
}