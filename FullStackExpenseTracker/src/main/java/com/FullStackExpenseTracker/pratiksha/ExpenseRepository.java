package com.FullStackExpenseTracker.pratiksha;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>{
	List<ExpenseEntity> findByUser(UserEntity user);
}
