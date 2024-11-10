package org.seoultech.tableapi.auth.service;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.auth.jwt.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // 헤더에서 Bearer 형식의 RefreshToken 가져오기
        String refreshToken = extractToken(request.getHeader("RefreshToken"));
        if (refreshToken == null) {
            return new ResponseEntity<>("Refresh token is null or not in the correct format", HttpStatus.BAD_REQUEST);
        }

        // RefreshToken이 만료되었는지 확인
        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("Refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // RefreshToken의 유효성 검사 및 사용자 정보 확인
        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        // 새로운 AccessToken 및 RefreshToken 생성
        String newAccessToken = jwtUtil.createJwt("AccessToken", username, role, 600000L); // 10분
        String newRefreshToken = jwtUtil.createJwt("RefreshToken", username, role, 86400000L); // 1일

        // 새로운 토큰을 헤더에 Bearer 형식으로 추가
        response.setHeader("Authorization", "Bearer " + newAccessToken);
        response.setHeader("RefreshToken", "Bearer " + newRefreshToken);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Bearer 토큰 형식에서 토큰만 추출하는 메서드
    private String extractToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
