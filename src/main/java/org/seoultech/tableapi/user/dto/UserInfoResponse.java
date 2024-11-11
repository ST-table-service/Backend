package org.seoultech.tableapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoResponse {
    private String code;
    private String message;
    private Data data;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Data {
        private UserInfo userInfo;
        private List<StampInfo> stampInfo;
        private OrderSummary orderSummary;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserInfo {
        private String username;
        private Integer totalPaymentLast6Months;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class StampInfo {
        private String storeName;
        private Integer stampsCollected;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OrderSummary {
        private Integer recentOrdersCount;
        private Integer totalPayment;
    }
}
