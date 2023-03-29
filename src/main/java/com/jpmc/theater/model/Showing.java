package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Movie object showing the movie details, the show sequence for the day,
 *  start time and movie cost
 */
@AllArgsConstructor
@Getter
public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    /**
     * getMovieFee: Get the Movie fee consider the discount ticket price
     *
     * @return movie fee
     */
    public double getMovieFee() {
        return movie.calculateTicketPrice(this);
    }

    /**
     * getSequenceOfTheDay: Get the show  sequence number for the day
     *
     * @return show sequence number
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

}
