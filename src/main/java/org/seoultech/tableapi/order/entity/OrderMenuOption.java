package org.seoultech.tableapi.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
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
    private MenuOption menuOption; // 이 관계로 MenuOptions와 연관시킴

    @Column(nullable = false)
    private Integer price;
}
