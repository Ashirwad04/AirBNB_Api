package AirBNB_Api.config;


import AirBNB_Api.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTResponseFilter extends OncePerRequestFilter {


    private JWTService jwtService;

    public JWTResponseFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null &&tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(8,tokenHeader.length()-1);

            String username= jwtService.getUserName(token);
            System.out.println(username);
        }
        filterChain.doFilter(request, response);




    }
}
