package com.codingdojo.firstproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.codingdojo.firstproject.services.CustomersAndTransactionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingdojo.firstproject.models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class TransactionsController {
	@Autowired
    private CustomersAndTransactionsService customersAndTransactionsService;
	
    // Get all transactions
    @GetMapping("/transactions")
    public List<Transaction> showAllTransactions() {
    	return customersAndTransactionsService.allTransactions();	
    }
    
    // Get a transaction
    @GetMapping("/transactions/{id}")
    public Transaction showTransaction(@PathVariable Long id) {
    	return customersAndTransactionsService.findTransaction(id);	
    }   
}
