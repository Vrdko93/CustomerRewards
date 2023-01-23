package com.codingdojo.firstproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.codingdojo.firstproject.services.CustomersAndTransactionsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingdojo.firstproject.models.Customer;
import com.codingdojo.firstproject.models.Month;
import com.codingdojo.firstproject.models.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CustomersController {
	
	@Autowired
    private CustomersAndTransactionsService customersAndTransactionsService;
	
    // Create month objects - Go to this URL first to trigger the creation of month objects
    @GetMapping("/")
    public List<Month> createMonths() {
    	List<Month> months = customersAndTransactionsService.allMonths();
    	
    	if (months.isEmpty()) {	
			customersAndTransactionsService.createMonth(new Month(1L, "January"));
			customersAndTransactionsService.createMonth(new Month(2L, "February"));
			customersAndTransactionsService.createMonth(new Month(3L, "March"));
			customersAndTransactionsService.createMonth(new Month(4L, "April"));
			customersAndTransactionsService.createMonth(new Month(5L, "May"));
			customersAndTransactionsService.createMonth(new Month(6L, "June"));
			customersAndTransactionsService.createMonth(new Month(7L, "July"));
			customersAndTransactionsService.createMonth(new Month(8L, "August"));
			customersAndTransactionsService.createMonth(new Month(9L, "September"));
			customersAndTransactionsService.createMonth(new Month(10L, "October"));
			customersAndTransactionsService.createMonth(new Month(11L, "November"));
			customersAndTransactionsService.createMonth(new Month(12L, "December"));
			
			months = customersAndTransactionsService.allMonths();
    	}
    	
    	return months;
    }
    
    // Process customer creation
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
    	return customersAndTransactionsService.createCustomer(customer);
    }
    
    // Get all customers
    @GetMapping("/customers")
	public List<Customer> showAllCustomers() {
		
		return customersAndTransactionsService.allCustomers();
	}
    
    // Get a customer
    @GetMapping("customers/{id}")
	public Customer showCustomer(@PathVariable Long id) {
		
		return customersAndTransactionsService.findCustomer(id);
	}
    
    // Customer makes a transaction
    @PostMapping("/customers/{id}/{amount}/{monthID}")
    public Transaction createTransacion(@PathVariable Long id, @PathVariable int amount, @PathVariable Long monthID) {
    	
    	Customer currentCustomer = customersAndTransactionsService.findCustomer(id);
    	Month currentMonth = customersAndTransactionsService.findMonth(monthID);
    	Transaction currentTransaction = new Transaction(amount, currentCustomer, currentMonth);
    	
    	customersAndTransactionsService.createTransaction(currentTransaction);
    	currentCustomer.getTransactions().add(currentTransaction);
    	currentMonth.getTransactions().add(currentTransaction);
    	
    	int currentPoints = currentCustomer.getPointsEarned();
    	currentPoints += currentTransaction.getPointsEarned();
    	currentCustomer.setPointsEarned(currentPoints);
    	customersAndTransactionsService.updateCustomer(currentCustomer);
    	
    	return currentTransaction;	 
    }
    
    // Get total rewards for one customer for one month
    @GetMapping("/customers/rewards/{id}/{monthID}")
    public int getRewardsForOneCustomerForOneMonth(@PathVariable Long id, @PathVariable Long monthID) {
    	
    	List<Transaction> transactions = customersAndTransactionsService.findTransactionsWithCustomerAndMonth(id, monthID);
    	int rewardsforOneMonth = 0;
    	
    	for (Transaction transaction : transactions) {
    		rewardsforOneMonth += transaction.getPointsEarned();
    	}

    	return rewardsforOneMonth;	 
    }
    
    // Get total rewards for one customer for 3 consecutive months combined
    @GetMapping("/customers/rewards/threemonth/{id}/{startingMonthID}")
    public int getRewardsForOneCustomerForThreeMonths(@PathVariable Long id, @PathVariable Long startingMonthID) {
    	
    	int totalRewardsforThreeMonths = 0;
    	// Using starting month to come up with 3 consecutive months and using the modulo % operator in case starting month is 11 or 12
    	List<Transaction> transactions1 = customersAndTransactionsService.findTransactionsWithCustomerAndMonth(id, startingMonthID % 12);
    	List<Transaction> transactions2 = customersAndTransactionsService.findTransactionsWithCustomerAndMonth(id, (startingMonthID + 1) % 12);
    	List<Transaction> transactions3 = customersAndTransactionsService.findTransactionsWithCustomerAndMonth(id, (startingMonthID + 2) % 12);
    	
    	
    	for (Transaction transaction : transactions1) {
    		totalRewardsforThreeMonths += transaction.getPointsEarned();
    	}
    	
    	for (Transaction transaction : transactions2) {
    		totalRewardsforThreeMonths += transaction.getPointsEarned();
    	}
    	
    	for (Transaction transaction : transactions3) {
    		totalRewardsforThreeMonths += transaction.getPointsEarned();
    	}

    	return totalRewardsforThreeMonths;	 	
    }
    
    // Get total rewards for one customer for all months combined
    @GetMapping("/customers/rewards/{id}")
    public int getRewardsForOneCustomerForAllMonths(@PathVariable Long id) {
    	
    	Customer customer = customersAndTransactionsService.findCustomer(id);
  
    	return customer.getPointsEarned();	 
    }
}
