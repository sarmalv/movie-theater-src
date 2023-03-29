package com.jpmc.theater;

import com.jpmc.theater.exceptions.MovieException;

import static com.jpmc.theater.constants.MovieConstants.MOVIE_RESERVE_ERROR;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    private static Logger log = LoggerFactory.getLogger(Theater.class);
    private List<Showing> schedule;

    /**
     * constructor
     */
    public Theater() {
        LocalDate todaysDate = LocalDate.now();
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

        schedule = List.of(
                new Showing(turningRed, 1, LocalDateTime.of(todaysDate, LocalTime.of(9, 0))),
                new Showing(spiderMan, 2, LocalDateTime.of(todaysDate, LocalTime.of(11, 0))),
                new Showing(theBatMan, 3, LocalDateTime.of(todaysDate, LocalTime.of(12, 50))),
                new Showing(turningRed, 4, LocalDateTime.of(todaysDate, LocalTime.of(14, 30))),
                new Showing(spiderMan, 5, LocalDateTime.of(todaysDate, LocalTime.of(16, 10))),
                new Showing(theBatMan, 6, LocalDateTime.of(todaysDate, LocalTime.of(17, 50))),
                new Showing(turningRed, 7, LocalDateTime.of(todaysDate, LocalTime.of(19, 30))),
                new Showing(spiderMan, 8, LocalDateTime.of(todaysDate, LocalTime.of(21, 10))),
                new Showing(theBatMan, 9, LocalDateTime.of(todaysDate, LocalTime.of(23, 0)))
        );
    }

    /**
     * reserve: Reserve the tickets for a showing
     *
     * @param customer       Customer making reservation
     * @param sequence       show Sequence of the day
     * @param howManyTickets number of tickers
     * @return Reservation with all applicable discounts
     * @throws MovieException Movie exception if issues reserving
     */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) throws MovieException {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            log.error("not able to find any showing for given sequence " + sequence + " - " + ex.getMessage());
            throw new MovieException(MOVIE_RESERVE_ERROR, "not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    /**
     * printSchedule: print schedules of all the shows for a day
     */
    public void printSchedule() {
        //printing in text format
        schedule.forEach(s ->
                log.info(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
      // printing in json format
        schedule.forEach(s -> {
            Gson gson = new Gson();
            log.info(gson.toJson(s));
        });

    }

    /**
     * humanReadableFormat: Movie Minutes converted to hours and minutes format
     *
     * @param duration total movie dutaion in minutes
     * @return string in hours and minutes format
     */
    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    /**
     * handlePlural: adding (s) postfix to handle plural correctly
     *
     * @param value value to check for plural
     * @return empty string or s
     */

    private String handlePlural(long value) {
        if (value <= 1) {
            return "";
        } else {
            return "s";
        }
    }

}
