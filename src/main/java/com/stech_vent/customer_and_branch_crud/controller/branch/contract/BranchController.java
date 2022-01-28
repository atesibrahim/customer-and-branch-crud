package com.stech_vent.customer_and_branch_crud.controller.branch.contract;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RequestMapping("/branchs")
public interface BranchController {

  @GetMapping("/health")
  ResponseEntity<String> checkHealth();

  @GetMapping("/getbranchcustomers/{id}")
  ResponseEntity<Set<Customer>> getBranchCustomers(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @PostMapping("/addcustomertobranch/{customerid}/{branchid}")
  ResponseEntity<Branch> addCustomerToBranch(@PathVariable("customerid") Long customerId, @PathVariable("branchid") Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  @DeleteMapping("/deletecustomerfrombranch/{customerid}/{branchid}")
  ResponseEntity<Set<Customer>> deleteCustomerFromBranch(@PathVariable("customerid") Long customerId, @PathVariable("branchid") Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

}
