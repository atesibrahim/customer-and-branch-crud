package com.stech_vent.customer_and_branch_crud.service.branch.contract.impl;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.BranchRepository;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.CustomerRepository;
import com.stech_vent.customer_and_branch_crud.service.branch.contract.BranchService;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

  private static final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);


  private CustomerRepository customerRepository;

  private BranchRepository branchRepository;

  @Autowired
  public BranchServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository) {
    this.customerRepository = customerRepository;
    this.branchRepository = branchRepository;
  }

  public Customer getCustomerById(Long id) throws ResourceNotFoundException {

    return customerRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Customer not found for this id :: " + id));

  }

  @Override
  public Set<Customer> getBranchCustomers(Long id) throws ResourceNotFoundException {
    return getBranchById(id).getCustomers();
  }

  @Override
  public Branch addCustomerToBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException {
    Customer customer = getCustomerById(customerId);

    Branch branch = getBranchById(branchId);

    branch.getCustomers().add(customer);

    try {
      return branchRepository.save(branch);
    }catch (Exception exception){
      logger.info("Error occured while add customer to branch. error message:", exception.getMessage());
      throw new ResourceNotImplementedException("Error occured while add customer to branch:" + exception.getMessage());
    }
  }

  @Override
  public Set<Customer> deleteCustomerFromBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException {
    Customer customer = getCustomerById(customerId);

    Branch branch = getBranchById(branchId);

    branch.getCustomers().remove(customer);

  try{
      branchRepository.save(branch);
      return getBranchCustomers(branchId);
    }catch (Exception exception){
      logger.info("Error occured while delete customer from branch. error message:", exception.getMessage());
      throw new ResourceNotImplementedException("Error occured while delete customer from branch:" + exception.getMessage());
    }
  }

  private Branch getBranchById(Long id) throws ResourceNotFoundException {
    return branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + id));
  }
}
