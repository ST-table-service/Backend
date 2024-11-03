package org.seoultech.tableapi.domain.menu.repository;

import org.seoultech.tableapi.domain.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
