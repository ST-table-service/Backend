package org.seoultech.tableapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinRequest {

    private String useremail;
    private String username;
    private String password;
}
