
/**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update:
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * <p> @author: Christina Bannon, Elvin Torres </p>
 * <p>  @version: 04.20.19 </p>
 * 
 * <h1> Customer Class</h>
 * 
 * Customer class holds and returns information
 * regarding a party of customers.
 * Information held includes
 * <ul> 
 * <li> Name of the party </li>
 * <li> Name of the movie the 
 * customer is seeing </li>
 * <li> Size of the party </li>
 * <li> If there is an underAge 
 * member of the party </li>
 * </ul>
 *
 */

public class Customer {
    //Data fields for this class
    private String name;
    private String movieName;
    private int partySize;
    private boolean underAge;

    /**
     * Constructor initializes private attributes
     * 
     * @param name
     * @param movieName
     * @param partySize
     * @param underAge
     */
    public Customer (String name, String movieName,
    		int partySize, boolean underAge)
    {
    	this.name = name;
    	this.movieName = movieName;
    	this.partySize = partySize;
    	this.underAge = underAge;
    }
    
    /**
     * getName returns the name associated
     *  with the party
     *  
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * getMovieName returns the name of the 
     * movie the party plans to attend
     * 
     * @return String movieName
     */
    public String getMovieName() {
        return movieName;
    }
    
    /**
     * getSizeOfParty returns an int 
     * representing how many members
     * of the party exist
     * 
     * @return int partySize
     */
    public int getSizeOfParty() {
        return partySize;
    }

    /**
     * isUnderAge returns a boolean (true)
     * if there is an under age member of 
     * the party, and a false if there is not
     * 
     * @return boolean
     */
    public boolean isUnderAge() {
        return underAge;
    }

    /**
     * toString() returns a sentence
     * stating the Customer name, 
     * party size and movie name. 
     */
    @Override
    public String toString() {
        return "Customer " + name + 
        		" part of " + partySize + 
        		" for " + movieName + " movie.";
    }
}
