package com.messapp.messapp.exception;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        if (exception instanceof UsernameNotFoundException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "USER NOT FOUND");
        } else if (exception instanceof BadCredentialsException) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "BAD CREDENTIALS");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "AUTHENTICATION FAILED");
        }
    }
}
