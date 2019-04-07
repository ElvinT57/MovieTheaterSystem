/*
 * 
 * The Theater class simulates a 
 * movie theater's seating chart. 
 */
public class Theater 
{
	private String movieName;
	private int rows;
	private int cols;
	private int numVacantSeats;
	private String [] [] seatChart;
	
	public Theater (int rows, int cols, String movieName)
	{
		this.movieName = movieName;
		this.rows = rows;
		this.cols = cols;
		
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
		int unSeatedCustomers = customer.getSize();
		
		while (unSeatedCustomers > 0)
		{
			int rowIndex = 0;
			int colIndex = 0;
			while ( index < cols)
			{
				if (seatChart[rowIndex] [colIndex] == null)
				{
					
				}
			}
		}
		
	}
	
	
	
	

	
}
