package com.codingdojo.firstproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.firstproject.models.Customer;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findAll();
    Customer findByName(String name);
}
