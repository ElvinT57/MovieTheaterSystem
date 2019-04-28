
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update:
 * Submitted:  04/30/19
 * Comment: test suite and sample run attached
 *
 * @author: Christina Bannon, Elvin Torres
 * @version:
 */

public class Driver {
	
    static BufferedReader std 
    = new BufferedReader(
    		new InputStreamReader(System.in));

    public static void main(String[] args)
    		throws IOException {
        //Local variables for this program
        StringBuilder input =
        		new StringBuilder("");
        int numRows, seatsInRow = 0;
        double PRICE_OF_TICKET;
        //List of queues [ Express, Reg1, Reg2 ]
        ParallelQueue<Customer> lines =
        		new ParallelQueue<>(3);
        //two references of Theater for both movies
        Theater[] movies = new Theater[2];

        System.out.println("Welcome to "
        		+ "the wonderful Movie Theater "
        		+ "program!\n" +
                "\tTonight's feature are:\n" +
                "\t\t\"Shazam!\" and \"Dumbo\"\n");

        //Prompt for the size of each theaters
        // and additional information
        System.out.println("Please specify "
        		+ "the size of the Movie Theaters");
        System.out.println("\t\tEnter the"
        		+ " information about the "
        		+ "Dumbo Movie Theater:");
        System.out.print("\t\t\t\t>>Enter "
        		+ "number of rows: ");
        numRows = 
        		Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter "
        		+ "number of seats in a row: ");
        seatsInRow = 
        		Integer.parseInt(getInput(input));
        //element 1 is dumbo
        movies[0] = new Theater(numRows,
        		seatsInRow, "Dumbo");

