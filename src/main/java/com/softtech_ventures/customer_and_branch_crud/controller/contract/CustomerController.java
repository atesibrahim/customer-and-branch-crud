package com.softtech_ventures.customer_and_branch_crud.controller.contract;

import com.softtech_ventures.customer_and_branch_crud.dao.model.Branch;
import com.softtech_ventures.customer_and_branch_crud.dao.model.Customer;
import java.util.List;
import java.util.Set;

import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import com.softtech_ventures.customer_and_branch_crud.utils.exception.ResourceNotModifiedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/customer")
public interface CustomerController {

  @GetMapping("/health")
  ResponseEntity<String> checkHealth();

  @GetMapping("/getcustomer/{id}")
  ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @GetMapping("/getcustomerlist")
  ResponseEntity<List<Customer>> getCustomerList();

  @PutMapping("/updatecustomer/{id}")
  ResponseEntity<Customer> updateCustomerById(@PathVariable("id") Long id, @RequestBody Customer customer) throws ResourceNotFoundException, ResourceNotModifiedException;

  @PostMapping("/addcustomer")
  ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws ResourceNotImplementedException;

  @DeleteMapping("/deletecustomer/{id}")
  ResponseEntity<Boolean> deleteCustomerById(@PathVariable("id") Long id) throws ResourceNotImplementedException;

  @GetMapping("/getcustomerbranches/{id}")
  ResponseEntity<Set<Branch>> getCustomerBranches(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @GetMapping("/getbranchcustomers/{id}")
  ResponseEntity<Set<Customer>> getBranchCustomers(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @PostMapping("/addcustomertobranch/{customerid}/{branchid}")
  ResponseEntity<Branch> addCustomerToBranch(@PathVariable("customerid") Long customerId, @PathVariable("branchid") Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  @DeleteMapping("/deletecustomerfrombranch/{customerid}/{branchid}")
  ResponseEntity<Branch> deleteCustomerFromBranch(@PathVariable("customerid") Long customerId, @PathVariable("branchid") Long branchId) throws ResourceNotFoundException, ResourceNotImplementedException;

  @PostMapping("/addbranchtocustomer/{branchid}/{customerid}")
  ResponseEntity<Customer> addBranchToCustomer(@PathVariable("branchid") Long branchId, @PathVariable("customerid") Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;

  @DeleteMapping("/deletebranchfromcustomer/{branchid}/{customerid}")
  ResponseEntity<Customer> deleteBranchFromCustomer(@PathVariable("branchid") Long branchId, @PathVariable("customerid") Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;

}
