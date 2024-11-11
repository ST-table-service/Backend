package org.seoultech.tableapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "회원 마이페이지 쿠폰 응답 DTO")
public class UserCouponResponse {

    @Schema(description = "쿠폰 ID", example = "1")
    private Long couponId;

    @Schema(description = "쿠폰 이름", example = "10% 할인 쿠폰")
    private String couponName;

    @Schema(description = "할인 금액", example = "1000")
    private Integer discountAmount;

    @Schema(description = "상점 이름", example = "바비든든")
    private String storeName;

    @Schema(description = "쿠폰 설명", example = "바비든든 10% 할인 쿠폰")
    private String description;

    @Schema(description = "최소 구매 금액", example = "5000")
    private Integer minimumPurchaseAmount;

    @Schema(description = "쿠폰 유효기간")
    private ValidityPeriod validityPeriod;

    @Schema(description = "쿠폰 사용 가능 여부", example = "true")
    private Boolean isAvailable;

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "쿠폰 유효기간")
    public class ValidityPeriod {

        @Schema(description = "시작일", example = "2024-10-01")
        private String startDate;

        @Schema(description = "종료일", example = "2024-10-31")
        private String endDate;

        @Builder
        public ValidityPeriod(String startDate, String endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    @Builder
    public UserCouponResponse(Long couponId, String couponName, Integer discountAmount, String storeName, String description, Integer minimumPurchaseAmount, ValidityPeriod validityPeriod, Boolean isAvailable) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.discountAmount = discountAmount;
        this.storeName = storeName;
        this.description = description;
        this.minimumPurchaseAmount = minimumPurchaseAmount;
        this.validityPeriod = validityPeriod;
        this.isAvailable = isAvailable;
    }
}