        System.out.println("\t\tEnter "
        		+ "the information about the "
        		+ "Shazam! Movie Theater:");
        System.out.print("\t\t\t\t>>Enter"
        		+ " number of rows: ");
        numRows = 
        		Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter"
        		+ " number of seats in a row: ");
        seatsInRow = 
        		Integer.parseInt(getInput(input));
        //element 2 is shazam
        movies[1] = new Theater(numRows,
        		seatsInRow, "Shazam!");

        //finally ask for price of ticket
        System.out.print("\t\t\t\t>>Enter"
        		+ " the price of a ticket: ");
        PRICE_OF_TICKET = 
        		Double
        		.parseDouble(getInput(input));

        //Display Menu
        System.out.println("\nSelect an operation "
        		+ "from the following menu\n" +
                "\t\t0.End the program.\n" +
                "\t\t1.Customer(s) enter(s)"
                + " Movie Theater.\n" +
                "\t\t2.Customer buys ticket(s).\n" +
                "\t\t3.Customer(s) leave(s)"
                + " the theater.\n" +
                "\t\t4.Display info about"
                + " customers waiting for tickets.\n" +
                "\t\t5.Display seating chart "
                + "for Shazam! Movie Theater.\n" +
                "\t\t6.Display seating chart"
                + " for Dumbo Movie Theater.\n" +
                "\t\t7.Display number of "
                + "tickets sold and"
                + " total earnings.");
        //Main loop
        while (true) {
            System.out.print(">>You know "
            		+ "the options. "
            		+ "Make your menu "
            		+ "selection now: ");
            switch (getInput(input)) {
                case "0":
                    System.out.printf("The "
                    		+ "Wonderful Movie"
                    		+ " Theater who "
                    		+ "earned $%.02f "
                    		+ "kicks out "
                    		+ "remaining "
                    		+ "customers and "
                    		+ "closes..."
                    		+ "\nGood Bye!",
                            (movies[0]
                            	.getTicketsSold() 
                            + movies[1]
                            	.getTicketsSold())
                            * PRICE_OF_TICKET);
                    System.exit(0);
                    break;
                case "1":
                    option1(input, lines, movies);
                    break;
                case "2":
                    option2(input, lines, movies);
                    break;
                case "3":
                    option3(input, movies);
                    break;
                case "4":
                    option4(lines);
                    break;
                case "5":
                    //display the seating chart for Shazam
                    System.out.println("Here's"
                    		+ " the seating for "
                    		+ "the Shazam! Movie "
                    		+ "Theater:");
                    System.out.println(movies[1]);
                    break;
                case "6":
                    //display the seating chart for Dumbo
                    System.out.println("Here's "
                    		+ "the seating for "
                    		+ "the Dumbo Movie "
                    		+ "Theater:");
                    System.out.println(movies[0]);
                    break;
                case "7":
                    System.out.println(movies[1]
                    		.getTicketsSold() 
                    		+ " tickets have been "
                    		+ "sold for the "
                    		+ "Shazam! Movie.");
                    System.out.println(movies[0]
                    		.getTicketsSold() 
                    		+ " tickets have been"
                    		+ " sold for the "
                    		+ "Dumbo Movie.");
                    System.out.println("Total "
                    		+ "Earning: " 
                    		+ ((movies[0]
                    			.getTicketsSold() 
                    		  + movies[1]
                    		    .getTicketsSold()) 
                    		 * PRICE_OF_TICKET));
                    break;
            }
            System.out.println();
        }
    }

    //============== CASES ==============

    /**
     * Prompts for customer information 
     * (Name, partySize, movieName, underAge).
     * After, it checks if the customer 
     * is in the either movie theater. Prompts
     * repeatably until they enter a name 
     * that is not in the movie theater.
     * If the customer was not found, 
     * then they will added to the appropriate
     * line.
     *
     * @param input  input string builder
     * @param lines  ParallelQueue that
     *  represents lines
     * @param movies array of movies
     * @throws IOException
     */
    private static void 
    option1(StringBuilder input, 
    		ParallelQueue lines, 
    		Theater[] movies) throws IOException 
    {
        //Local variables
        String name, movieName;
        int partySize = 0;
        boolean underAge;
        boolean isInMovies = true;

        do {
            //prompt for info for new Customer
            System.out.print(">>Enter "
            		+ "customer name: ");
            name = getInput(input);
            //check if the name is in one of the theater
            if ((movies[0].hasName(name) 
            		|| movies[1].hasName(name)) 
            		|| lines.contains(name)) {
                System.out.println("Customer " 
            		+ name + " is already in the "
            				+ "theater!");
                System.out.println("Please"
                		+ " specify a different "
                		+ "name");
            } else
                isInMovies = false;
        } while (isInMovies);

        System.out.print(">>Enter party size: ");
        partySize = 
        		Integer.parseInt(getInput(input));

        System.out.print(">>Enter movie name: ");
        movieName = getInput(input);

        System.out.print(">>Is a child 11 "
        		+ "or younger in this "
        		+ "party(Y/N)? ");
        underAge 
        = (getInput(input)
        		.equalsIgnoreCase("Y"))
        		? true : false;
        //enqueue the customer into 
        // the appropriate line
        lines.enqueue(new Customer(name,
        		movieName, partySize, underAge));
        switch (lines.getLastEQ()) {
            case 0:
                System.out.println("Customer " 
            + name + " is in express line.");
                break;
            case 1:
                System.out.println("Customer " 
            + name + " is in first line.");
                break;
            case 2:
                System.out.println("Customer "
            + name + " is in second line.");
                break;
        }


    }

    /**
     * Retrieve the next customer from
     * the lines and assign their seats 
     * if there are enough seats.
     *
     * @param input  input string builder
     * @param lines  ParallelQueue that 
     * represents lines
     * @param movies array of movies
     * @throws IOException
     */

    private static void 
    option2(StringBuilder input, 
    		ParallelQueue lines, Theater[] movies)
    				throws IOException 
    {
        if (lines.getCurrentDQ() == -1) {
            //decide what line to serve first
            System.out.print("Which line"
            		+ " would like to serve "
            		+ "customers first? "
            		+ "(Express/Reg1/Reg2): ");
            //retrieve index from option
            lines.setCurrentDQ((getInput(input)
            	.charAt(0) == 'E') ?
            	 0 : Character
            		.getNumericValue(input
            				.toString()
            				.charAt(input
            					.length() - 1)));
        }
        if (!lines.isEmpty()) {
            //Inquiry next customer
        	// using the lastLine index
            Customer customer 
            = (Customer) lines.dequeue();

            System.out.println("Serving customer "
            + customer.getName());
            //assign seats
            if (customer.getMovieName()
            		.equals("Dumbo"))
                assignMovie0(input,
                		movies, customer, true);
            else
                assignMovie1(input, 
                		movies, customer, true);
        } else
            System.out.println("There"
            		+ " are no customers"
            		+ " waiting in any line.");
    }

    /**
     * Prompts for a customer 
     * to leave Movie Theater.
     * <p>
     * If the customer is found in either
     * movie theaters, they will be removed 
     * from the seating chart.
     *
     * @param input  input string builder
     * @param movies array of movies
     * @throws IOException
     */

    private static void
    option3(StringBuilder input, 
    		Theater[] movies) throws IOException
    {
        if (!movies[0].isEmpty() 
        		&& !movies[1].isEmpty()) {
            System.out.print(">>Enter customer "
            		+ "name to leave Movie "
            		+ "Theater: ");
            String name = getInput(input);
            if (movies[0].isEmpty()
            	 && movies[1].isEmpty()) {
                System.out.println("No customers "
                		+ "are in the movie "
                		+ "theater at this time.");
            }
            else {
                if (movies[0].removeCustomer(name) 
                	|| movies[1].removeCustomer(name))
                    System.out.println("Customer "
                + name + " has left the Movie"
                		+ " Theater.");
                else
                    System.out.println("This "
                    		+ "customer is not in"
                    		+ " Movie Theater.");
            }
        } else {
            System.out.println("No customers are "
            		+ "in the movie theater at "
            		+ "this time.");
        }
    }

    /**
     * Displays the customers in each line.
     *
     * @param lines ParallelQueue that 
     * represents lines
     */
    public static void 
    option4(ParallelQueue lines) {
        //display first line
        if (lines.getSizeOf(1) != 0) {
            if (lines.getSizeOf(1) > 1)
                System.out.println("\t\tThe "
                		+ "following customers "
                		+ "are in the "
                		+ "first line: ");
            else
                System.out.println("\t\tThe "
                		+ "following customer is "
                		+ "in the first line: ");
            displayLineInfo(lines.getQueue(1));
        } else
            System.out.println("\t\tNo customers "
            		+ "in the first line");

        //display second line
        if (lines.getSizeOf(2) != 0) {
            if (lines.getSizeOf(2) > 1)
                System.out.println("\t\tThe "
                		+ "following customers "
                		+ "are in the second "
                		+ "line: ");
            else
                System.out.println("\t\tThe"
                		+ " following customer "
                		+ "is in the second"
                		+ " line: ");
            displayLineInfo(lines.getQueue(2));
        } else
            System.out.println("\t\tNo customers"
            		+ " in the second line");

        //display third line
        if (lines.getSizeOf(0) != 0) {
            if (lines.getSizeOf(0) > 1)
                System.out.println("\t\tThe"
                		+ " following customers"
                		+ " are in the express"
                		+ " line: ");
            else
                System.out.println("\t\tThe "
                		+ "following customer "
                		+ "is in the express "
                		+ "line: ");
            displayLineInfo(lines.getQueue(0));
        } else
            System.out.println("\t\tNo customers"
            		+ " in the express line");
    }

    //============== HELPER METHODS ==============

    private static String 
    getInput(StringBuilder input) 
    		throws IOException {
        input.replace(0, 
        		input.length(), 
        		std.readLine());
        System.out.println(input.toString());
        return input.toString();    
      // return the toString just in case we 
      // need to chain call
    }

    /**
     * Calls the toString method of the 
     * given line, and manipulates the string 
     * to display it properly.
     *
     * @param line ParallelQueue that 
     * represents lines
     */
    private static void 
    displayLineInfo(QueueRA line) {
        String str = line.toString();
        System.out.println(
        		str.replace(". ", ".\n"));
    }

    /**
     * This method checks if the party 
     * can be seated in the Dumbo movie (movie #1).
     * If they cannot be seated, 
     * then prompt to be seated in the other
     * movie.
     *
     * @param input
     * @param movies
     * @param customer
     * @param firstTime
     * @throws IOException
     */
    private static void 
    assignMovie0(StringBuilder input, 
    		Theater[] movies, 
    		Customer customer, 
    		boolean firstTime)
    				throws IOException {
        if (movies[0].assignSeats(customer)){
            //the customer has been seated
        	// successfully
            System.out.println(customer.getName() 
            		+ ", party of " + 
            		customer.getSizeOfParty() + 
            		" has been seated in "
            		+ "the Dumbo Movie Theater");
        } else {
            //check if the other movie has 
            // enough seats
            if (movies[1]
            		.hasEnoughSeats(customer
            				.getSizeOfParty())) {
            	
            	//if it is the first time 
            	//checking the other movie
                if (firstTime) {    
                    System.out.println("Sorry. "
                    		+ "This movie is sold"
                    		+ " out.");
                    System.out.print("Would you"
                    		+ " like to see the "
                    		+ "other movie(Y/N)?");
                    
                    // if yes then attempt the 
                    // other movie
                    if (getInput(input)
                    	.equalsIgnoreCase("Y")) { 
                        assignMovie1(input, 
                        		movies, customer,
                        		false);
                    }
                    else {
                        System.out.println(
                        		"Customer " +
                        customer.getName() +
                        " has left the Movie "
                        + "Theater.");
                    }
                }
                // if it's the second time
            	// checking, then both movies 
            	// are sold out
                else {

                    System.out.println("Sorry. "
                    		+ "Both movies are "
                    		+ "sold out."
                    		+ " Good bye!");
                    System.out.println("Customer "
                    		+ customer.getName() 
                    		+ " has left the "
                    		+ "Movie Theater.");
                }
            } else {
                //if there are not enough 
            	//seats in the other movie
                System.out.println("Sorry. "
                		+ "Both movies are"
                		+ " sold out. Good bye!");
                System.out.println("Customer "
                		+ customer.getName() + 
                		" has left the Movie "
                		+ "Theater.");
            }
        }
    }

    /**
     * This method checks if the party 
     * can be seated in the Shazam! movie 
     * (movie #2). If they cannot be
     * seated, they are prompt to be seated 
     * in the other movie.
     *
     * @param input
     * @param movies
     * @param customer
     * @param firstTime
     * @throws IOException
     */
    private static void 
    assignMovie1(StringBuilder input, 
    		Theater[] movies, Customer customer, 
    		boolean firstTime) 
    				throws IOException {
    	
    	// if the customer has been seated
    	// successfully
        if (movies[1].assignSeats(customer)) {
           
            System.out.println(customer.getName() 
            		+ ", party of " 
            		+ customer.getSizeOfParty() 
            		+ " has been seated in the "
            		+ "Shazam! Movie Theater");
        } else {
            if (movies[0]
            		.hasEnoughSeats(customer
            				.getSizeOfParty())) {
                if (firstTime) {
                    System.out.println("Sorry. "
                    		+ "This movie is sold "
                    		+ "out.");
                    System.out.print("Would you "
                    		+ "like to see the "
                    		+ "other movie(Y/N)?");
                    if (getInput(input)
                    	.equalsIgnoreCase("Y")) {
                        assignMovie0(input, movies,
                        		customer, false);
                    }else {
                        System.out.println(
                        		"Customer " 
                        + customer.getName() +
                        " has left the Movie "
                        + "Theater.");
                    }
                } else {
                    System.out.println("Sorry. "
                    		+ "Both movies are "
                    		+ "sold out. "
                    		+ "Good bye!");
                    System.out.println("Customer "
                    		+ customer.getName() 
                    		+ " has left the "
                    		+ "Movie Theater.");
                }
            } else {
                System.out.println("Sorry. "
                		+ "Both movies are "
                		+ "sold out. Good bye!");
                System.out.println("Customer " 
                		+ customer.getName() + 
                		" has left the Movie "
                		+ "Theater.");
            }
        }
    }
}
