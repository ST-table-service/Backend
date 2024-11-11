package org.seoultech.tableapi.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.auth.dto.LoginRequest;
import org.seoultech.tableapi.auth.dto.RegisterRequest;
import org.seoultech.tableapi.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "회원 인증/인가/삭제 관련 API")
public class UserAuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "회원 가입")
    public String join(@RequestBody RegisterRequest registerRequest) {
        return "success";
    }

    @GetMapping("/duplication-check")
    @Operation(summary = "이메일 중복 확인")
    public String checkEmailDuplication(
            @Parameter(description = "이메일", example = "exampe@seoultech.ac.kr")
            @RequestParam Long useremail) {

        return "success";
    }

    @PostMapping("/certify")
    @Operation(summary = "이메일 인증 요청")
    public String certify(@RequestParam String email) {
        return "success";
    }

    @PostMapping("/verify")
    @Operation(summary = "이메일 인증 코드 확인")
    public String verify(@RequestParam String email) {
        return "success";
    }

    @PostMapping("/login")
    @Operation(summary = "회원 로그인")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            authService.login(loginRequest, response);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "회원 로그아웃")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            authService.logout(request, response);
            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logout failed: " + e.getMessage());
        }
    }

    @PostMapping("/email-check")
    @Operation(summary = "인증된 이메일인지 확인")
    public String checkEmail(@RequestParam String email) {
        return "success";
    }

    @DeleteMapping
    @Operation(summary = "회원 탈퇴")
    public String delete() {
        return "success";
    }
}
