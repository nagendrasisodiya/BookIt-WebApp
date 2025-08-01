package org.spring.bookitrestapi.security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring.bookitrestapi.security.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
     try {
         String jwt=jwtUtils.getJwtFromHeader(request);
         if(jwt!=null&&jwtUtils.validateToken(jwt)){
             String userName=jwtUtils.getUserNameFromToken(jwt);
             UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                     new UsernamePasswordAuthenticationToken(userDetails,
                             null, userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
         }
     }catch (Exception e){
         logger.error("cant set userAuthentication"+e.getMessage());
     }
     filterChain.doFilter(request, response);
    }
}
