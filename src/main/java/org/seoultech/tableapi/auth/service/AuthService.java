package org.seoultech.tableapi.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.auth.dto.LoginRequest;
import org.seoultech.tableapi.auth.jwt.JwtTokenProvider;
import org.seoultech.tableapi.auth.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUtil jwtUtil;

    public void login(LoginRequest loginRequest, HttpServletResponse response) throws AuthenticationException {
        // 로그인 인증 처리
        Authentication authentication = authenticate(loginRequest);
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        // AccessToken과 RefreshToken 생성
        String accessToken = jwtTokenProvider.createAccessToken(username, role);
        String refreshToken = jwtTokenProvider.createRefreshToken(username, role);

        // AccessToken과 RefreshToken을 헤더에 추가
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("RefreshToken", "Bearer " + refreshToken);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // 헤더에서 RefreshToken 가져오기
        String refreshToken = request.getHeader("RefreshToken");
        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid refresh token format");
        }

        // Bearer 접두사 제거
        refreshToken = refreshToken.substring(7);

        // RefreshToken 만료 확인
        if (jwtUtil.isExpired(refreshToken)) {
            throw new IllegalArgumentException("Expired refresh token");
        }

        // RefreshToken의 유효성 확인
        String category = jwtUtil.getCategory(refreshToken);
        String username = jwtUtil.getUsername(refreshToken);
        if (!"RefreshToken".equals(category)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // 응답에서 RefreshToken 헤더를 제거
        response.setHeader("RefreshToken", "");
    }

    private Authentication authenticate(LoginRequest loginRequest) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUseremail(), loginRequest.getPassword());
        return authenticationManager.authenticate(authToken);
    }
}
