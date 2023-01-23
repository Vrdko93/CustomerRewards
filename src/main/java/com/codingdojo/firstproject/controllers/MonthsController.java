package com.codingdojo.firstproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.codingdojo.firstproject.services.CustomersAndTransactionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingdojo.firstproject.models.Month;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MonthsController {
	
	@Autowired
    private CustomersAndTransactionsService customersAndTransactionsService;
	
    // Get all months
    @GetMapping("/months")
    public List<Month> showAllmonths() {
    	return customersAndTransactionsService.allMonths();	
    }
    
    // Get a month
    @GetMapping("/months/{id}")
    public Month showMonth(@PathVariable Long id) {
    	return customersAndTransactionsService.findMonth(id);	
    }   
}
