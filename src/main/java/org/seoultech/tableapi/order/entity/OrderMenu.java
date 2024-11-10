package org.seoultech.tableapi.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.store.entity.Menu;
import org.seoultech.tableapi.store.entity.MenuImage;

import java.util.List;

@Table(name = "order_menu")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "menu_total_price", nullable = false)
    private Integer menuTotalPrice;

    @Column(name = "is_option", nullable = false)
    private Boolean isOption;

    @OneToMany(mappedBy = "order_menu")
    private List<OrderMenuOptions> orderMenuOptionsw;

    @Builder
    public OrderMenu(Menu menu, Order order, Integer quantity, Integer menuTotalPrice, Boolean isOption) {
        this.menu = menu;
        this.order = order;
        this.quantity = quantity;
        this.menuTotalPrice = menuTotalPrice;
        this.isOption = isOption;
    }
}
