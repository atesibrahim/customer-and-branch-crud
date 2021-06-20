package com.stech_vent.customer_and_branch_crud.controller.branch;

import com.stech_vent.customer_and_branch_crud.controller.branch.contract.BranchController;
import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.service.branch.contract.BranchService;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BranchControllerImpl implements BranchController {

  private static final Logger logger = LoggerFactory.getLogger(BranchControllerImpl.class);


  private BranchService branchService;

  @Autowired
  public BranchControllerImpl(BranchService branchService) {
        this.branchService = branchService;
  }

  @Override
  public ResponseEntity<String> checkHealth() {
    logger.info("checkHealth");
    return new ResponseEntity<>("service is up and running",
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Set<Customer>> getBranchCustomers(Long id) throws ResourceNotFoundException {
    return new ResponseEntity<>(branchService.getBranchCustomers(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Branch> addCustomerToBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException {
    return new ResponseEntity<>(branchService.addCustomerToBranch(customerId, branchId), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Set<Customer>> deleteCustomerFromBranch(Long customerId, Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException {
    return new ResponseEntity<>(branchService.deleteCustomerFromBranch(customerId, branchId), HttpStatus.OK);
  }
}
