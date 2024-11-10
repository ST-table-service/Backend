package org.seoultech.tableapi.pay.repository;

import org.seoultech.tableapi.pay.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
