package org.seoultech.tableapi.global.generic;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money.getPrice().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long amount) {
        return Money.wons(amount);
    }
}
