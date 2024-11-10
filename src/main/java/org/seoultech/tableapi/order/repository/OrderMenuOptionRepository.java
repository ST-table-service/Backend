package org.seoultech.tableapi.order.repository;

import org.seoultech.tableapi.order.entity.OrderMenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuOptionRepository extends JpaRepository<OrderMenuOption, Long> {
}
