package org.seoultech.tableapi.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.order.entity.Order;
import org.seoultech.tableapi.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "store")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", unique = true, nullable = false)
    private User owner;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen;

    @Column(name = "open_time", nullable = false)
    private LocalDateTime openTime;

    @Column(name = "close_time", nullable = false)
    private LocalDateTime closeTime;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;

    @OneToMany(mappedBy = "store")
    private List<Notice> notices;

    @Builder
    public Store(User owner, String storeName, Boolean isOpen, LocalDateTime openTime, LocalDateTime closeTime) {
        this.owner = owner;
        this.storeName = storeName;
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

}
