package org.seoultech.tableapi.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.global.generic.Money;
import org.seoultech.tableapi.global.generic.MoneyConverter;

@Table(name = "menu_option")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "option_name", nullable = false)
    private String optionName;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "price", nullable = false)
    private Money price;

    @Column(name = "option_available", nullable = false)
    private Boolean optionAvailable;

    @Builder
    public MenuOption(Menu menu, String optionName, Money price, Boolean optionAvailable) {
        this.menu = menu;
        this.optionName = optionName;
        this.price = price;
        this.optionAvailable = optionAvailable;
    }
}
