package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;

@ExceptionDoc
public class CertifyEmailExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 잘못된_이메일_형식 = new UserException(UserErrorCode.INVALID_EMAIL);

}