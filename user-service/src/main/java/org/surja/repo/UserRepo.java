package org.surja.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.surja.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
