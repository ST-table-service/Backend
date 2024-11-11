package org.seoultech.tableapi.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.seoultech.tableapi.auth.exception.AuthException;
import org.seoultech.tableapi.global.dto.ErrorReason;
import org.seoultech.tableapi.global.dto.ErrorResponse;
import org.seoultech.tableapi.user.exception.UserErrorCode;
import org.seoultech.tableapi.user.exception.UserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // WebRequest 객체를 ServletWebRequest로 캐스팅하여 HttpServletRequest 객체를 추출
        ServletWebRequest servletWebRequest = (ServletWebRequest)request;
        // 예외가 발생한 URL과 같은 요청에 대한 세부 정보를 추출
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        // 예외 요청이 발생한 URL을 문자열 형태로 추출
        String url = httpServletRequest.getRequestURL().toString();

        // HttpStatusCode를 HttpStatus로 캐스팅하여 HTTP 상태 코드와 메시지를 추출
        HttpStatus httpStatus = (HttpStatus)status;
        // 사용자 정의 ErrorResponse 객체를 생성
        ErrorResponse errorResponse =
                new ErrorResponse(httpStatus.value(),   // HTTP 상태 코드
                        httpStatus.getReasonPhrase(),   // HTTP 상태 메시지
                        ex.getMessage(),                // 예외 메시지
                        url);                           // 예외가 발생한 URL
        // ResponseEntityExceptionHandler의 handleExceptionInternal 메서드를 호출하여 결과를 반환
        return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    // Auth 사용자 정의 오류 응답 생성
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException ex, HttpServletRequest request) {

        ErrorReason reason = ex.getErrorReason();
        ErrorResponse errorResponse =
                new ErrorResponse(ex.getErrorReason(), request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.valueOf(reason.getStatus()))
                .body(errorResponse);
    }

    // User 사용자 정의 오류 응답 생성
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleMemberException(UserException ex, HttpServletRequest request) {

        ErrorReason reason = ex.getErrorReason();
        ErrorResponse errorResponse =
                new ErrorResponse(ex.getErrorReason(), request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.valueOf(reason.getStatus()))
                .body(errorResponse);
    }


    //주로 요청 본문이 유효성 검사를 통과하지 못할 때 발생
    @SneakyThrows // 메서드 선언부에 Throws 를 정의하지 않고도, 검사 된 예외를 Throw 할 수 있도록 하는 Lombok 에서 제공하는 어노테이션
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // 유효성 검사를 통과하지 못한 필드의 에러를 추출
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();   //유효성 검사 싶패에 대한 결과 반환, 실패한 필드의 리스트 반환
        //위와 동일
        ServletWebRequest servletWebRequest = (ServletWebRequest)request;
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
        String url = httpServletRequest.getRequestURL().toString();

        String firstErrorMessage = errors.getFirst().getDefaultMessage();

        // 이메일 형식 잘못 입력한 경우
        if (firstErrorMessage.equals("INVALID_EMAIL")) {
            UserErrorCode errorCode = UserErrorCode.INVALID_EMAIL;
            UserException memberException = new UserException(errorCode);

            ErrorReason reason = memberException.getErrorReason();
            ErrorResponse errorResponse = new ErrorResponse(reason, url);

            return ResponseEntity.status(HttpStatus.valueOf(reason.getStatus()))
                    .body(errorResponse);
        }

        // 비밀번호 형식 잘못 입력한 경우
        if (firstErrorMessage.equals("INVALID_PASSWORD")) {
            UserErrorCode errorCode = UserErrorCode.INVALID_PASSWORD_FORMAT;
            UserException memberException = new UserException(errorCode);

            ErrorReason reason = memberException.getErrorReason();
            ErrorResponse errorResponse = new ErrorResponse(reason, url);

            return ResponseEntity.status(HttpStatus.valueOf(reason.getStatus()))
                    .body(errorResponse);
        }

        // stream을 사용해 FieldError 객체 리스트에서 필드 이름과 해당 오류 메시지를 맵으로 변환
        Map<String, Object> fieldAndErrorMessages =
                errors.stream()
                        .collect(
                                Collectors.toMap(
                                        FieldError::getField, FieldError::getDefaultMessage));

        // JSON 문자열로 변환
        String errorsToJsonString = new ObjectMapper().writeValueAsString(fieldAndErrorMessages);

        HttpStatus httpStatus = (HttpStatus)status;
        // 사용자 정의 ErrorResponse 객체를 생성
        ErrorResponse errorResponse =
                new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), errorsToJsonString, url);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /** Request Param Validation 예외 처리
     * 유효성 검사 제약 조건이 위반되었을 때 발생합니다. (예: @NotNull, @Size, @Email 어노테이션 사용 시)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(
            ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, Object> bindingErrors = new HashMap<>(); // 유효성 검사 실패 필드와 해당 오류 메시지를 저장하기 위한 맵을 생성
        // 예외 객체에서 유효성 검사 위반 항목들을 가져옴.
        ex.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            //위반된 속성의 경로를 가져옴. 이 경로는 문자열로 변환되어 점(.)을 기준으로 분할
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            // 마지막 요소를 추출하여 실제 필드 이름을 가져옴. ex) 경로가 user.address.street면 street가 추출
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            //위반된 필드 이름과 해당 오류 메시지를 맵에 저장
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        ErrorReason errorReason =
                ErrorReason.builder()
                        .code("BAD_REQUEST")
                        .status(400)
                        .reason(bindingErrors.toString())
                        .build();
        ErrorResponse errorResponse =
                new ErrorResponse(errorReason, request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(errorReason.getStatus()))
                .body(errorResponse);
    }

    @ExceptionHandler(GlobalCodeException.class)
    public ResponseEntity<ErrorResponse> GlobalCodeExceptionHandler(
            GlobalCodeException e, HttpServletRequest request) {
        BaseErrorCode code = e.getErrorCode();
        ErrorReason errorReason = code.getErrorReason();
        ErrorResponse errorResponse =
                new ErrorResponse(errorReason, request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(errorReason.getStatus()))
                .body(errorResponse);
    }

    @ExceptionHandler(GlobalDynamicException.class)
    public ResponseEntity<ErrorResponse> GlobalDynamicExceptionHandler(
            GlobalDynamicException e, HttpServletRequest request) {
        ErrorResponse errorResponse =
                new ErrorResponse(
                        e.getStatus(),
                        e.getCode(),
                        e.getReason(),
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(errorResponse);
    }

    //우리가 도메인에서 설정한 에러와 예외 처리에 대해서 이 위에 작성(처리)해야한다.
    //여기까지 왔다는 건 위에서 오류를 처리하지 못했다는 것이다. = 개발자가 예상할 수 없는 오류가 발생
    //디코나 메일로 알림이 가도록 구성해주는 것도 방법이라고 한다. (근데 이건 할 시간 없을 듯)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request)
            throws IOException {
        ServletWebRequest servletWebRequest = (ServletWebRequest)request;
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest(); // 예외가 발생한 URL과 같은 요청에 대한 세부 정보를 추출
        String url = httpServletRequest.getRequestURL().toString();

        log.error("INTERNAL_SERVER_ERROR", ex);
        GlobalErrorCode internalServerError = GlobalErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =
                new ErrorResponse(
                        internalServerError.getStatus(),
                        internalServerError.getCode(),
                        internalServerError.getReason(),
                        url);

        return ResponseEntity.status(HttpStatus.valueOf(internalServerError.getStatus()))
                .body(errorResponse);
    }
}