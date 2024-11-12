package org.seoultech.tableapi.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.auth.dto.LoginRequest;
import org.seoultech.tableapi.auth.service.AuthService;
import org.seoultech.tableapi.auth.service.RefreshTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        try {
            return refreshTokenService.reissue(request, response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reissue failed: " + e.getMessage());
        }
    }
}
