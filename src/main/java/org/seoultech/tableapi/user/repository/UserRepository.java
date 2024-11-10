package org.seoultech.tableapi.user.repository;

import org.seoultech.tableapi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUseremail(String useremail);
    Boolean existsByUseremail(String useremail);
}
