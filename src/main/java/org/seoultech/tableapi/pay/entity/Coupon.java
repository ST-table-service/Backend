package org.seoultech.tableapi.pay.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.store.entity.Store;
import org.seoultech.tableapi.user.entity.User;

import java.time.LocalDate;

@Table(name = "coupon")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @Column(name = "discount_price", nullable = false)
    private Integer discountPrice;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Builder
    public Coupon(User user, Store store, String couponName, Integer discountPrice,
                  LocalDate startDate, LocalDate endDate, Boolean isAvailable) {
        this.user = user;
        this.store = store;
        this.couponName = couponName;
        this.discountPrice = discountPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isAvailable = isAvailable;
    }
}
