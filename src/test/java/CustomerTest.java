import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerTest {
    private Customer customer;
    private Rental rental;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Jenny");
    }

    @Test
    public void testRentalRegularAndRentDaysLessThanTwo() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.REGULAR);
        rental = new Rental(movie, 2);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points"));
    }

    @Test
    public void testRentalRegularAndRentDaysGTTwo() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.REGULAR);
        rental = new Rental(movie, 4);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t5.0\n" +
                        "Amount owed is 5.0\n" +
                        "You earned 1 frequent renter points"));

    }

    @Test
    public void testRentalNewRelease() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.NEW_RELEASE);
        rental = new Rental(movie, 1);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points"));
    }

    @Test
    public void testRentalNewReleaseAndRentForOverOneDay() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.NEW_RELEASE);
        rental = new Rental(movie, 3);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t9.0\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter points"));
    }

    @Test
    public void testRentChildrenAndRentDaysLessThanThress() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.CHILDRENS);
        rental = new Rental(movie, 3);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points"));

    }

    @Test
    public void testRentChildrenAndRentDaysGreaterThanThress() throws Exception {
        Movie movie = new Movie("Gone with the wind", Movie.CHILDRENS);
        rental = new Rental(movie, 5);
        customer.addRental(rental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t4.5\n" +
                        "Amount owed is 4.5\n" +
                        "You earned 1 frequent renter points"));
    }

    @Test
    public void testMultiMovies() throws Exception {
        Movie regularMovie = new Movie("Gone with the wind", Movie.REGULAR);
        rental = new Rental(regularMovie, 2);
        customer.addRental(rental);

        Movie newReleaseMovie = new Movie("West World", Movie.NEW_RELEASE);
        Rental newReleaseRental = new Rental(newReleaseMovie, 3);
        customer.addRental(newReleaseRental);

        Movie ChildrenMovie = new Movie("Go away Mr wolf", Movie.CHILDRENS);
        Rental childrenRental = new Rental(ChildrenMovie, 5);
        customer.addRental(childrenRental);

        assertThat(customer.statement(),
                is("Rental Record for Jenny\n" +
                        "\tGone with the wind\t2.0\n" +
                        "\tWest World\t9.0\n" +
                        "\tGo away Mr wolf\t4.5\n" +
                        "Amount owed is 15.5\n" +
                        "You earned 4 frequent renter points"));
    }
}