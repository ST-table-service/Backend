package org.seoultech.tableapi.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String useremail;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String useremail, String username, String password, Role role) {
        this.useremail = useremail;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
