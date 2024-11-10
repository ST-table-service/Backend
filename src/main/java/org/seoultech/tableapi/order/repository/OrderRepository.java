package org.seoultech.tableapi.order.repository;

import org.seoultech.tableapi.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
