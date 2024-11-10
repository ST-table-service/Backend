package org.seoultech.tableapi.store.repository;

import org.seoultech.tableapi.store.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
