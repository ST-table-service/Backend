package org.seoultech.tableapi.store.repository;

import org.seoultech.tableapi.store.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
