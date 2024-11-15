package org.seoultech.tableapi.global.generic;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
public class Money {
    public static final int SCALE = 0;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal price;

    private Money(BigDecimal price) {
        this.price = stripToInteger(price);
    }

    public Money plus(Money amount) {
        return new Money(this.price.add(amount.price));
    }

    public Money minus(Money amount) {
        return new Money(this.price.subtract(amount.price));
    }

    public Money multiply(double percent) {
        return new Money(stripToInteger(this.price.multiply(BigDecimal.valueOf(percent))));
    }

    public Money divide(double percent) {
        return new Money(stripToInteger(price.divide(BigDecimal.valueOf(percent), SCALE, ROUNDING_MODE)));
    }

    public boolean isLessThan(Money other) {
        return price.compareTo(other.price) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return price.compareTo(other.price) >= 0;
    }

    public String toString() {
        return price.toString() + "원";
    }

    // Factory Method
    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    // 소수점 제거
    private BigDecimal stripToInteger(BigDecimal value) {
        return value.setScale(SCALE, ROUNDING_MODE);
    }
}