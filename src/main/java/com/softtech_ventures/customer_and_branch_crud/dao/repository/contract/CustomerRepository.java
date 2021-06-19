package com.softtech_ventures.customer_and_branch_crud.dao.repository.contract;

import com.softtech_ventures.customer_and_branch_crud.dao.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
