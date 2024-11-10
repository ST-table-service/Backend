package org.seoultech.tableapi.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import org.seoultech.tableapi.common.entity.BaseEntity;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String useremail;

    @Column(name = "name", nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "order_count", nullable = false)
    private Integer orderCount;

    @Builder
    public User(String useremail, String username, String password, Role role, Integer orderCount) {
        this.useremail = useremail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.orderCount = orderCount;
    }
}
