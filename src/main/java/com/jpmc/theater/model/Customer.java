package com.jpmc.theater.model;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
/**
 * Customer with id and name
 */
public class Customer {

    private String name;
    private String id;

    /**
     * equals: Equals overridden to compare two Customer Objects
     *
     * @param o object to be compared with current customer object
     * @return boolean indicating if they are equal or not
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    /**
     * hashCode: hashCode method overriden for the class
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    /**
     * toString: toString  method overriden for the class
     *
     * @return Customer object printed as string
     */
    @Override
    public String toString() {
        return "name: " + name + " id: " + id;
    }
}