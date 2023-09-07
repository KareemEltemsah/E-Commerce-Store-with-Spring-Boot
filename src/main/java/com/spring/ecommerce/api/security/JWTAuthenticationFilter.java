package com.spring.ecommerce.api.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.model.dao.UserRepository;
import com.spring.ecommerce.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // extract authorization header
        String authHeader = request.getHeader("Authorization");
        // validate authorization header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // extract jwt
            String token = authHeader.substring(7);
            try {
                String username = jwtService.getUserName(token);
                // check if user exist
                Optional<LocalUser> opUser = userRepository.findByUsername(username);
                if (opUser.isPresent()) {
                    LocalUser user = opUser.get();
                    // create auth token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            new ArrayList<>()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    // update security context with auth token
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (JWTDecodeException e) {
                // invalid jwt case
            }
        }
        filterChain.doFilter(request, response);
    }
}
