package com.softtech_ventures.customer_and_branch_crud.dao.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp(){
        customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Ibrahim");
    }

    @Test
    public void idShouldEqualTest(){
        assertEquals("1", customer.getId().toString());
    }

    @Test
    public void fullNameShouldEqualTest(){
        assertEquals("Ibrahim", customer.getFullName());
    }

    @Test
    public void branchesShouldEmptyTest(){
        assertTrue(customer.getBranches().isEmpty());
    }

}
