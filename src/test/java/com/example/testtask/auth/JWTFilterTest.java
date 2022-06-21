package com.example.testtask.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JWTFilterTest {
    @Mock
    HttpServletRequest request;
    @Mock
    TokenService tokenService;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain filterChain;

    @InjectMocks
    JWTFilter jwtFilter;

    @Test
    public void whenHeaderIsNullThenShouldAuthorize() throws ServletException, IOException {
        //GIVEN
        when(request.getHeader(anyString())).thenReturn(null);
        //WHEN
        jwtFilter.doFilter(request, response, filterChain);
        //THEN
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void whenTokenIsNotValidThenResponseContainError() throws ServletException, IOException {
        //GIVEN
        String token = "token";
        //WHEN
        when(request.getHeader(anyString())).thenReturn(token);
        when(tokenService.verifyToken(token)).thenReturn(false);
        jwtFilter.doFilter(request, response, filterChain);
        //THEN
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void whenTokeIsValidThenShouldCallNextFilter() throws ServletException, IOException {
        //GIVEN
        String token = "token";
        //WHEN
        when(request.getHeader(anyString())).thenReturn(token);
        when(tokenService.verifyToken(token)).thenReturn(true);
        jwtFilter.doFilter(request, response, filterChain);
        //THEN
        verify(filterChain).doFilter(any(),any());
    }
}
