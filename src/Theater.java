/*
 * The Theater class simulates a 
 * movie theater's seating chart. 
 * 
 * 
 * Seating chart is functioning, can add
 */

public class Theater 
{
	private String       movieName;  
	private int         currentRow;     //CB keep track of the last        
	private int         currentCol;     // r/c we left off at
	private int     numVacantSeats;
	private String [] [] seatChart;

	public Theater (int rows, int cols, String movieName)
	{
		this.movieName = movieName;
		currentRow = 0;
		currentCol = 0;

		numVacantSeats = rows * cols;

		seatChart = new String [rows][cols];
	}

	public String getMovieName() {
		return movieName;
	}

	public int getNumVacantSeats()
	{
		return numVacantSeats;
	}

	private boolean seatCapacity(int sizeOfParty)
	{
		boolean vacancy = true;
		if ( sizeOfParty > numVacantSeats)
		{
			vacancy = false;
		}
		return vacancy;
	}

	//CB working - tried to break it and couldnt. 
	public boolean assignSeats(Customer customer)
	{
		int unseated = customer.getSizeOfParty();
		
		//CB first test for if the customer can be sat or not!
		boolean successfulSeating = seatCapacity(unseated);
		
		while(successfulSeating & (unseated > 0)) {

			if (currentRow % 2 == 0) {

				successfulSeating = fillRightToLeft(customer);

			} else {

				successfulSeating = fillLeftToRight(customer);
			}
			
			if (successfulSeating)
			{
				unseated --;
				numVacantSeats--;
			}
		}
		return successfulSeating;
	}

	/*
	 * 
	 * fills a row from right to left (surprise)
	 * returns a boolean to represent if 
	 * the seats were successfully filled
	 * adjacently, or if something went wrong. 
	 * 
	 *
	 */
	private boolean fillRightToLeft(Customer customer)
	{
		boolean seatingFulfilled = false;
		
		//CB if the seats can't be adjacent, the 
		// arrangement is not fulfilled!
		if (currentRow == seatChart.length){
			updateCurrents();					
		}
		
		//There should be acheck as to if its null, maybe ere
		if (seatChart[currentRow][currentCol] == null){
			seatChart[currentRow] [currentCol] = customer.getName();
			
			seatingFulfilled = true;

			currentCol++;
			
			if (currentCol == seatChart[0].length ) {
				
				currentCol--;
				currentRow++;
			}
		}
	
		if (!seatingFulfilled){
			removeCustomer(customer.getName());
		}	
		return seatingFulfilled;
	}

	private boolean fillLeftToRight(Customer customer)
	{
		boolean seatingFulfilled = false;
		
		if (currentRow == seatChart.length){
			updateCurrents();					
		}
		
		if(seatChart[currentRow][currentCol] == null){
			seatChart[currentRow] [currentCol] = customer.getName();		
			seatingFulfilled = true;

			currentCol--;
			
			if (currentCol < 0) {
				
				currentCol++;
				currentRow = (++currentRow) % seatChart.length;
			}
			
			
		}
		
		if (!seatingFulfilled) {
			removeCustomer(customer.getName());
		}
		
		return seatingFulfilled;
	}
	
	
	/**
	 * Removes the party of the given name.
	 * @param name Name of the party
	 * @return true or false whether the customer is in the theater and has been removed
	 *
	 * UNTESTED
	 */
	public boolean removeCustomer(String name)
	{
		boolean customerPresent = false;
		
		//nested loops to remove a customer
		for (int row = 0; row < seatChart.length; row++)
		{
			for (int col = 0; col < seatChart[0].length; col++)
			{
				if (name.equals(seatChart[row][col]))
				{
					seatChart[row][col] = null;
					customerPresent = true;
				}
			}
		}
		updateCurrents();

		return customerPresent;
	}
	
	/*
	 * 
	 * Helper method for removeCustomers... Still thinking on 
	 * whether we may be better off removing the 'currents'
	 * and just always finding the next possible spot. 
	 * I'll do some quick math and update later? 
	 */
	private void updateCurrents()
	{
		for (int row = 0; row < seatChart.length; row++)
		{
			for (int col = 0; col < seatChart[0].length; col++)
			{
				if (seatChart[row][col] != null)
				{
					currentRow = row;
					currentCol = col; 
					
					col = seatChart[0].length;
					row = seatChart.length;
				}
			}
		}
	}


	public boolean isEmpty()
	{
		boolean isEmpty = true;

		if (numVacantSeats > 0)
		{
			isEmpty = false;
		}
		return isEmpty;
	}
	
	
	
	//In matrix format, just to better see what's happening
	public String showSeating()
	{
		StringBuilder string = new StringBuilder();
		
		for (int row = 0; row < seatChart.length; row++)
		{
			for (int col = 0; col < seatChart[0].length; col++)
			{
				if (seatChart[row][col] != null){
					string.append( seatChart[row][col] + "    ");
				}else {
					string.append("(" + row + ","+ col + ")");
				}
			}
			
			string.append("\n");
		}
		
		return string.toString();
	}
}
