package org.seoultech.tableapi.pay.exception;

import lombok.Getter;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.user.exception.UserErrorCode;

@Getter
public class CouponException extends GlobalCodeException {

    public CouponException(UserErrorCode errorCode) {
        super(errorCode);
    }
}