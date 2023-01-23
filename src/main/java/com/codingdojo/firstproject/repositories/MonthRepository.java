package com.codingdojo.firstproject.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.codingdojo.firstproject.models.Month;
import java.util.List;

@Repository
public interface MonthRepository extends CrudRepository<Month, Long>{
    List<Month> findAll();
}
