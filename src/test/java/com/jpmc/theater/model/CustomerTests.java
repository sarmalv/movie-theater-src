package com.jpmc.theater.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerTests {

    @Test
    public void testEqualsWithNullObject() {
        //testing equals with real movie objects. else equals will be called on mock or spy objects
        Customer customer1 = new Customer("test", "12121");
        assertFalse(customer1.equals(null));
    }

    @Test
    public void testEqualsWithSameObjectReference() {
        Customer customer1 = new Customer("test", "12121");
        Customer customer2 = customer1;
        assertTrue(customer1.equals(customer2));
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        Customer customer1 = new Customer("test", "12121");
        assertFalse(customer1.equals("hello"));
    }

    @Test
    public void testEqualsWithObjectWithSameValue() {
        Customer customer1 = new Customer("test", "12121");
        Customer customer2 = new Customer("test", "12121");
        assertTrue(customer1.equals(customer2));
    }

    @Test
    public void testEqualsWithObjectWithDifferentValue() {
        Customer customer1 = new Customer("test", "12121");
        Customer customer2 = new Customer("test", "12131");
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void testToString() {
        Customer customer1 = new Customer("test", "12121");
        assertEquals("name: test id: 12121",customer1.toString());
    }

    @Test
    public void testHashCodeForEqualValues() {
        Customer customer1 = new Customer("test", "12121");
        Customer customer2 = new Customer("test", "12121");
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    @Test
    public void testHashCodeForDifferentValues() {
        Customer customer1 = new Customer("test", "12121");
        Customer customer2 = new Customer("test", "12321");
        assertNotEquals(customer1.hashCode(), customer2.hashCode());
    }


}
