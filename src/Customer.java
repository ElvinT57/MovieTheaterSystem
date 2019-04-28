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
    private int partySize;
    private boolean underAge;

    public Customer (String name, String movieName,
    		int partySize, boolean underAge)
    {
    	this.name = name;
    	this.movieName = movieName;
    	this.partySize = partySize;
    	this.underAge = underAge;
    }
    public String getName() {
        return name;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getSizeOfParty() {
        return partySize;
    }

    public boolean isUnderAge() {
        return underAge;
    }

    @Override
    public String toString() {
        return "Customer " + name + " part of " + partySize + " for " + movieName + " movie.";
    }
}
