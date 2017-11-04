package com.configurations;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Auth
{
    private Authentication auth;

    private void setAuth()
    {
        auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
    }

    public Auth()
    {
        setAuth();
    }

    public UserDetails getLoginUser()
    {
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            try {
                return (UserDetails) auth.getPrincipal();
            } catch (NullPointerException e) {
                return null;
            }
        }

        return null;
    }

}
