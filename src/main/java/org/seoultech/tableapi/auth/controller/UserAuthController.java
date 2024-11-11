package org.seoultech.tableapi.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.auth.document.*;
import org.seoultech.tableapi.auth.dto.LoginRequest;
import org.seoultech.tableapi.auth.dto.RegisterRequest;
import org.seoultech.tableapi.auth.service.AuthService;
import org.seoultech.tableapi.global.annotation.ApiErrorExceptionsExample;
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
    @ApiErrorExceptionsExample(RegisterExceptionDocs.class)
    public String join(@RequestBody RegisterRequest registerRequest) {
        return "success";
    }

    @GetMapping("/duplication-check")
    @Operation(summary = "이메일 중복 확인")
    @ApiErrorExceptionsExample(EmailDuplicationExceptionDocs.class)
    public String checkEmailDuplication(
            @Parameter(description = "이메일", example = "example@seoultech.ac.kr")
            @RequestParam String useremail) {

        return "success";
    }

    @PostMapping("/certify")
    @Operation(summary = "이메일 인증 요청")
    @ApiErrorExceptionsExample(CertifyEmailExceptionDocs.class)
    public String certify(
            @Parameter(description = "이메일", example = "examlpe@seoultech.ac.kr")
            @RequestParam String useremail) {
        return "success";
    }

    @PostMapping("/verify")
    @Operation(summary = "이메일 인증 코드 확인")
    @ApiErrorExceptionsExample(VerifyEmailExceptionDocs.class)
    public String verify(
            @Parameter(description = "이메일", example = "example@seoultech.ac.kr")
            @RequestParam String useremail,
            @Parameter(description = "인증 코드", example = "123456")
            @RequestParam int codeNumber) {
        return "success";
    }

    @PostMapping("/login")
    @Operation(summary = "회원 로그인")
    @ApiErrorExceptionsExample(LoginExceptionDocs.class)
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


    @DeleteMapping
    @Operation(summary = "회원 탈퇴")
    public String delete() {
        return "success";
    }
}
