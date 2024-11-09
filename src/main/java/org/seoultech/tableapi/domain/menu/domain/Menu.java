package org.seoultech.tableapi.domain.menu.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.domain.restaurant.domain.Restaurant;

@Table(name = "menu")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO : Restaurant mapping
    @ManyToOne
    private Restaurant restaurant;

    private String name;

    private String description;

    private Long price;

    private boolean isBest;

    private boolean isPopular;

    private boolean isAvailable;

    private Long totalOrderCount;

    // TODO : BaseEntity 시간 추가

    @Builder
    public Menu(
            String name,
            String description,
            Long price,
            boolean isBest,
            boolean isPopular,
            boolean isAvailable,
            Long totalOrderCount
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isBest = isBest;
        this.isPopular = isPopular;
        this.isAvailable = isAvailable;
        this.totalOrderCount = totalOrderCount;
    }
}
