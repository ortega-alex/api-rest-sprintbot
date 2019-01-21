package com.notas.resfull.configuration;

import com.notas.resfull.services.UsuarioServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioServis userdetailservice;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userdetailservice);
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login" , "/notas/all").permitAll() //permiimos el acceso a /login a cualquir usuairo
                .anyRequest().authenticated() //cualquir otra peticion requiere authentication
                .and() //las peticiones /login paran preciamente por este filtro
                .addFilterBefore(new LoginFilter("/login" , authenticationManager()) ,
                        UsernamePasswordAuthenticationFilter.class)
                //las demas peticiones pasaran por este filtro para validad el token
        .addFilterBefore(new JwtFilter() , UsernamePasswordAuthenticationFilter.class);
    }

}
