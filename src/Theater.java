**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * @author: Christina Bannon, Elvin Torres 
 * @version: 
 *
 * The Theater class simulates a 
 * movie theater's seating chart. 
 */
public class Theater 
{
	private String  movieName;
	private int     rows;
	private int     cols;
	private int     currentRow;
	private int     currentCol;
	private int     numVacantSeats;
	
	private String [] [] seatChart;
	
	public Theater (int rows, int cols, String movieName)
	{
		this.movieName = movieName;
		this.rows = rows;
		this.cols = cols;
		currentRow = currentCol = 0;
		
		numVacantSeats = this.rows * this.cols;
		
		seatChart = new String [rows][cols];
	}

	public String getMovieName() {
		return movieName;
	}

	public int getRows() {
		return rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
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
	
	private void fillSeat(Customer customer)
	{	
		//boolean seatSpace = false;
		
		for (int i = currentRow; i < rows; i++)
		{
			for (int j = currentCol; j < cols; i++)
			{
				if (seatChart[i][j] == null)
				{
					seatChart [i][j] = customer.getName();
					
					currentRow = i;
					currentCol = j;
					
					numVacantSeats--;
					
					//seatSpace = true;
					
					i = rows;
					j = cols;
				}		
			}
		}	
	}
}
