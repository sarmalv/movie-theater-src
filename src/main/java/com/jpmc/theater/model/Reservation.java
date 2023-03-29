package com.jpmc.theater.model;

import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * Reservation Object representing customer , show and the number of people going
 */

@AllArgsConstructor
public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    /**
     * totalFee: Returns the totol fee for reservation based on number of people booked
     *
     * @return total fee
     */
    public double totalFee() {
        return showing.getMovieFee() * audienceCount;
    }

    /**
     * equals: equals method overwritten to compare current reservation instance with other object
     *
     * @param o object instance
     * @return boolean indicating whether the object are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Reservation)) return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(customer, reservation.customer) && Objects.equals(showing, reservation.showing) &&
                audienceCount == reservation.audienceCount;
    }
}