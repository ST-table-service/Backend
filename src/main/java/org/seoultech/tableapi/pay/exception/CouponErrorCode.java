package org.seoultech.tableapi.pay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.seoultech.tableapi.global.annotation.ExplainError;
import org.seoultech.tableapi.global.dto.ErrorReason;
import org.seoultech.tableapi.global.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

import static org.seoultech.tableapi.global.consts.STtableStatic.*;

@Getter
@AllArgsConstructor
public enum CouponErrorCode implements BaseErrorCode {

    @ExplainError("스탬프가 부족하여 쿠폰을 발급할 수 없는 경우 발생하는 오류입니다.")
    INSUFFICIENT_STAMPS(BAD_REQUEST, "COUPON_400_1", "쿠폰 발급에 필요한 스탬프가 부족합니다."),

    @ExplainError("쿠폰 발급 로직에서 예외가 발생한 경우 발생하는 오류입니다.")
    COUPON_ISSUE_FAILURE(BAD_REQUEST, "COUPON_400_2", "쿠폰 발급 중 오류가 발생했습니다."),

    @ExplainError("잘못된 가게에서 발급 시도하는 경우 발생하는 오류입니다.")
    COUPON_RESTRICTED_TO_STORE(BAD_REQUEST, "COUPON_400_3", "잘못된 가게에서 쿠폰 발급을 시도하였습니다."),

    @ExplainError("쿠폰 데이터가 불일치하거나 오류가 있는 경우 발생하는 오류입니다.")
    COUPON_DATA_INCONSISTENCY(BAD_REQUEST, "COUPON_400_4", "쿠폰 데이터에 오류가 발생했습니다."),

    @ExplainError("이미 발급된 스탬프에 대해 중복으로 쿠폰을 발급하려는 경우 발생하는 오류입니다.")
    DUPLICATE_COUPON_ISSUE(BAD_REQUEST, "COUPON_400_5", "이미 발급된 쿠폰이 있습니다."),

    @ExplainError("쿠폰 발급 과정에서 시스템 오류가 발생한 경우 발생하는 오류입니다.")
    COUPON_SYSTEM_ERROR(BAD_REQUEST, "COUPON_400_6", "시스템 오류로 인해 쿠폰 발급이 실패했습니다."),

    @ExplainError("발급된 쿠폰의 사용 기한이 만료된 경우 발생하는 오류입니다.")
    COUPON_EXPIRED(BAD_REQUEST, "COUPON_400_7", "쿠폰의 사용 기한이 만료되었습니다.");

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
