package com.jpmc.theater.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.jpmc.theater.constants.MovieConstants.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class MovieTests {

    private Movie movieMock;
    private Showing showingMock;

    @Before
    public void setUp() {
        movieMock = Mockito.spy(new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL));
        showingMock = Mockito.spy(new Showing(movieMock, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now())));
    }

    @Test
    public void testSpecialMovieWith20PercentDiscount() {
        assertEquals(10, movieMock.calculateTicketPrice(showingMock));
    }

    @Test
    public void testFirstShowDiscountWithSpecialCode() {
        Mockito.when(showingMock.getSequenceOfTheDay()).thenReturn(FIRST_SHOW_OF_DAY);
        assertEquals(9.5, movieMock.calculateTicketPrice(showingMock));
    }

    @Test
    public void testSecondShowDiscountWithOutSpecialCode() {
        Mockito.when(showingMock.getSequenceOfTheDay()).thenReturn(SECOND_SHOW_OF_DAY);
        movieMock = Mockito.spy(new Movie("test", Duration.ofMinutes(0), 12.5, 0));
        assertEquals(10.5, movieMock.calculateTicketPrice(showingMock));
    }

    @Test
    public void testSeventhShowDiscountWithOutSpecialCode() {
        Mockito.when(showingMock.getSequenceOfTheDay()).thenReturn(SEVENTH_SHOW_OF_DAY);
        movieMock = Mockito.spy(new Movie("test", Duration.ofMinutes(0), 12.5, 0));
        assertEquals(11.5, movieMock.calculateTicketPrice(showingMock));
    }

    @Test
    public void test11To4ShowDiscountWithOutSpecialCode() {
        showingMock = Mockito.spy(new Showing(movieMock, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))));
        Mockito.when(showingMock.getSequenceOfTheDay()).thenReturn(SECOND_SHOW_OF_DAY);
        movieMock = Mockito.spy(new Movie("test", Duration.ofMinutes(0), 12, 0));
        assertEquals(9, movieMock.calculateTicketPrice(showingMock));
    }


    @Test
    public void testGetBestOfDiscounts() {
        showingMock = Mockito.spy(new Showing(movieMock, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50))));
        Mockito.when(showingMock.getSequenceOfTheDay()).thenReturn(2);
        movieMock = Mockito.spy(new Movie("test", Duration.ofMinutes(0), 20, MOVIE_CODE_SPECIAL));
        assertEquals(15, movieMock.calculateTicketPrice(showingMock));
    }

    @Test
    public void testEqualsWithNullObject() {
        //testing equals with real movie objects. else equals will be called on mock or spy objects
        Movie movie1 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        assertFalse(movie1.equals(null));
    }

    @Test
    public void testEqualsWithSameObjectReference() {
        Movie movie1 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = movie1;
        assertTrue(movie1.equals(movie2));
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        Movie movie1 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        assertFalse(movie1.equals("hello"));
    }

    @Test
    public void testEqualsWithObjectWithSameValue() {
        Movie movie1 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        assertTrue(movie1.equals(movie2));
    }

    @Test
    public void testEqualsWithObjectWithDifferentPrices() {
        Movie movie1 = new Movie("test1", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test1", Duration.ofMinutes(0), 11.5, MOVIE_CODE_SPECIAL);
        assertFalse(movie1.equals(movie2));
    }

    @Test
    public void testEqualsWithObjectWithDifferentSpecialCodes() {
        Movie movie1 = new Movie("test1", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test1", Duration.ofMinutes(0), 12.5, 0);
        assertFalse(movie1.equals(movie2));
    }

    @Test
    public void testEqualsWithObjectWithDifferentMovieDurations() {
        Movie movie1 = new Movie("test1", Duration.ofMinutes(90), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test1", Duration.ofMinutes(85), 12.5, 0);
        assertFalse(movie1.equals(movie2));
    }

    @Test
    public void testHashCodeWithDifferentTitles() {
        Movie movie1 = new Movie("test1", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test2", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        assertNotEquals(movie1.hashCode(), movie2.hashCode());
    }


    @Test
    public void testHashCodeWithSameFieldValues() {
        Movie movie1 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        Movie movie2 = new Movie("test", Duration.ofMinutes(0), 12.5, MOVIE_CODE_SPECIAL);
        assertEquals(movie1.hashCode(), movie2.hashCode());
    }


}
