package com.configsView;

import com.configurations.Auth;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter
{
    private static final String DEFAULT_TEMPLATE = "template";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }

        request.setAttribute("loginUser", (new Auth().getLoginUser()));
        request.setAttribute("role", (new Auth().getRole()));
        request.setAttribute("body", modelAndView.getViewName());
        modelAndView.setViewName(DEFAULT_TEMPLATE);
    }
}
