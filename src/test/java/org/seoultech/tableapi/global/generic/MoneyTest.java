package org.seoultech.tableapi.global.generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MoneyTest {

    @Test
    @DisplayName("정적 팩토리 메서드를 통해 Money 객체를 생성할 수 있다")
    void testFactoryMethod() {
        Money money1 = Money.wons(1000);
        Money money2 = Money.wons(1000.50);

        assertEquals(new BigDecimal("1000"), money1.getPrice());
        assertEquals(new BigDecimal("1001"), money2.getPrice());  // SCALE이 0이므로 반올림 처리
    }

    @Test
    @DisplayName("Money 객체끼리 덧셈이 가능하다 - 다양한 값")
    void testPlusVariousValues() {
        Money money1 = Money.wons(1000);
        Money money2 = Money.wons(2000);
        Money result1 = money1.plus(money2);
        assertEquals(Money.wons(3000), result1);

        Money negative = Money.wons(-500);
        Money result2 = money1.plus(negative);
        assertEquals(Money.wons(500), result2);

        Money zero = Money.ZERO;
        Money result3 = money1.plus(zero);
        assertEquals(money1, result3);

        Money largeMoney = Money.wons(1_000_000_000);
        Money result4 = largeMoney.plus(money1);
        assertEquals(Money.wons(1_000_001_000), result4);
    }

    @Test
    @DisplayName("Money 객체에 백분율을 곱할 수 있다 - 다양한 값")
    void testMultiplyVariousValues() {
        Money money = Money.wons(1000);

        Money result1 = money.multiply(2.0);
        assertEquals(Money.wons(2000), result1);

        Money result2 = money.multiply(0.5);
        assertEquals(Money.wons(500), result2);

        Money result3 = money.multiply(0);
        assertEquals(Money.ZERO, result3);

        Money negativeMoney = Money.wons(-1000);
        Money result4 = negativeMoney.multiply(1.5);
        assertEquals(Money.wons(-1500), result4);

        Money result5 = money.multiply(0.3333); // 반올림 테스트
        assertEquals(Money.wons(333), result5);
    }


    @Test
    @DisplayName("Money 객체를 소수 비율로 나누는 경우")
    void testDivideWithDecimalValues() {
        Money money = Money.wons(1000);

        Money result1 = money.divide(3);
        assertEquals(Money.wons(333), result1);

        Money result2 = money.divide(0.3333); // 소수점 이하 처리 확인
        assertEquals(Money.wons(3000), result2);
    }

    @Test
    @DisplayName("소수점 처리와 반올림 동작을 확인하는 곱셈 테스트")
    void testMultiplyWithRounding() {
        Money money = Money.wons(999.99);  // 소수점 반올림 테스트
        assertEquals(Money.wons(1000), money);

        Money downMoney = Money.wons(1000.49);  // 반올림 적용 안 됨
        assertEquals(Money.wons(1000), downMoney);

        Money roundedUpMoney = Money.wons(1000.51);  // 반올림 테스트
        assertEquals(Money.wons(1001), roundedUpMoney);
    }
}
