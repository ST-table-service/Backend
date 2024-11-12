package org.seoultech.tableapi.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.dto.ErrorReason;
import org.seoultech.tableapi.global.exception.BaseErrorCode;

import static org.seoultech.tableapi.global.consts.STtableStatic.*;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    @ExplainError("이메일이 틀린 경우 발생하는 오류입니다.")
    INVALID_USER_EMAIL(BAD_REQUEST, "AUTH_400_1", "잘못된 이메일입니다."),

    @ExplainError("비밀번호가 틀린 경우 발생하는 오류입니다.")
    INVALID_PASSWORD(BAD_REQUEST, "AUTH_400_2", "잘못된 비밀번호입니다."),

    @ExplainError("이미 탈퇴한 회원인 경우 발생하는 오류입니다.")
    MEMBER_ALREADY_WITHDRAWN(BAD_REQUEST, "AUTH_400_3", "이미 탈퇴한 회원입니다."),

    @ExplainError("JWT 토큰이 없는 경우 발생하는 오류입니다.")
    TOKEN_NOT_FOUND(UNAUTHORIZED, "AUTH_401_1", "인증 토큰이 존재하지 않습니다."),

    @ExplainError("유효하지 않은 JWT 토큰인 경우 발생하는 오류입니다.")
    INVALID_TOKEN(UNAUTHORIZED, "AUTH_401_2", "유효하지 않은 인증 토큰입니다."),

    @ExplainError("JWT 토큰이 만료된 경우 발생하는 오류입니다.")
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_3", "만료된 인증 토큰입니다."),

    @ExplainError("인증 로직이 실패한 경우 발생하는 오류입니다.")
    AUTHENTICATION_FAIL(UNAUTHORIZED, "AUTH_401_4", "인증에 실패하였습니다."),

    @ExplainError("해당 작업을 수행할 수 있는 권한이 없는 역할입니다.")
    INVALID_ROLE(FORBIDDEN, "AUTH_403_1", "Invalid role for this operation"),

    @ExplainError("유효하지 않은 토큰 클레임입니다.")
    INVALID_CLAIMS(BAD_REQUEST, "AUTH_401_4", "유효하지 않은 토큰 클레임입니다."),

    @ExplainError("회원이 존재하지 않는 경우 발생하는 오류입니다.")
    MEMBER_NOT_FOUND(NOT_FOUND, "AUTH_404_1", "해당 회원이 존재하지 않습니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() {
        return this.reason;
    }
}


