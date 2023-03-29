package com.jpmc.theater;

import com.jpmc.theater.exceptions.MovieException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.Duration;

import static com.jpmc.theater.constants.MovieConstants.MOVIE_RESERVE_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TheaterTests {
    @Mock
    private Customer customer;

    private Theater theater;

    @Before
    public void setUp() {
        theater = Mockito.spy(new Theater());
    }

    @Test
    public void testTotalFeeForCustomer() throws MovieException {
        Reservation reservation = theater.reserve(customer, 2, 4);
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    public void testPrintMovieSchedule() {
        theater.printSchedule();
        Mockito.verify(theater, Mockito.times(3)).humanReadableFormat(Duration.ofMinutes(85));
        Mockito.verify(theater, Mockito.times(3)).humanReadableFormat(Duration.ofMinutes(90));
        Mockito.verify(theater, Mockito.times(3)).humanReadableFormat(Duration.ofMinutes(95));
    }

    @Test(expected = MovieException.class)
     public void testReserveWithWrongShowSequence() {
        Reservation reservation = theater.reserve(customer, 15, 4);
    }

    @Test
    public void testReserveErrorCodeWithWrongShowSequence() {
        try {
            Reservation reservation = theater.reserve(customer, 15, 4);
        }catch(MovieException e){
            assertEquals(MOVIE_RESERVE_ERROR, e.getErrorCode());
        }
    }


}
