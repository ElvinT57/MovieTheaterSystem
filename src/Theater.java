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
	
	public boolean assignSeats(Customer customer)
	{
		int unSeatedCustomers = customer.getSizeOfParty();
		boolean seatSpace = false;
		
		while (unSeatedCustomers > 0)
		{
			seatSpace = findSeat(customer);
			unSeatedCustomers --;
		}
		return seatSpace;
	}
	
	private boolean findSeat(Customer customer)
	{
		boolean seatSpace = false;
		int unSeatedCustomers = customer.getSizeOfParty();
		
		if (unSeatedCustomers <= numVacantSeats)
		{
			fillSeat(customer);
			seatSpace = true;
		}
		else
		{
			seatSpace = false;
		}
		
		return seatSpace;
	}
	

	// We want a "snake" fill
	private void fillSeat(Customer customer)
	{	
		//boolean seatSpace = false;
		
		for (int i = currentRow; i < seatChart.length; i ++)
		{
			//CB even number rows gget filled "left to right"
			if (i % 2 == 0){
				fillLeftToRight(customer, i);
			}
			
			//CB odd numbered rows get filled "right to left"
			else {
				fillRightToLeft(customer, i);
			}
		}	
	}
	
	private void fillLeftToRight(Customer customer, int row)
	{
		for (int c = currentCol; c < seatChart[0].length; c++){
			if (seatChart[row][c] == null){
				seatChart [row][c] = customer.getName();
			
				currentRow = row;
				currentCol = c;
			
				numVacantSeats--;
			}
			else
			{
				System.out.println("SEATS TAKEN @ (" 
						+ row + " , " + c + ")" );
				c = seatChart[0].length; 
			}
		}
	}
	
	private void fillRightToLeft(Customer customer, int row)
	{
		for (int c = currentCol; c >= 0; c--){
			if (seatChart[row][c] == null){
				seatChart [row][c] = customer.getName();
			
				currentRow = row;
				currentCol = c;
			
				numVacantSeats--;
			}
			else
			{
				System.out.println("SEATS TAKEN @ (" 
						+ row + " , " + c + ")" );
				c = seatChart[0].length; 
			}
		}
	}
}
