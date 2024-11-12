package org.seoultech.tableapi.auth.exception;

import lombok.Getter;
import org.seoultech.tableapi.global.exception.GlobalCodeException;

@Getter
public class AuthException extends GlobalCodeException {

    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
