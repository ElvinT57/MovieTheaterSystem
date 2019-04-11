/*
 * The Theater class simulates a 
 * movie theater's seating chart. 
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

	private void seatCapacity(Customer customer)
	{
		if (customer.getSizeOfParty() > numVacantSeats)
		{
			// Throw a big old exception!
		}
	}

	//CB this method just keeps growing the more that I think about it. 
	//CB might break it into a control method and some private helpers tomorrow. 
	public boolean assignSeats(Customer customer)
	{
		//CB will throw a big ol exception yalllllll
		seatCapacity(customer);

		int unseated = customer.getSizeOfParty();

		while (unseated > 0) {

			if (currentRow % 2 == 0) {

				fillRightToLeft(customer);

			} else {

				fillLeftToRight(customer);
			}
			unseated --;
			numVacantSeats--;
		}
		return true;
	}

	private void fillRightToLeft(Customer customer)
	{
		currentCol = (++currentCol) % seatChart[0].length;
		//There should be acheck as to if its null, maybe ere
		if (seatChart[currentRow][currentCol] == null)
		{
			seatChart[currentRow] [currentCol] = customer.getName();

			if (currentCol == ( seatChart[0].length - 1)) {
				currentRow = (++currentRow) % seatChart.length;
			}
		}
		else
		{
			//CB throw a seats taken exception????
		}
	}

	private void fillLeftToRight(Customer customer)
	{
		currentCol = (--currentCol) % seatChart[0].length;

		if(seatChart[currentRow][currentCol] == null)
		{
			seatChart[currentRow] [currentCol] = customer.getName();

			if (currentCol == 0) {
				currentRow = (++currentRow) % seatChart.length;
			}
		}
		else
		{
			//an exception could be good here too! just a free throw 
		}
	}
	/**
	 * Removes the party of the given name.
	 * @param name Name of the party
	 * @return true or false whether the customer is in the theater and has been removed
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
	 * I'll do some quick math and update later. 
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


	public boolean isEmpty(){
        return (numVacantSeats == (seatChart.length * seatChart[0].length));
    }
}

