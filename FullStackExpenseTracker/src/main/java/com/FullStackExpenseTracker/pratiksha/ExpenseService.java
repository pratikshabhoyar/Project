package com.FullStackExpenseTracker.pratiksha;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
	 @Autowired
	    private ExpenseRepository expenseRepository;

	    public ExpenseEntity saveExpense(ExpenseEntity expense) {
	        expense.setCreatedAt(LocalDateTime.now());
	        return expenseRepository.save(expense);
	    }

	    public List<ExpenseEntity> getExpenses(UserEntity user) {
	        return expenseRepository.findByUser(user);
	    }

	    public ExpenseEntity updateExpense(ExpenseEntity expense) {
	        expense.setUpdatedAt(LocalDateTime.now());
	        return expenseRepository.save(expense);
	    }

	    public void deleteExpense(Long id) {
	        expenseRepository.deleteById(id);
	    }

		public ExpenseEntity findById(Long id) {
			// TODO Auto-generated method stub
			expenseRepository.findById(id);
			return null;
		}

		
}
