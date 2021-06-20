package com.stech_vent.customer_and_branch_crud.service.customer.contract.impl;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.BranchRepository;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.CustomerRepository;
import com.stech_vent.customer_and_branch_crud.service.customer.contract.CustomerService;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotFoundException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotImplementedException;
import com.stech_vent.customer_and_branch_crud.utils.exception.ResourceNotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


  private CustomerRepository customerRepository;

  private BranchRepository branchRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository) {
    this.customerRepository = customerRepository;
    this.branchRepository = branchRepository;
  }

  public Customer getCustomerById(Long id) throws ResourceNotFoundException {

    return customerRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Customer not found for this id :: " + id));

  }

  public List<Customer> getCustomerList() {
    return customerRepository.findAll();
  }

  public Customer updateCustomerById(Long id, Customer customer) throws ResourceNotModifiedException, ResourceNotFoundException {

    Customer customerReturn = getCustomerById(id);

    customerReturn.setFullName(customer.getFullName());

    try{
      return customerRepository.save(customerReturn);
    } catch (Exception exception){
      logger.info("Updating Customer error occured while updating customer error message:", exception.getMessage());
      throw  new ResourceNotModifiedException("Updating Customer error occured while updating customer:"+exception.getMessage());
    }

  }

  public Customer addCustomer(Customer customer) throws ResourceNotImplementedException {
    try{
      return customerRepository.save(customer);
    }
    catch (Exception exception){
      logger.info("Error occured while creating customer. error message:", exception.getMessage());
      throw  new ResourceNotImplementedException("Error occured while creating customer:" + exception.getMessage());
    }

  }

  public void deleteCustomerById(Long id) throws ResourceNotImplementedException{

    try{
      customerRepository.deleteById(id);
    }
    catch (Exception exception){
      logger.info("Error occured while deleting customer. error message:", exception.getMessage());
      throw  new ResourceNotImplementedException("Error occured while deleting customer:" + exception.getMessage());
    }

  }

  @Override
  public Set<Branch> getCustomerBranches(Long id) throws ResourceNotFoundException {
    return getCustomerById(id).getBranches();
  }

  @Override
  public Customer addBranchToCustomer(Long branchId, Long customerId)throws ResourceNotFoundException, ResourceNotImplementedException {
    Customer customer = getCustomerById(customerId);

    Branch branch = getBranchById(branchId);

    customer.getBranches().add(branch);

    try{
    return customerRepository.save(customer);
    }catch (Exception exception){
        logger.info("Error occured while delete customer from branch. error message:", exception.getMessage());
      throw new ResourceNotImplementedException("Error occured while delete customer from branch:" + exception.getMessage());
    }
  }

  @Override
  public Set<Branch>  deleteBranchFromCustomer(Long branchId, Long customerId) throws ResourceNotFoundException, ResourceNotImplementedException {
    Customer customer = getCustomerById(customerId);

    Branch branch = getBranchById(branchId);

    customer.getBranches().remove(branch);

  try{
    customerRepository.save(customer);
    return getCustomerBranches(customerId);
    }catch (Exception exception){
      logger.info("Error occured while delete customer from branch:. error message:", exception.getMessage());
      throw new ResourceNotImplementedException("Error occured while delete customer from branch:" + exception.getMessage());
    }
  }

  private Branch getBranchById(Long id) throws ResourceNotFoundException {
    return branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Branch not found for this id :: " + id));
  }
}
