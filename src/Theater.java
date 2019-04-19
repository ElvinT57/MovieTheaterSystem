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
 * <h1> Theater Class </h1>
 * 
 * - Simulates a single theater playing a single show. 
 * - Holds and returns data regarding: 
 *   1) Seating - whether a party can be 
 *      accomodated or not, and where they will
 *      sit. 
 *   2) Name of movie being played. 
 *   3) Tickets sold for the show
 *   4) Whether 
 */


public class Theater 
{
	private String []    seatChart;
	private String       movieName;  
	private int     numVacantSeats;
	private int 	   ticketsSold;
	private int               cols;

	public Theater (int rows, int cols, String movieName)
	{
		seatChart = new String [rows * cols];
		this.movieName = movieName;
		numVacantSeats = rows * cols;
		this.cols   = cols;
		ticketsSold = 0;
	}

	public String getMovieName() 
	{
		return movieName;
	}
	
	public int getTicketsSold()
	{
		return ticketsSold;
	}
	
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
	
	public boolean assignSeats(Customer customer)
	{
		boolean successfulSeating;
		
		if ( customer.getSizeOfParty() < numVacantSeats )
		{
			seatingHelper(customer);
			
			successfulSeating = true;
			ticketsSold += customer.getSizeOfParty();
			numVacantSeats -= customer.getSizeOfParty();
		}
		else
		{
			successfulSeating = false;
		}
		
		return successfulSeating;	
	}
	
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
	
	/*
	 * 
	 * removeCustomer
	 * 
	 * accepts a String name, searches 
	 * the seatChart array for the 
	 * name, sets every elements of the
	 *  array equal to the string 
	 * to null and returns a boolean that 
	 * represents whether the name was 
	 * present in the array or not. 
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
	
	/*
	 * isEmpty
	 * 
	 * returns a boolean to describe whether the 
	 * theater is empty or not
	 */
	public boolean isEmpty(){
        return (numVacantSeats ==  seatChart.length);
    }

	/*
	 * 
	 * returns a String representation 
	 * of the movie theater!
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
