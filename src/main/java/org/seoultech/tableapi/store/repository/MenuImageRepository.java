package org.seoultech.tableapi.store.repository;

import org.seoultech.tableapi.store.entity.MenuImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuImageRepository extends JpaRepository<MenuImage, Long> {
}
