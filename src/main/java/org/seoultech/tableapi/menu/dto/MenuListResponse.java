package org.seoultech.tableapi.menu.dto;

import org.seoultech.tableapi.menu.domain.Menu;

import java.util.List;

public record MenuListResponse(
        List<MenuResponse> menus
) {
    public static MenuListResponse from(List<Menu> menus) {
        List<MenuResponse> responses = menus.stream()
                .map(MenuResponse::from)
                .toList();

        return new MenuListResponse(responses);
    }
}
