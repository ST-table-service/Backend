package org.seoultech.tableapi.pay.repository;

import org.seoultech.tableapi.pay.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
