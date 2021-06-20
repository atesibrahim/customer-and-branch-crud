package com.stech_vent.customer_and_branch_crud.controller.customer;

import com.stech_vent.customer_and_branch_crud.controller.customer.contract.CustomerController;
import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.service.customer.contract.CustomerService;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CustomerControllerImpl implements CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerControllerImpl.class);


  private CustomerService customerService;

  @Autowired
  public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
  }

  @Override
  public ResponseEntity<String> checkHealth() {
    logger.info("checkHealth");
    return new ResponseEntity<>("service is up and running",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Customer> getCustomerById(Long id) throws ResourceNotFoundException {
    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Customer>> getCustomerList() {
    return new ResponseEntity<>(customerService.getCustomerList(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Customer> updateCustomerById(Long id, Customer customer) throws ResourceNotFoundException, ResourceNotModifiedException {
    return new ResponseEntity<>(customerService.updateCustomerById(id, customer), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Customer> addCustomer(Customer customer) throws ResourceNotImplementedException {
    return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Boolean> deleteCustomerById(Long id) throws ResourceNotImplementedException {
    customerService.deleteCustomerById(id);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Set<Branch>> getCustomerBranches(Long id) throws ResourceNotFoundException {
    return new ResponseEntity<>(customerService.getCustomerBranches(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Customer> addBranchToCustomer(Long branchId, Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException {
    return new ResponseEntity<>(customerService.addBranchToCustomer(branchId, customerId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Set<Branch> > deleteBranchFromCustomer(Long branchId, Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException {
    return new ResponseEntity<>(customerService.deleteBranchFromCustomer(branchId, customerId), HttpStatus.OK);
  }
}
