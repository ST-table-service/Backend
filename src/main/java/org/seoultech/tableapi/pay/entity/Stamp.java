package org.seoultech.tableapi.pay.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.seoultech.tableapi.common.entity.BaseEntity;
import org.seoultech.tableapi.store.entity.Store;
import org.seoultech.tableapi.user.entity.User;

@Table(
        name = "stamp",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "store_id"})
        }
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stamp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "stamp_count", nullable = false)
    private Integer stampCount;

    @Builder
    public Stamp(User user, Store store, Integer stampCount) {
        this.user = user;
        this.store = store;
        this.stampCount = stampCount;
    }

}