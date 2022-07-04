package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
