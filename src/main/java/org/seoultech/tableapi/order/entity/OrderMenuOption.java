package org.seoultech.tableapi.order.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.global.generic.Money;
import org.seoultech.tableapi.global.generic.MoneyConverter;
import org.seoultech.tableapi.store.entity.MenuOption;

@Table(name = "order_menu_option")
@Entity
@Getter
@NoArgsConstructor
public class OrderMenuOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_menu_id", nullable = false)
    private OrderMenu orderMenu;

    @ManyToOne
    @JoinColumn(name = "menu_option_id", nullable = false)
    private MenuOption menuOption;

    @Convert(converter = MoneyConverter.class)
    @Column(nullable = false)
    private Money price;

    @Builder
    public OrderMenuOption(OrderMenu orderMenu, MenuOption menuOption, Money price) {
        this.orderMenu = orderMenu;
        this.menuOption = menuOption;
        this.price = price;
    }
}
