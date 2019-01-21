package com.notas.resfull.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/*
   *las peticiones que no sean /login pasaran por este filtro
   * el cual se encargara de paasar el "request" a nuestra clase de utilidad jwtUtil
   *para que valide el token
 */

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = JwtUtil.getAutentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request , response);
    }
}
