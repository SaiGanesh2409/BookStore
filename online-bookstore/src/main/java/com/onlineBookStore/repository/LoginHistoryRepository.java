package com.onlineBookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineBookStore.Entity.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

}
