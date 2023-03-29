package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import java.time.Duration;
import java.util.Objects;
import java.util.Arrays;
import java.util.Collections;



import static com.jpmc.theater.constants.MovieConstants.*;

/**
 * Movie object with the title , duration, ticket price and discount code
 */
@Getter
@AllArgsConstructor
public class Movie {

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /**
     * calculateTicketPrice: Return the ticket price after applying the discount
     *
     * @param showing Details of showing
     * @return disounted ticket price
     */
    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }


    /**
     * getDiscount: Calculate the ticket discount based on the show sequence
     *
     * @param showing movie showing
     * @return Discount to be applied
     */
    private double getDiscount(Showing showing) {
        double specialDiscount = 0;
        double sequenceDiscount = 0;
        double hourDiscount = 0;


        LocalDateTime movieStartTime = showing.getShowStartTime();

        // discount based on movie start time
        if (movieStartTime.getHour() >= AM_ELEVEN && movieStartTime.getHour() <= PM_FOUR) {
            hourDiscount = ticketPrice * TWENTY_FIVE_PERCENT;

        }

        // discount based on the special code
        if (specialCode == MOVIE_CODE_SPECIAL) {
            specialDiscount = ticketPrice * TWENTY_PERCENT;
        }

        // discount based on the show sequence
        int showSequence = showing.getSequenceOfTheDay();

        if (showSequence == FIRST_SHOW_OF_DAY) {
            sequenceDiscount = THREE_DOLLARS;
        } else if (showSequence == SECOND_SHOW_OF_DAY) {
            sequenceDiscount = TWO_DOLLARS;
        } else if (showSequence == SEVENTH_SHOW_OF_DAY) {
            sequenceDiscount = ONE_DOLLAR;
        }

       return Collections.max(Arrays.asList(hourDiscount, sequenceDiscount, specialDiscount));
    }

    /**
     * equals: equals overridden compare two Movie Objects
     *
     * @param o object to be compared with current movie object
     * @return boolean indicating if they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    /**
     * hashCode: hashCode method overridden for the class
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}