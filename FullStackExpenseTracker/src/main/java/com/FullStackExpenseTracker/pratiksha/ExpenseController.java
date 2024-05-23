package com.FullStackExpenseTracker.pratiksha;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	@Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestHeader("Authorization") String token, @RequestBody ExpenseEntity expense) {
        String username = Jwts.parser()
                .setSigningKey("SecretKey")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();

        UserEntity user = userService.findByUsername(username);
        expense.setUser(user);
        expenseService.saveExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body("Expense added successfully");
    }

    @GetMapping
    public ResponseEntity<?> viewExpenses(@RequestHeader("Authorization") String token) {
        String username = Jwts.parser()
                .setSigningKey("SecretKey")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();

        UserEntity user = userService.findByUsername(username);
        List<ExpenseEntity> expenses = expenseService.getExpenses(user);
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editExpense(@PathVariable Long id, @RequestBody ExpenseEntity expense) {
        ExpenseEntity existingExpense = expenseService.findById(id);
        if (existingExpense != null) {
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setComments(expense.getComments());
            expenseService.updateExpense(existingExpense);
            return ResponseEntity.ok("Expense updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
