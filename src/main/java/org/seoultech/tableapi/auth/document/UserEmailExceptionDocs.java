package org.seoultech.tableapi.auth.document;

import org.seoultech.tableapi.global.annotation.ExceptionDoc;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.exception.GlobalCodeException;
import org.seoultech.tableapi.global.interfaces.SwaggerExampleExceptions;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;


@ExceptionDoc
public class UserEmailExceptionDocs implements SwaggerExampleExceptions {

    @ExplainError
    public GlobalCodeException 이미_등록된_이메일 = new UserException(UserErrorCode.EMAIL_ALREADY_REGISTERED);

}
