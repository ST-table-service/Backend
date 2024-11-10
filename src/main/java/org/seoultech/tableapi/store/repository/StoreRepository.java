package org.seoultech.tableapi.store.repository;

import org.seoultech.tableapi.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
