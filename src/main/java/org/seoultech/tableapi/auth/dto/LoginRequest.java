package org.seoultech.tableapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

    private final String useremail;

    private final String password;
}
