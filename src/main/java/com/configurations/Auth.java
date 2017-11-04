package com.configurations;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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

    public String getRole() {
        try {
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            for (GrantedAuthority authority : authorities) {
                return authority.getAuthority();
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
