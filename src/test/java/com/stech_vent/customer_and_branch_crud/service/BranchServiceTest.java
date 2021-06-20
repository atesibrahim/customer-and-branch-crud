package com.stech_vent.customer_and_branch_crud.service;

import com.stech_vent.customer_and_branch_crud.dao.model.Branch;
import com.stech_vent.customer_and_branch_crud.dao.model.Customer;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.BranchRepository;
import com.stech_vent.customer_and_branch_crud.dao.repository.contract.CustomerRepository;
import com.stech_vent.customer_and_branch_crud.service.branch.contract.BranchService;
import com.stech_vent.customer_and_branch_crud.service.branch.contract.impl.BranchServiceImpl;
import com.stech_vent.customer_and_branch_crud.service.customer.contract.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BranchServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    BranchRepository branchRepository;

    private BranchService branchService;

    private Customer customer;

    private Branch branch;

    private List<Customer> customerList;

    private Set<Customer> customers;

    private Set<Branch> branches;

    private Optional<Customer> optionalCustomer;

    private Optional<Branch> optionalBranch;

    @Before
    public void setUp(){

        customerRepository = mock(CustomerRepository.class);
        branchRepository = mock(BranchRepository.class);
        branchService = new BranchServiceImpl(customerRepository, branchRepository);
        String name = "Ates";
        customer = new Customer();
        customer.setFullName("Ibrahim");
        customer.setId(1L);
        customerList = new ArrayList<>();
        customerList.add(customer);


        branch = new Branch();
        branch.setId(1L);
        branch.setName("Kadıkoy");

        branches = new HashSet<>();
        branches.add(branch);

        customers= new HashSet<>();
        customers.add(customer);

        optionalCustomer = Optional.empty();
        optionalCustomer = Optional.of(customer);

        optionalBranch= Optional.empty();
        optionalBranch = Optional.of(branch);
    }

    @Test
    public void getBranchCustomersTest() throws Exception {

        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        assertTrue(branchService.getBranchCustomers(1L).isEmpty());

    }


    @Test
    public void addCustomerToBranchTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(branchRepository.save(branch)).thenReturn(branch);

        assertEquals(branch, branchService.addCustomerToBranch(1L, 1L));

    }

    @Test
    public void deleteCustomerFromBranchTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(branchRepository.save(branch)).thenReturn(branch);

        assertEquals(branch, branchService.addCustomerToBranch(1L, 1L));

    }


}
