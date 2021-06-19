package com.stech_vent.customer_and_branch_crud.dao.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BranchTest {

    private Branch branch;

    @Before
    public void setUp(){
        branch = new Branch();
        branch.setId(1L);
        branch.setName("Kadikoy");
    }

    @Test
    public void idShouldEqualTest(){
        assertEquals("1", branch.getId().toString());
    }

    @Test
    public void fullNameShouldEqualTest(){
        assertEquals("Kadikoy", branch.getName());
    }

    @Test
    public void branchesShouldEmptyTest(){
        assertTrue(branch.getCustomers().isEmpty());
    }

}
