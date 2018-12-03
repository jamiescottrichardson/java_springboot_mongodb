package com.ibm.cic.br.seeds.javaspringbootmongodb.dao;

import com.ibm.cic.br.seeds.javaspringbootmongodb.dao.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
