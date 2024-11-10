package org.seoultech.tableapi.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;

import java.util.List;

@Table(name = "menu")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "is_best", nullable = false)
    private Boolean isBest;

    @Column(name = "is_popular", nullable = false)
    private Boolean isPopular;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "order_count", nullable = false)
    private Integer orderCount;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "menu")
    private List<MenuImage> images;

    @OneToMany(mappedBy = "menu")
    private List<MenuOption> options;

    @Builder
    public Menu(Store store,
                String menuName,
                Boolean isBest,
                Boolean isPopular,
                Integer price,
                Boolean isAvailable,
                Integer orderCount,
                String description) {
        this.store = store;
        this.menuName = menuName;
        this.isBest = isBest;
        this.isPopular = isPopular;
        this.price = price;
        this.isAvailable = isAvailable;
        this.orderCount = orderCount;
        this.description = description;
    }
}
