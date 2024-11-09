package org.seoultech.tableapi.menu.dto;

import org.seoultech.tableapi.menu.domain.Menu;

public record MenuResponse(
        String restaurantName,
        String name,
        String description,
        Long price
) {
    public static MenuResponse from(Menu menu) {
        return new MenuResponse(
                menu.getRestaurant().getName(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice()
        );
    }
}
