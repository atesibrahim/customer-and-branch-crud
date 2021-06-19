package com.stech_vent.customer_and_branch_crud.dao.repository.contract;

import com.stech_vent.customer_and_branch_crud.dao.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
