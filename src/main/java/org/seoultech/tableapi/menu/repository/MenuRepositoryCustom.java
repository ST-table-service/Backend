package org.seoultech.tableapi.menu.repository;

import org.seoultech.tableapi.menu.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepositoryCustom {
    List<Menu> findAllByRestaurantId(Long restaurantId);

    Optional<Menu> findMenuWithRestaurant(Long id);
}