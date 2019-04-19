/*
 * The Theater class simulates a 
 * movie theater's seating chart. 
 * 
 * Seating chart is functioning, can add
 */

public class Theater 
{
	private String []    seatChart;
	private String       movieName;  
	private int     numVacantSeats;
	private int 	   ticketsSold;
	private int cols;

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

	public int getNumVacantSeats()
	{
		return numVacantSeats;
	}

	public int getTicketsSold()
	{
		return ticketsSold;
	}
	
	public boolean assignSeats(Customer customer)
	{
		boolean successfulSeating;
		
		if ( customer.getSizeOfParty() < numVacantSeats )
		{
			seatingHelper(customer);
			successfulSeating = true;
			ticketsSold += customer.getSizeOfParty();
			
			System.out.println("FOUND ALLA DEES SEETZ");
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
				
				seatIndex = ++seatIndex % seatChart.length;
				
				unSeated--;
				numVacantSeats--;
			}
		}
	}
	
	/*
	 * 
	 * remove is functioning with issues:
	 * - after removing a bulk of customers, add 
	 * sometimes returns false depending on where the available
	 * seats are. I'm thinking I might have to do some refactoring, 
	 * and maybe use a loop w an increment to keep track of
	 * if in the entire 2d array there exists x number of seats in a row. 
	 * It will make for more comparisons, but it will be more correct.
	 */
	public boolean removeCustomer(String name)
	{
		boolean customerPresent = false;
		
		//nested loops to remove a customer
		for (int index = 0; index < seatChart.length; index++) {
			if (name.compareTo(seatChart[index]) == 0) {
				seatChart[index] = null;
				numVacantSeats++;
			}
		}
		return customerPresent;
	}
	
	/*
	 * Helper method for removeCustomers... Still thinking on 
	 * whether we may be better off removing the 'currents'
	 * and just always finding the next possible spot. 
	 * I'll do some quick math and update later. 
	 */
	public boolean isEmpty()
	{
		boolean isEmpty = true;

		if (numVacantSeats > 0)
		{
			isEmpty = false;
		}
		return isEmpty;
	}

	//slithery lil string
	public String showSeating()
	{
		StringBuilder string = new StringBuilder();
		
		for (int row = 0; row < (seatChart.length / cols); row++)
		{
			for (int col = 0; col < cols; col++)
			{	
				//if it's an even numbered row, again regular append
				if (row % 2 == 0) {
					if (seatChart[ ((cols - 1) * row) + col] == null) {
						string.append(" (" + row + "," + col + ")");
					}
					else {
						string.append("  ");
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
						string.append("  ");
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
