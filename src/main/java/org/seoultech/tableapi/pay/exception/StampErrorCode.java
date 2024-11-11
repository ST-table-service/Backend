package org.seoultech.tableapi.pay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.dto.ErrorReason;
import org.seoultech.tableapi.global.exception.BaseErrorCode;

import static org.seoultech.tableapi.global.consts.STtableStatic.*;

@Getter
@AllArgsConstructor
public enum StampErrorCode implements BaseErrorCode {

    @ExplainError("결제 금액이 5,000원 미만인 경우 발생하는 오류입니다.")
    MINIMUM_AMOUNT_NOT_MET(BAD_REQUEST, "STAMP_400_1", "적립에 필요한 최소 결제 금액에 미달했습니다."),

    @ExplainError("존재하지 않는 가게에서 스탬프 적립을 시도한 경우 발생하는 오류입니다.")
    STORE_NOT_FOUND(BAD_REQUEST, "STAMP_400_2", "존재하지 않는 가게입니다."),

    @ExplainError("존재하지 않는 사용자가 스탬프 적립을 시도한 경우 발생하는 오류입니다.")
    USER_NOT_FOUND(BAD_REQUEST, "STAMP_400_3", "존재하지 않는 사용자입니다."),

    @ExplainError("스탬프 데이터가 불일치하거나 오류가 있는 경우 발생하는 오류입니다.")
    STAMP_DATA_INCONSISTENCY(BAD_REQUEST, "STAMP_400_4", "스탬프 데이터에 오류가 발생했습니다."),

    @ExplainError("동일한 결제 건에 대해 중복으로 스탬프 적립을 시도한 경우 발생하는 오류입니다.")
    DUPLICATE_STAMP_ATTEMPT(BAD_REQUEST, "STAMP_400_5", "이미 적립된 결제 건입니다."),

    @ExplainError("스탬프 적립 중 시스템 오류가 발생한 경우 발생하는 오류입니다.")
    STAMP_SYSTEM_ERROR(BAD_REQUEST, "STAMP_400_6", "스탬프 적립 중 시스템 오류가 발생했습니다.");

    private final Integer status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() {
        return this.reason;
    }
}
