package com.codingdojo.firstproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.codingdojo.firstproject.controllers.CustomersController;
import com.codingdojo.firstproject.models.Customer;
import com.codingdojo.firstproject.repositories.CustomerRepository;

@SpringBootTest
class FirstprojectApplicationTests {

	@Autowired
	private CustomersController controller;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void contextLoads() {
	}

    @Test
    void testController() {
        assertThat(controller).isNotNull();
    }
    
    @Test
    void createCustomer() {
    	 Customer customer = customerRepository.save(new Customer("Nick"));
         assertEquals(customer.getName(), "Nick");
         assertEquals(customer.getPointsEarned(), 0);
         assertNull(customer.getTransactions());
    }
}
