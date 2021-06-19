package com.softtech_ventures.customer_and_branch_crud.service;

import com.softtech_ventures.customer_and_branch_crud.dao.model.Branch;
import com.softtech_ventures.customer_and_branch_crud.dao.model.Customer;
import com.softtech_ventures.customer_and_branch_crud.dao.repository.contract.BranchRepository;
import com.softtech_ventures.customer_and_branch_crud.dao.repository.contract.CustomerRepository;
import com.softtech_ventures.customer_and_branch_crud.service.contract.CustomerService;
import com.softtech_ventures.customer_and_branch_crud.service.contract.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    BranchRepository branchRepository;

    private CustomerService customerService;

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
        customerService = new CustomerServiceImpl(customerRepository, branchRepository);
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
    public void getCustomerByIdTest() throws Exception {

        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);

        assertEquals(customer, customerService.getCustomerById(1L));

    }

    @Test
    public void getCustomerListTest() throws Exception {

        when(customerRepository.findAll()).thenReturn(customerList);

        assertEquals(customerList, customerService.getCustomerList());

    }

    @Test
    public void addCustomerTest() throws Exception {

        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.addCustomer(customer));

    }

    @Test
    public void updateCustomerTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.updateCustomerById(1L, customer));

    }

    @Test
    public void deleteCustomerTest() throws Exception {

        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomerById(1L);

    }

    @Test
    public void getCustomerBranchesTest() throws Exception {

        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);

        assertTrue(customerService.getCustomerBranches(1L).isEmpty());

    }

    @Test
    public void getBranchCustomersTest() throws Exception {

        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        assertTrue(customerService.getBranchCustomers(1L).isEmpty());

    }

    @Test
    public void addBranchToCustomerTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.addBranchToCustomer(1L, 1L));

    }

    @Test
    public void addCustomerToBranchTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(branchRepository.save(branch)).thenReturn(branch);

        assertEquals(branch, customerService.addCustomerToBranch(1L, 1L));

    }

    @Test
    public void deleteBranchFromCustomerTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(customerRepository.save(customer)).thenReturn(customer);

        assertEquals(customer, customerService.deleteBranchFromCustomer(1L, 1L));

    }

    @Test
    public void deleteCustomerFromBranchTest() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(optionalCustomer);
        when(branchRepository.findById(1L)).thenReturn(optionalBranch);

        when(branchRepository.save(branch)).thenReturn(branch);

        assertEquals(branch, customerService.addCustomerToBranch(1L, 1L));

    }


}
