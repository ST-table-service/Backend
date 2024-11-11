package org.seoultech.tableapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "회원 마이페이지 정보 응답 DTO")
public class UserInfoResponse {

    @Schema(description = "유저 이름", example = "홍길동")
    private String username;

    @Schema(description = "최근 6개월 구매금액", example = "19000")
    private Integer totalPaymentLast6Months;

    @Schema(description = "스탬프 적립 리스트")
    private List<StampInfo> stampInfo;

    @Schema(description = "나의 주문 횟수(최근 3개월)", example = "3")
    private Integer recentOrdersCount;

    @Schema(description = "총 주문 금액", example = "50000")
    private Integer totalPayment;

    @Data
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "스탬프 적립 정보")
    public static class StampInfo {

        @Schema(description = "식당 이름", example = "바비든든")
        private String storeName;

        @Schema(description = "스탬프 적립 개수", example = "5")
        private int stampsCollected;

        @Builder
        public StampInfo(String storeName, int stampsCollected) {
            this.storeName = storeName;
            this.stampsCollected = stampsCollected;
        }
    }

    @Builder
    public UserInfoResponse(String username, Integer totalPaymentLast6Months, List<StampInfo> stampInfo, Integer recentOrdersCount, Integer totalPayment) {
        this.username = username;
        this.totalPaymentLast6Months = totalPaymentLast6Months;
        this.stampInfo = stampInfo;
        this.recentOrdersCount = recentOrdersCount;
        this.totalPayment = totalPayment;
    }

}
