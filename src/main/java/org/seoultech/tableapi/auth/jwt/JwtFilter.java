package org.seoultech.tableapi.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.seoultech.tableapi.auth.dto.CustomUserDetails;
import org.seoultech.tableapi.user.entity.Role;
import org.seoultech.tableapi.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 Authorization에 담긴 토큰을 꺼냄
        String authorizationHeader = request.getHeader("Authorization");
        // 토큰이 없다면 다음 필터로 넘김
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Bearer 접두사를 제거하고 순수 토큰 값만 추출
        String accessToken = authorizationHeader.substring(7);
        try {
            // 토큰 만료 여부 확인 및 만료된 경우 예외 처리
            jwtUtil.isExpired(accessToken);

            // 토큰이 AccessToken인지 확인 (발급시 페이로드에 명시)
            String category = jwtUtil.getCategory(accessToken);
            if (category == null || !category.equals("AccessToken")) {
                sendErrorResponse(response, "Invalid access token", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // username, role 값을 획득
            String username = jwtUtil.getUsername(accessToken);
            String role = jwtUtil.getRole(accessToken);
            if (username == null || role == null) {
                sendErrorResponse(response, "Invalid token claims", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // SecurityContext에 인증 정보 설정
            User member = User.builder()
                    .username(username)
                    .role(Role.valueOf(role))
                    .build();
            CustomUserDetails customUserDetails = new CustomUserDetails(member);
            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, "Access token expired", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (JwtException e) {
            sendErrorResponse(response, "JWT processing failed", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 다음 필터로 넘김
        filterChain.doFilter(request, response);
    }
    private void sendErrorResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        PrintWriter writer = response.getWriter();
        writer.print(message);
        writer.flush();
    }
}