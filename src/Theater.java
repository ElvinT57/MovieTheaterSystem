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
	
	public boolean assignSeats(Customer customer)
	{
		//CB will throw a big ol exception yalllllll
		seatCapacity(customer);
		
		int unseated = customer.getSizeOfParty();
		
		while (unseated > 0) {
			
			if (currentRow % 2 == 0) {
				
				currentCol = (++currentCol) % seatChart[0].length;
				seatChart[currentRow] [currentCol] = customer.getName();
				
				if (currentCol == ( seatChart[0].length - 1)) {
					currentRow++;
				}
				
			} else {
				
				currentCol = (--currentCol) % seatChart[0].length;
				seatChart[currentRow] [currentCol] = customer.getName();
				
				if (currentCol == 0) {
					currentRow++;
				}
			}
			unseated --;
		}
		return true;
	}
/**
     * Removes the party of the given name.
     * @param name Name of the party
     * @return true or false whether the customer is in the theater and has been removed
     */

    public boolean removeCustomer(String name)
    {
    	for (int row = 0; row < ; row++)
    	{
    		for 
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
}

