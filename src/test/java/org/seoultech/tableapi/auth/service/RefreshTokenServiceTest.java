package org.seoultech.tableapi.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.seoultech.tableapi.auth.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RefreshTokenServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private String refreshToken;

    @BeforeEach
    public void setUp() {
        // 1. 초기 RefreshToken 생성 (1일 유효 기간)
        refreshToken = jwtUtil.createJwt("RefreshToken", "testUser", "ADMIN", 86400000L); // 1일
    }

    @Test
    @DisplayName("AccessToken 만료 시 RefreshToken을 통한 재발급")
    public void testAccessTokenExpirationAndRefresh() throws Exception {
        // 1. 만료된 AccessToken 생성 (1초 유효 기간)
        String expiredAccessToken = jwtUtil.createJwt("AccessToken", "testUser", "ADMIN", 1000L);
        // 2. AccessToken 만료를 위해 2초 대기
        Thread.sleep(2000);

        // 3. 만료된 AccessToken으로 요청하여 401 Unauthorized 기대
        mockMvc.perform(get("/api/admin")
                        .header("Authorization", "Bearer " + expiredAccessToken))
                .andExpect(status().isUnauthorized());

        // 4. 유효한 RefreshToken을 사용해 새로운 AccessToken 요청
        mockMvc.perform(post("/api/auth/refresh-token")
                        .header("RefreshToken", "Bearer " + refreshToken))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"))
                .andExpect(header().exists("RefreshToken"));
    }

    @Test
    @DisplayName("RefreshToken을 통한 AccessToken 재발급 성공")
    public void testReissueTokenSuccess() throws Exception {
        // 1. 유효한 RefreshToken을 사용하여 새로운 AccessToken 요청
        mockMvc.perform(post("/api/auth/refresh-token")
                        .header("RefreshToken", "Bearer " + refreshToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"))
                .andExpect(header().exists("RefreshToken"));
    }

    @Test
    @DisplayName("잘못된 형식의 RefreshToken으로 재발급 실패")
    public void testInvalidRefreshTokenFormat() throws Exception {
        // 1. 잘못된 형식의 RefreshToken으로 재발급 요청
        mockMvc.perform(post("/api/auth/refresh-token")
                        .header("RefreshToken", "InvalidToken"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Refresh token is null or not in the correct format"));
    }

    @Test
    @DisplayName("만료된 RefreshToken으로 재발급 실패")
    public void testExpiredRefreshToken() throws Exception {
        // 1. 짧은 유효 기간의 RefreshToken 생성 (예: 1초)
        String expiredRefreshToken = jwtUtil.createJwt("RefreshToken", "testUser", "ADMIN", 1000L);

        // 2. RefreshToken 만료를 위해 잠시 대기
        Thread.sleep(2000);

        // 3. 만료된 RefreshToken으로 재발급 요청
        mockMvc.perform(post("/api/auth/refresh-token")
                        .header("RefreshToken", "Bearer " + expiredRefreshToken))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Refresh token expired"));
    }

}
