
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheaterTester 
{
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException
	{
		Theater theater = getTheaterInfo();
		
		int menuSelection = makeMenuSelection();
		
		while (true){
			switch (menuSelection) {
				case 0: 
					seatCustomer(theater);
					break;
					
				case 1: 
					showSeatingChart(theater);
					break;
				
				case 2:
					removeCustomer(theater);
					break;
					
				case 3: 
					theater = getTheaterInfo();
					break;
					
				case 4: 
					getNumTickets(theater);
					break;
					
				case 5: 
					findYoMan(theater);
					break;
					
			}
			
			menuSelection = makeMenuSelection();
		}	
	}
	
	public static int makeMenuSelection()
			throws IOException
	{
		System.out.println("\n  Please select from "
						+ "the following menu: ");
		System.out.println("0. Input Customer info & seat customer\n"
						+ "1. Show Seating Chart\n"
						+ "2. Remove Customers\n"
						+ "3. Make a Theater\n"
						+ "4. Get the number of Tickets sold!\n"
						+ "5. Find out if your man is in the theater w some girl\n");
		int menuSelection = 
				Integer.parseInt(reader
					         	.readLine()
					        	.trim());
		return menuSelection;
	}
	
	//CB Option 0 helperrrrr
	private static Customer getCustomerInfo() throws IOException
	{
		System.out.println("Enter customer name: ");
		String name = reader.readLine().trim();
		
		System.out.println("Enter size of party: ");
		int sizeOfParty = Integer.parseInt(reader.readLine().trim());
		
		String movie = "movie";

		return new Customer(name, movie, sizeOfParty, true);

	}
	
	//CB option 0
	public static void seatCustomer(Theater theater) throws IOException
	{
		System.out.println("Now finding this customer a seat!!");
		
		Customer customer = getCustomerInfo();
		boolean seatSuccess = theater.assignSeats(customer);
		
		if (seatSuccess)
		{
			System.out.println("The customers have seats!!!");
		}
		else
		{
			System.out.println("Sorry, You're beat!");
		}
	}
	
	//CB Option 1
	public static void showSeatingChart(Theater theater)
	{
		System.out.println(theater.showSeating());
	}
	
	//CB Option 2
	public static void removeCustomer(Theater theater) throws IOException
	{
		System.out.println("Customer to remove: ");
		String name = reader.readLine().trim();
		
		boolean success = theater.removeCustomer(name);
		
		if (success)
		{
			System.out.println(name + " was removed!! ");
		}
		else
		{
			System.out.println("They aren't there!");
		}
		
		showSeatingChart(theater);
	}
	
	//CB Option 3
	public static Theater getTheaterInfo() throws IOException
	{
		String name = "movie";
		
		System.out.println("Enter numRows: " );
		int rows = Integer.parseInt(reader.readLine().trim());
		
		System.out.println("Enter numCols: " );
		int cols = Integer.parseInt(reader.readLine().trim());
		
		return new Theater(rows, cols, name);
	}
	
	public static void getNumTickets(Theater theater)
	{
		System.out.println(theater.getTicketsSold() + " tickets sold "
				+ " for " + theater.getMovieName());
	}
	
	public static void findYoMan(Theater theater) throws IOException
	{
		System.out.println("What's his name?");
		String name = reader.readLine().trim();
		
		if (theater.hasName(name)) 
		{
			System.out.println("YEA HES HERE");
		}
		else
		{
			System.out.println("He isn't here!!" );
		}
	}
}
