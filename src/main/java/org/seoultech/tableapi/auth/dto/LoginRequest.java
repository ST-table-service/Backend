package org.seoultech.tableapi.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    @NotNull
    @Schema(description = "이메일", example = "exampe@seoultech.ac.kr")
    private final String useremail;

    @NotNull
    @Schema(description = "비밀번호", example = "password1234!")
    private final String password;
}
