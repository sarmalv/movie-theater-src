package com.jpmc.theater.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ReservationTests {


    @Mock
    private Customer customerMock;
    private Reservation reservationSpy;
    private Showing showing;

    @Before
    public void setUp() {
         showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now());
        reservationSpy = Mockito.spy(new Reservation(customerMock, showing, 3));
    }

    @Test
    public void testTotalFee() {
        assertTrue(reservationSpy.totalFee() == 28.5);
    }


    @Test
    public void testEqualsWithNullObject() {
        Customer customer = new Customer("hi","433");
        Reservation reservation = new Reservation(customer,showing, 10);
        //testing equals with real movie objects. else equals will be called on mock or spy objects
        assertFalse(reservation.equals(null));
    }

    @Test
    public void testEqualsWithSameObjectReference() {
        Customer customer = new Customer("hi","433");
        Reservation reservation1 = new Reservation(customer,showing, 10);
        Reservation reservation2 =reservation1;
        assertTrue(reservation1.equals(reservation2));
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        Customer customer = new Customer("hi","433");
        Reservation reservation1 = new Reservation(customer,showing, 10);
        assertFalse(reservation1.equals("hello"));
    }

    @Test
    public void testEqualsWithObjectWithSameValue() {
        Customer customer = new Customer("hi","433");
        Reservation reservation1 = new Reservation(customer,showing, 10);
        Reservation reservation2 = new Reservation(customer,showing, 10);
        assertTrue(reservation1.equals(reservation2));
    }

    @Test
    public void testEqualsWithObjectWithDifferentValue() {
        Customer customer = new Customer("hi","433");
        Reservation reservation1 = new Reservation(customer,showing, 10);
        Reservation reservation2 = new Reservation(customer,showing, 20);
        assertFalse(reservation1.equals(reservation2));
    }

}
