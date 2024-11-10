package org.seoultech.tableapi.order.repository;

import org.seoultech.tableapi.order.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
