package com.example.testtask.auth;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter implements Filter {
    private static final String AUTHORIZATION = "Authorization";
    private final TokenService tokenService;

    public JWTFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = req.getHeader(AUTHORIZATION);

        if (token == null || !tokenService.verifyToken(token)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
