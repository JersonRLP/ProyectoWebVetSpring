package com.citvet.handler;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        for (GrantedAuthority authority : authorities) {
	            switch (authority.getAuthority()) {
	                case "admin":
	                    response.sendRedirect("/cliente-listado");
	                    return;
	                case "cajero(a)":
	                    response.sendRedirect("/citas");
	                    return;    
	            }
	        }
	        response.sendRedirect("/error");
	    }

	
}
