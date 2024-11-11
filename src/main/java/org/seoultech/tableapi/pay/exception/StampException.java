package org.seoultech.tableapi.pay.exception;

import lombok.Getter;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.user.exception.UserErrorCode;

@Getter
public class StampException extends GlobalCodeException {

    public StampException(StampErrorCode errorCode) {
        super(errorCode);
    }
}