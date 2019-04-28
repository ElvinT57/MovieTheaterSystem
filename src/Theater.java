
/**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 4/20/19
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * <p>@author: Christina Bannon, Elvin Torres </p>
 * <p>@version: 4.20.2019</p>
 * 
 * 
 * <h1> Theater </h1>
 * 
 * <ul>
 * <li>Simulates a single theater 
 *   playing a single show. </li>
 * <li>Holds manipulates & returns 
 *   data regarding: 
 *   <ol>
 *   <li> Seating - whether a party 
 *      can be seated or not, and 
 *      where they will sit. </li>
 *   <li> Name of movie being played. </li>
 *   <li> Tickets sold for the show</li>
 *   <li> Whether a customer of the 
 *      same name is already seated 
 *      in the Theater or not</li>
 *      </li>
 *   </ol>
 *  </ul>
 */

public class Theater 
{

	private String []    seatChart;
	private String       movieName;  
	private int     numVacantSeats;
	private int 	   ticketsSold;
	private int               cols;

	/**
	 * Theater constructor initializes 
	 * private variables to the user input.
	 * 
	 * @param rows 
	 * @param cols
	 * @param movieName
	 */
	public Theater (int rows, int cols, 
			        String movieName)
	{
		seatChart = new String [rows * cols];
		this.movieName = movieName;
		numVacantSeats = rows * cols;
		this.cols   = cols;
		ticketsSold = 0;
	}

	/**
	 * getMovieName returns the user 
	 * input name of the movie
	 * 
	 * @return String
	 */
	public String getMovieName() 
	{
		return movieName;
	}
	
	/**
	 * getTicketsSold returns the
	 * accumulator of tickets sold for 
	 * a particular movie
	 * 
	 * @return int
	 */
	public int getTicketsSold()
	{
		return ticketsSold;
	}
	
    /**
     * Checks if there is enough seats for the
     * given size of party.
     *
     * @param partySize size of the party
     * @return t/f whether this theater has enough seats.
     */
    public boolean hasEnoughSeats(int partySize){
        return (numVacantSeats >= partySize);
    }
	
	/**
	 * accepts a String name, 
	 * checks the seatChart array
	 * for the name, and returns a
	 * boolean indicating whether the 
	 * name is present in the theater
	 * @param name
	 * @return
	 */
	public boolean hasName(String name)
	{
		boolean presence = false;
		
		name = name.trim();
		for (int index = 0; index 
				< seatChart.length; index++)
		{
			if (name.equals(seatChart[index])) {
				index = seatChart.length;
				presence = true;
			}
		}
		return presence;
	}
	
	/**
	 * assignSeats accepts a customer, 
	 * and if there are seats available for 
	 * the individuals in the customer's 
	 * party, 'seats' the customers by 
     * copying their party name into available
	 * seat elements until every party member
	 * has been seated
	 * 
	 * 
	 */
	public void assignSeats(Customer customer)
	{
			seatingHelper(customer);
			
			ticketsSold += 
					customer.getSizeOfParty();
			
			int seatIndex = 0;
			int unSeated = customer.getSizeOfParty();
			
			while ( unSeated > 0 ) {
				if ( seatChart[seatIndex] == null ) {
					
					seatChart[seatIndex] = 
							customer.getName();
					unSeated--;
					numVacantSeats--;
				}
				
				seatIndex = ++seatIndex % 
						seatChart.length;
			}

	}
	
	/**

	 * 
	 * @param Customer customer
	 */
	private void seatingHelper(Customer customer)
	{

	}
	
	/**
 	 * removeCustomer accepts a name,
 	 * searches the seatChart array for the 
	 * name, sets every elements of the
	 * array equal to the string to null
	 * and returns a boolean that represents
	 * whether the name was present in 
	 * the array or not. 
	 * 
	 * @param String name
	 * @return boolean customerPresent
	 */
	public boolean removeCustomer(String name)
	{
		boolean customerPresent = false;
		
		//nested loops to remove a customer
		for (int index = 0; 
				index < seatChart.length; index++) {
			if (name.equals(seatChart[index])) {
				seatChart[index] = null;
				numVacantSeats++;
				customerPresent = true;
			}
		}
		
		return customerPresent;
	}
	
	/**
	 * isEmpty returns a boolean to 
	 * describe whether the theater is 
	 * empty or not
	 * 
	 * @return boolean
	 */
	public boolean isEmpty(){
        return (numVacantSeats 
        		== seatChart.length);
    }

	/**
	 * toString creates a String that 
	 * represents the seatChart array 
	 * in the form of a row x col
     * seating chart
	 * @return String
	 */
	@Override
	public String toString()
	{
		StringBuilder string = 
				new StringBuilder();
		
		for (int row = 0; 
				row < (seatChart.length / cols); 
					row++)
		{
			for (int col = 0; col < cols; col++)
			{	
				if (seatChart[ (cols * row) + col] == null) {
					string.append("Row " 
							+ (row + 1) + 
								" seat " 
							+ (col + 1) + 
								" is free.");
				}
				else {
					string.append("Row " 
							+ (row + 1) + 
							    " seat " 
							+ (col + 1) + 
							    " is used by ");
					string.append(
						seatChart[ (cols * row)
						           + col] + 
								"'s party. ");
				}			
				string.append("\n");
			}	
		}	
		return string.toString();
	}
}
