import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
    static BufferedReader std = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        //Local variables for this program
        StringBuilder input = new StringBuilder("");
        int numberTicketsSold, numRows, seatsInRow = 0;
        double PRICE_OF_TICKET;
        double totalEarning = 0;
        //List of queues [ Express, Reg1, Reg2 ]
        QueueRA[] lines = new QueueRA[3];
        //initialize each queue
        lines[0] = new QueueRA<Customer>();
        lines[1] = new QueueRA<Customer>();
        lines[2] = new QueueRA<Customer>();
        //two references of Theater for both movies
        Theater[] movies = new Theater[2];
        //index of current line that will be called
        int currLine = -1; //initialize with -1 to indicate it is our first call

        System.out.println("Welcome to the wonderful Movie Theater program!\n" +
                "\tTonight's feature are:\n" +
                "\t\t\"Shazam!\" and \"Dumbo\"\n");

        //Prompt for the size of each theaters and additional information
        System.out.println("Please specify the size of the Movie Theaters");
        System.out.println("\t\tEnter the information about the Dumbo Movie Theater:");
        System.out.print("\t\t\t\t>>Enter number of rows: ");
        numRows = Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter number of seats in a row: ");
        seatsInRow = Integer.parseInt(getInput(input));
        //element 1 is dumbo
        movies[0] = new Theater(numRows, seatsInRow, "Dumbo");

        System.out.println("\t\tEnter the information about the Shazam! Movie Theater:");
        System.out.print("\t\t\t\t>>Enter number of rows: ");
        numRows = Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter number of seats in a row: ");
        seatsInRow = Integer.parseInt(getInput(input));
        //element 2 is shazam
        movies[1] = new Theater(numRows, seatsInRow, "Shazam!");

        //finally ask for price of ticket
        System.out.print("\t\t\t\t>>Enter the price of a ticket: ");
        PRICE_OF_TICKET = Double.parseDouble(getInput(input));

        //Display Menu
        System.out.println("\nSelect an operation from the following menu\n" +
                "\t\t0.End the program.\n" +
                "\t\t1.Customer(s) enter(s) Movie Theater.\n" +
                "\t\t2.Customer buys ticket(s).\n" +
                "\t\t3.Customer(s) leave(s) the theater.\n" +
                "\t\t4.Display info about customers waiting for tickets.\n" +
                "\t\t5.Display seating chart for Shazam! Movie Theater.\n" +
                "\t\t6.Display seating chart for Dumbo Movie Theater.\n" +
                "\t\t7.Display number of tickets sold and total earnings.");
        //Main loop
        while (true) {
            System.out.print(">>You know the options. Make your menu selection now: ");
            switch (getInput(input)) {
                case "0":
                    System.out.printf("The Wonderful Movie Theater who earned %.02f kicks out remaining customers and closes...\nGood Bye!", totalEarning);
                    System.exit(0);
                    break;
                case "1":
                    option1(input, lines);
                    break;
                case "2":
                    option2(input, lines, movies, currLine);
                    break;
                case "3":
                    option3(input,movies);
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
            }
        }
    }

    //============== CASES ==============
    private static void option1(StringBuilder input, QueueRA[] lines) throws IOException {
        //Local variables
        String name, movieName;
        int partySize = 0;
        boolean underAge;

        //prompt for info for new Customer
        System.out.print(">>Enter customer name: ");
        name = getInput(input);

        System.out.print(">>Enter party size: ");
        partySize = Integer.parseInt(getInput(input));

        System.out.print(">>Enter movie name: ");
        movieName = getInput(input);

        System.out.print(">>Is a child 11 or younger in this party(Y/N)? ");
        underAge = (getInput(input).equalsIgnoreCase("Y")) ? true : false;

        //find the appropriate line for this customer
        assignLine(new Customer(name, movieName, partySize, underAge));
    }

    private static void option2(StringBuilder input, QueueRA[] lines, Theater[] movies, int currLine) throws IOException {
        if (currLine == -1) {
            //decide what line to serve first
            System.out.print("Which line would like to serve customers first? (Express/Reg1/Reg2): ");
            //retrieve index from option
            currLine = (getInput(input).charAt(0) == 'E') ? 0 : Character.getNumericValue(input.toString().charAt(input.length() - 1));
        }
        //Inquiry next customer using the lastLine index
        Customer customer = (Customer) lines[(currLine % 3)].dequeue();   //use modulo for round robin
        //increment currline
        currLine++;

        //assign seats
        if (customer.getMovieName().equals("Dumbo")) {
            //CONTINUE
        } else {
            //CONTINUE
        }
    }

    private static void option3(StringBuilder input, Theater[] movies) throws IOException{
        System.out.print(">>Enter customer name to leave Movie Theater: ");
        String name = getInput(input);
        if (movies[0].isEmpty() && movies[1].isEmpty())
            System.out.println("No customers are in the movie theater at this time.");
        else {
            if(movies[0].removeCustomer(name) || movies[1].removeCustomer(name)){
                System.out.println("Customer " + name + " has left the Movie Theater.");
            }else
                System.out.println("This customer is not in Movie Theater.");
        }
    }

    //============== HELPER METHODS ==============
    private static String getInput(StringBuilder input) throws IOException {
        input.replace(0, input.length(), std.readLine());
        System.out.println(input.toString()); //echo
        return input.toString();    //return the toString just in case we need to chain call
    }

    /**
     * Assign the appropriate line for the given customer.
     * No need to return a reference since we are doing
     * in place changes to the queues.
     *
     * @param customer
     */
    private static void assignLine(Customer customer) {
        //CONTINUE
    }
}
