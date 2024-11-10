package org.seoultech.tableapi.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;

@Table(name = "nenu_image")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menuId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
