package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;

@ExceptionDoc
public class VerifyEmailExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 인증코드_불일치 = new UserException(UserErrorCode.INVALID_VERIFICATION_CODE);

    @ExplainError
    public GlobalCodeException 인증코드_만료 = new UserException(UserErrorCode.EXPIRATION_VERIFICATION_CODE);

}
