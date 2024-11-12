package org.seoultech.tableapi.user.document;

import org.seoultech.tableapi.auth.exception.AuthErrorCode;
import org.seoultech.tableapi.auth.exception.AuthException;
import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;
import org.seoultech.tableapi.pay.exception.CouponErrorCode;
import org.seoultech.tableapi.pay.exception.CouponException;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;

@ExceptionDoc
public class UserCoupomExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 유저_없음 = new UserException(UserErrorCode.USER_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 토큰_없음 = new AuthException(AuthErrorCode.TOKEN_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);

    @ExplainError
    public GlobalCodeException 쿠폰_정보_조회_오류 = new CouponException(CouponErrorCode.COUPON_DATA_INCONSISTENCY);
}
