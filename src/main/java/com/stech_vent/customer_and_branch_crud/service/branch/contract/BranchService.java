package com.stech_vent.customer_and_branch_crud.service.branch.contract;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BranchService {

  Set<Customer> getBranchCustomers(Long id) throws ResourceNotFoundException;

  Branch addCustomerToBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  Set<Customer> deleteCustomerFromBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

}
