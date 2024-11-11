package org.seoultech.tableapi.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.seoultech.tableapi.user.entity.Role;

public class RegisterRequest {

    @NotNull
    @Schema(description = "이메일", example = "example@seoultech.ac.kr")
    private String useremail;

    @NotNull
    @Schema(description = "이름", example = "홍길동")
    private String username;

    @NotNull
    @Schema(description = "비밀번호", example="password1234!")
    private String password;

}
