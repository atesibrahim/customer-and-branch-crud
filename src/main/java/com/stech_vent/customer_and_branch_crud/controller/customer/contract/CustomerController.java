package com.stech_vent.customer_and_branch_crud.controller.customer.contract;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import java.util.List;
import java.util.Set;

import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotModifiedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/customers")
public interface CustomerController {

  @GetMapping("/health")
  ResponseEntity<String> checkHealth();

  @GetMapping("/{id}")
  ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @GetMapping
  ResponseEntity<List<Customer>> getCustomerList();

  @PutMapping("/{id}")
  ResponseEntity<Customer> updateCustomerById(@PathVariable("id") Long id, @RequestBody Customer customer) throws ResourceNotFoundException, ResourceNotModifiedException;

  @PostMapping
  ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws ResourceNotImplementedException;

  @DeleteMapping("/{id}")
  ResponseEntity<Boolean> deleteCustomerById(@PathVariable("id") Long id) throws ResourceNotImplementedException;

  @GetMapping("/getcustomerbranches/{id}")
  ResponseEntity<Set<Branch>> getCustomerBranches(@PathVariable("id") Long id) throws ResourceNotFoundException;

  @PostMapping("/addbranchtocustomer/{branchid}/{customerid}")
  ResponseEntity<Customer> addBranchToCustomer(@PathVariable("branchid") Long branchId, @PathVariable("customerid") Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;

  @DeleteMapping("/deletebranchfromcustomer/{branchid}/{customerid}")
  ResponseEntity<Set<Branch> > deleteBranchFromCustomer(@PathVariable("branchid") Long branchId, @PathVariable("customerid") Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException;

}
