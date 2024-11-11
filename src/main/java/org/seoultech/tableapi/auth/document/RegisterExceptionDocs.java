package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.auth.exception.AuthErrorCode;
import org.seoultech.tableapi.auth.exception.AuthException;
import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;

@ExceptionDoc
public class RegisterExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 잘못된_이메일_형식 = new UserException(UserErrorCode.INVALID_EMAIL);

    @ExplainError
    public GlobalCodeException 잘못된_비밀번호_형식 = new UserException(UserErrorCode.INVALID_PASSWORD_FORMAT);

    @ExplainError
    public GlobalCodeException 이미_등록된_이메일 = new UserException(UserErrorCode.EMAIL_ALREADY_REGISTERED);

    @ExplainError
    public GlobalCodeException 잘못된_권한_형식 = new UserException(UserErrorCode.INVALID_ROLE_FORMAT);
}
