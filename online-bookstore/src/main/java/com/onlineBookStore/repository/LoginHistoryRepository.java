package com.onlineBookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineBookStore.Entity.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

	List<LoginHistory> findAllByUserId(Long userId);

}
