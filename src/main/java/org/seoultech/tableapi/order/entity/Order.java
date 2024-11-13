package org.seoultech.tableapi.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.global.generic.Money;
import org.seoultech.tableapi.global.generic.MoneyConverter;
import org.seoultech.tableapi.store.entity.MenuImage;
import org.seoultech.tableapi.store.entity.Store;
import org.seoultech.tableapi.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "order")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_price", nullable = false)
    private Money totalPrice;

    @Column(name = "discount")
    private Integer discount;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "total_payment", nullable = false)
    private Money totalPayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "payed_at", nullable = false)
    private LocalDateTime payedAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus;

    @Builder
    public Order(User user, Store store, Money totalPrice, Integer discount, Money totalPayment, OrderStatus orderStatus, LocalDateTime payedAt, LocalDateTime endAt) {
        this.user = user;
        this.store = store;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.totalPayment = totalPayment;
        this.orderStatus = orderStatus;
        this.payedAt = payedAt;
        this.endAt = endAt;
    }
}
