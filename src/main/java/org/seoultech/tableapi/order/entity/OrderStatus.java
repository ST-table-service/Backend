package org.seoultech.tableapi.order.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("결제 대기"),
    ORDERED("주문 완료"),
    PREPARING("음식 준비중"),
    FOODREADY("음식 준비 완료");

    private final String name;

    private static final Map<String, OrderStatus> NAME_TO_ENUM_MAP = new HashMap<>();

    static {
        for (OrderStatus type : OrderStatus.values()) {
            NAME_TO_ENUM_MAP.put(type.getName(), type);
        }
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static OrderStatus fromName(String name) {
        OrderStatus type = NAME_TO_ENUM_MAP.get(name);

        if (type == null) {
           //TODO: 유효하지 않은 주문 상태 유형 에러 출력
        }

        return type;
    }
}
