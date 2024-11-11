package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.auth.exception.AuthErrorCode;
import org.seoultech.tableapi.auth.exception.AuthException;
import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class JwtExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 토큰_만료 = new AuthException(AuthErrorCode.TOKEN_EXPIRED);

    @ExplainError
    public GlobalCodeException 토큰_없음 = new AuthException(AuthErrorCode.TOKEN_NOT_FOUND);

    @ExplainError
    public GlobalCodeException 토큰_유효하지_않음 = new AuthException(AuthErrorCode.INVALID_TOKEN);
}
