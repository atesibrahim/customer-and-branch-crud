package com.stech_vent.customer_and_branch_crud.controller;

import com.stech_vent.customer_and_branch_crud.controller.contract.CustomerController;
import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.service.contract.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService service;

    private CustomerController customerController;

    private ResponseEntity<Customer> customerResponseEntity;

    private ResponseEntity<Branch> branchResponseEntity;

    private ResponseEntity<List<Customer>> customerListResp;

    private ResponseEntity<Boolean> booleanResponseEntity;

    private ResponseEntity<Set<Branch>> branchesResponseEntity;

    private ResponseEntity<Set<Customer>> customersResponseEntity;

    private Customer customer;

    private List<Customer> customerList = new ArrayList<>();

    private Branch branch;

    private Set<Branch> branches;

    private Set<Customer> customers;

    @Before
    public void setUp(){
        service = mock(CustomerService.class);
        customerController = new CustomerControllerImpl(service);
        String name = "Ates";
        customer = new Customer();
        customer.setFullName("Ibrahim");
        customer.setId(1L);
        customerList.add(customer);

        customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.OK);

        customerListResp = new ResponseEntity<>(customerList, HttpStatus.OK);

        booleanResponseEntity = new ResponseEntity<>(true, HttpStatus.OK);

        branch = new Branch();
        branch.setId(1L);
        branch.setName("Kadıkoy");

        branchResponseEntity = new ResponseEntity<>(branch, HttpStatus.OK);

        branches = new HashSet<>();
        branches.add(branch);
        branchesResponseEntity = new ResponseEntity<>(branches, HttpStatus.OK);

        customers= new HashSet<>();
        customers.add(customer);
        customersResponseEntity = new ResponseEntity<>(customers, HttpStatus.OK);

    }

    @Test
    public void getCustomerByIdTest() throws Exception {

        when(service.getCustomerById(1L)).thenReturn(customer);

        assertEquals(customerResponseEntity, customerController.getCustomerById(1L));

    }

    @Test
    public void getCustomerListTest() throws Exception {

        when(service.getCustomerList()).thenReturn(customerList);

        assertEquals(customerListResp, customerController.getCustomerList());

    }

    @Test
    public void addCustomerTest() throws Exception {

        when(service.addCustomer(customer)).thenReturn(customer);

        assertEquals(customerResponseEntity, customerController.addCustomer(customer));

    }

    @Test
    public void updateCustomerTest() throws Exception {

        when(service.updateCustomerById(1L, customer)).thenReturn(customer);

        assertEquals(customerResponseEntity, customerController.updateCustomerById(1L, customer));

    }

    @Test
    public void deleteCustomerTest() throws Exception {

        doNothing().when(service).deleteCustomerById(1L);

        assertEquals(booleanResponseEntity, customerController.deleteCustomerById(1L));

    }

    @Test
    public void getCustomerBranchesTest() throws Exception {

        when(service.getCustomerBranches(1L)).thenReturn(branches);

        assertEquals(branchesResponseEntity, customerController.getCustomerBranches(1L));

    }

    @Test
    public void getBranchCustomersTest() throws Exception {

        when(service.getBranchCustomers(1L)).thenReturn(customers);

        assertEquals(customersResponseEntity, customerController.getBranchCustomers(1L));

    }

    @Test
    public void addBranchToCustomerTest() throws Exception {

        when(service.addBranchToCustomer(1L, 1L)).thenReturn(customer);

        assertEquals(customerResponseEntity, customerController.addBranchToCustomer(1L, 1L));

    }

    @Test
    public void addCustomerToBranchTest() throws Exception {

        when(service.addCustomerToBranch(1L, 1L)).thenReturn(branch);

        assertEquals(branchResponseEntity, customerController.addCustomerToBranch(1L, 1L));

    }

    @Test
    public void deleteBranchFromCustomerTest() throws Exception {

        when(service.deleteBranchFromCustomer(1L, 1L)).thenReturn(customer);

        assertEquals(customerResponseEntity, customerController.deleteBranchFromCustomer(1L, 1L));

    }

    @Test
    public void deleteCustomerFromBranchTest() throws Exception {

        when(service.deleteCustomerFromBranch(1L, 1L)).thenReturn(branch);

        assertEquals(branchResponseEntity, customerController.deleteCustomerFromBranch(1L, 1L));

    }


}
