
/**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 4/19/19
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * @author: Christina Bannon, Elvin Torres 
 * @version: 4.19.2019
 */

/**
 * 
 * <h1> Theater </h1>
 * 
 * - Simulates a single theater 
 *   playing a single show. 
 * - Holds manipulates & returns 
 *   data regarding: 
 *   1) Seating - whether a party 
 *      can be seated or not, and 
 *      where they will sit. 
 *   2) Name of movie being played. 
 *   3) Tickets sold for the show
 *   4) Whether a customer of the 
 *      same name is already seated 
 *      in the Theater or not
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
		
		for (int index = 0; index < seatChart.length; index++)
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
	 * passing the customer object to the 
	 * seatingHelper
	 * 
	 * @return boolean - success or failure 
	 *    of the seating attempt
	 */
	public boolean assignSeats(Customer customer)
	{
		boolean successfulSeating;
		
		if ( customer.getSizeOfParty() < numVacantSeats )
		{
			seatingHelper(customer);
			
			successfulSeating = true;
			ticketsSold += customer.getSizeOfParty();
			numVacantSeats -=customer.getSizeOfParty();
		} 
		else 
		{
			successfulSeating = false;
		}
		
		return successfulSeating;	
	}
	
	/**
	 * seatingHelper accepts a customer, 
	 * and copies their name into available
	 * seat elements until every party member
	 * has been seated
	 * 
	 * @param Customer customer
	 */
	private void seatingHelper(Customer customer)
	{
		int seatIndex = 0;
		int unSeated = customer.getSizeOfParty();
		
		while ( unSeated > 0 ) {
			if ( seatChart[seatIndex] == null ) {
				
				seatChart[seatIndex] = customer.getName();
				unSeated--;
			}
			
			seatIndex = ++seatIndex % seatChart.length;
		}
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
		for (int index = 0; index < seatChart.length; index++) {
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
        return (numVacantSeats ==  seatChart.length);
    }

	/**
	 * showSeating creates a String that 
	 * represents the setChart array in 
	 * a 'snake' formation, to represent the 
	 * theater, & returns that String. 
	 * 
	 * @return String
	 */
	public String showSeating()
	{
		StringBuilder string = new StringBuilder();
		
		for (int row = 0; row < (seatChart.length / cols); row++)
		{
			for (int col = 0; col < cols; col++)
			{	
				
				//////////////////////////////////////////
				//put this whole chunk in another method?
				//if it's an even numbered row, again regular append
				if (row % 2 == 0) {
					if (seatChart[ ((cols - 1) * row) + col] == null) {
						string.append(" (" + row + "," + col + ")");
					}
					else {
						string.append("   ");
						string.append(seatChart[((cols - 1 ) * row) + col]);
						string.append("  ");
					}
				}
				
				//if it's an odd numbered row, list them the other way!
				else {
					if (seatChart[ ((cols - 1) * row) 
					              + ((cols - 1) - col)] == null) 
					{
						string.append(" (" + row + "," + col + ")");
					}
					else {
						string.append("   ");
						string.append(seatChart[((cols - 1) * row)
							+ ((cols -1 ) - col)]);
						string.append("  ");
						
					}
				}
			}	
			string.append("\n");
		}	
		return string.toString();
	}
}
