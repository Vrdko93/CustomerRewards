package com.codingdojo.firstproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.firstproject.models.Transaction;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    List<Transaction> findAll();
    List<Transaction> findByCustomer_idAndMonth_id(Long customer_id, Long month_id);
}
