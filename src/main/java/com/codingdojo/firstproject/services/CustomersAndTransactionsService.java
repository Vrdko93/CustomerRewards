package com.codingdojo.firstproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingdojo.firstproject.repositories.CustomerRepository;
import com.codingdojo.firstproject.repositories.TransactionRepository;
import com.codingdojo.firstproject.repositories.MonthRepository;
import com.codingdojo.firstproject.models.Customer;
import com.codingdojo.firstproject.models.Transaction;
import com.codingdojo.firstproject.models.Month;
import java.util.List;
import java.util.Optional;

@Service
public class CustomersAndTransactionsService {

	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
    private TransactionRepository transactionRepository;
	@Autowired
    private MonthRepository monthRepository;
    
    // returns all the customers
    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }
    // creates a customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    // retrieves a customer
    public Customer findCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } 
        else {
            return null;
        }
    }
    
    // updates a customer
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    // returns all the transactions
    public List<Transaction> allTransactions() {
        return transactionRepository.findAll();
    }
    // creates a transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    // retrieves a transaction
    public Transaction findTransaction(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        } 
        else {
            return null;
        }
    }
    
    // Get transactions for a specific customer for a specific month
    public List<Transaction> findTransactionsWithCustomerAndMonth(Long customer_id, Long month_id) {
  
        List<Transaction> transactions = transactionRepository.findByCustomer_idAndMonth_id(customer_id, month_id);
        
        return transactions;

    }
    
    // returns all the months
    public List<Month> allMonths() {
        return monthRepository.findAll();
    }
    // creates a month
    public Month createMonth(Month month) {
        return monthRepository.save(month);
    }
    // retrieves a month
    public Month findMonth(Long id) {
        Optional<Month> optionalMonth = monthRepository.findById(id);
        if(optionalMonth.isPresent()) {
            return optionalMonth.get();
        } 
        else {
            return null;
        }
    }
}