package org.seoultech.tableapi.domain.restaurant.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // TODO 나머지 속성 채우기
}
