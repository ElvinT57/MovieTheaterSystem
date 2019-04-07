/**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update:
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * @author: Christina Bannon, Elvin Torres
 * @version:
 */
public class Customer {
    //Data fields for this class
    private String name;
    private String movieName;
    private int sizeOfParty;
    private boolean underAge;

    public String getName() {
        return name;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getSizeOfParty() {
        return sizeOfParty;
    }

    public boolean isUnderAge() {
        return underAge;
    }

    @Override
    public String toString() {
        return "Customer " + name + " part of " + sizeOfParty + " for " + movieName + " movie.";
    }
}
