package org.seoultech.tableapi.global.annotation;

import org.seoultech.tableapi.global.exception.BaseErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeExample {
    Class<? extends BaseErrorCode> value();  // BaseErrorCode 타입을 확장한 에러 코드만 받음
}