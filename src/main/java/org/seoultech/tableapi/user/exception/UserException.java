package org.seoultech.tableapi.user.exception;

import lombok.Getter;
import org.seoultech.tableapi.global.exception.GlobalCodeException;

@Getter
public class UserException extends GlobalCodeException {

    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
