package com.notas.resfull.configuration;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class JwtUtil {

    static  void addAuthentication(HttpServletResponse res , String username) {
        String token = Jwts.builder().setSubject(username)

            //hash con el que firmaremos la clave
            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, "P@tito")
            .compact();

        res.addHeader("Authorization" , "Bearer" + token);
    }

    //Metodo para validar el token enviado por el cliente
    static Authentication getAutentication (HttpServletRequest request) {

        //obrenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        //si hay un token presente , entoces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("P@tito")
                    .parseClaimsJws(token.replace("Bearer" , ""))
                    .getBody()
                    .getSubject();

            // Recordamos que para lkas demas peticiones que no sean /login
            // no requerimos usar authentication por username/password
            // por este motivo podemos devolver un usernamePasswordAuthenticationToken sin password

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user , null , Collections.emptyList()) :
                    null;
        }
        return null;
    }
}
