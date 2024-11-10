package org.seoultech.tableapi.store.repository;

import org.seoultech.tableapi.store.entity.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
