package com.salma.Ecommerce.Secuirty;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Repository.UserRepository;
import com.salma.Ecommerce.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader=request.getHeader("Authorization");
        if(tokenHeader!=null || !tokenHeader.startsWith("Bearer ")){
            String token=tokenHeader.substring(7);
            try{
                String username= jwtService.getUserNameFromJWT(token);
                Optional<User> localUser=userRepository.findByUsernameIgnoreCase(username);
                if(localUser.isPresent()){
                  User user=localUser.get();
                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(user,null,new ArrayList());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JWTDecodeException e) {
            }

        }
        filterChain.doFilter(request,response);
    }
}
