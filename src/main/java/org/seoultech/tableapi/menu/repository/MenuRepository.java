package org.seoultech.tableapi.menu.repository;

import org.seoultech.tableapi.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
