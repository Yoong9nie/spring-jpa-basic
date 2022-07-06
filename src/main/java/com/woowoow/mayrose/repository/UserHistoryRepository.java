package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
