package com.stech_vent.customer_and_branch_crud.controller;

import com.stech_vent.customer_and_branch_crud.controller.branch.BranchControllerImpl;
import com.stech_vent.customer_and_branch_crud.controller.branch.contract.BranchController;
import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.service.branch.contract.BranchService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BranchControllerTest {

    @Mock
    private BranchService service;

    private BranchController branchController;

    private ResponseEntity<Branch> branchResponseEntity;

    private ResponseEntity<Set<Customer>> customersResponseEntity;

    private Customer customer;


    private Branch branch;

    private Set<Customer> customers;

    @Before
    public void setUp(){
        service = mock(BranchService.class);
        branchController = new BranchControllerImpl(service);
        String name = "Ates";
        customer = new Customer();
        customer.setFullName("Ibrahim");
        customer.setId(1L);
        customers= new HashSet<>();
        customers.add(customer);
        customersResponseEntity = new ResponseEntity<>(customers, HttpStatus.OK);

        branch = new Branch();
        branch.setId(1L);
        branch.setName("Kadıkoy");

        branchResponseEntity = new ResponseEntity<>(branch, HttpStatus.OK);

    }

    @Test
    public void getBranchCustomersTest() throws Exception {

        when(service.getBranchCustomers(1L)).thenReturn(customers);

        assertEquals(customersResponseEntity, branchController.getBranchCustomers(1L));

    }


    @Test
    public void addCustomerToBranchTest() throws Exception {

        when(service.addCustomerToBranch(1L, 1L)).thenReturn(branch);

        assertEquals(branchResponseEntity, branchController.addCustomerToBranch(1L, 1L));

    }


    @Test
    public void deleteCustomerFromBranchTest() throws Exception {

        when(service.deleteCustomerFromBranch(1L, 1L)).thenReturn(customers);

        assertEquals(customersResponseEntity, branchController.deleteCustomerFromBranch(1L, 1L));

    }


}
