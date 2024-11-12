package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.auth.exception.AuthErrorCode;
import org.seoultech.tableapi.auth.exception.AuthException;
import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class LoginExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 이메일_틀림 = new AuthException(AuthErrorCode.INVALID_USER_EMAIL);

    @ExplainError
    public GlobalCodeException 비밀번호_틀림 = new AuthException(AuthErrorCode.INVALID_PASSWORD);

}
