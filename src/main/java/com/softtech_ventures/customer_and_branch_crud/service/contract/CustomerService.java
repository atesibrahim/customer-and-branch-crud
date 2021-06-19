package com.softtech_ventures.customer_and_branch_crud.service.contract;

import com.softtech_ventures.customer_and_branch_crud.dao.model.Branch;
import com.softtech_ventures.customer_and_branch_crud.dao.model.Customer;
import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotModifiedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CustomerService {

  Customer getCustomerById(Long id) throws ResourceNotFoundException;

  List<Customer> getCustomerList();

  Customer updateCustomerById(Long id, Customer customer) throws ResourceNotFoundException, ResourceNotModifiedException;

  Customer addCustomer(Customer customer) throws ResourceNotImplementedException;

  void deleteCustomerById(Long id) throws ResourceNotImplementedException;

  Set<Branch> getCustomerBranches(Long id) throws ResourceNotFoundException;

  Set<Customer> getBranchCustomers(Long id) throws ResourceNotFoundException;

  Branch addCustomerToBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  Branch deleteCustomerFromBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  Customer addBranchToCustomer(Long branchId, Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;

  Customer deleteBranchFromCustomer(Long branchId, Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;
}
