package org.seoultech.tableapi.pay.exception;

import lombok.Getter;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.user.exception.UserErrorCode;

@Getter
public class CouponException extends GlobalCodeException {

    public CouponException(CouponErrorCode errorCode) {
        super(errorCode);
    }
}