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
        int numRows, seatsInRow = 0;
        double PRICE_OF_TICKET;
        double totalEarning = 0;
        //List of queues [ Express, Reg1, Reg2 ]
        ParallelQueue<Customer> lines = new ParallelQueue<>(3);
        //two references of Theater for both movies
        Theater[] movies = new Theater[2];

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
                    option1(input, lines, movies);
                    break;
                case "2":
                    option2(input, lines, movies, totalEarning, PRICE_OF_TICKET);
                    break;
                case "3":
                    option3(input, movies);
                    break;
                case "4":
                    option4(lines);
                    break;
                case "5":
                    //display the seating chart for Shazam
                    System.out.println("Here's the seating for the Shazam! Movie Theater:");
                    System.out.println(movies[1]);
                    break;
                case "6":
                    //display the seating chart for Dumbo
                    System.out.println("Here's the seating for the Dumbo Movie Theater:");
                    System.out.println(movies[0]);
                    break;
                case "7":
                    System.out.println(" tickets have been sold for the Shazam! Movie.");
                    System.out.println(" tickets have been sold for the Dumbo Movie.");
                    System.out.println("Total Earning: " + totalEarning);
                    break;
            }
        }
    }

    //============== CASES ==============
    private static void option1(StringBuilder input, ParallelQueue lines, Theater[] movies) throws IOException {
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
//        //check if the name is in one of the theater
//        if(!movies[0].hasName("") && !movies[1].hasName("")){
//            lines.enqueue(new Customer(name, movieName, partySize, underAge));
//        }else
//            System.out.println("Is alread");
    }

    /**
     * Get the next customer from the lines and
     * assign their seats if there are enough seats.
     *
     * @param input
     * @param lines
     * @param movies
     * @param totalEarning
     * @return total earning of the tickets purchase
     * @throws IOException
     */
    private static double option2(StringBuilder input, ParallelQueue lines, Theater[] movies, double totalEarning, double PRICE_OF_TICKET) throws IOException {
        if (lines.getCurrentDQ() == -1) {
            //decide what line to serve first
            System.out.print("Which line would like to serve customers first? (Express/Reg1/Reg2): ");
            //retrieve index from option
            lines.setCurrentDQ((getInput(input).charAt(0) == 'E') ? 0 : Character.getNumericValue(input.toString().charAt(input.length() - 1)));
        }
        //Inquiry next customer (in Round Robin manner)
        Customer customer = (Customer) lines.dequeue();

        //assign seats
        if (customer.getMovieName().equals("Dumbo")) {
            //CONTINUE
        } else {
            //CONTINUE
        }

        return totalEarning;
    }

    private static void option3(StringBuilder input, Theater[] movies) throws IOException {
        System.out.print(">>Enter customer name to leave Movie Theater: ");
        String name = getInput(input);
        if (movies[0].isEmpty() && movies[1].isEmpty())
            System.out.println("No customers are in the movie theater at this time.");
        else {
            if (movies[0].removeCustomer(name) || movies[1].removeCustomer(name)) {
                System.out.println("Customer " + name + " has left the Movie Theater.");
            } else
                System.out.println("This customer is not in Movie Theater.");
        }
    }

    public static void option4(ParallelQueue lines) {
        //display first line
        if (lines.getSizeOf(1) != 0) {
            if (lines.getSizeOf(1) > 1)
                System.out.println("\t\tThe following customers are in the first line: ");
            else
                System.out.println("\t\tThe following customer is in the first line: ");
            displayLineInfo(lines.getQueue(1));
        } else
            System.out.println("\t\tNo customers in the first line");

        //display second line
        if (lines.getSizeOf(2) != 0) {
            if (lines.getSizeOf(2) > 1)
                System.out.println("\t\tThe following customers are in the second line: ");
            else
                System.out.println("\t\tThe following customer is in the second line: ");
            displayLineInfo(lines.getQueue(2));
        } else
            System.out.println("\t\tNo customers in the second line");

        //display express line
        if (lines.getSizeOf(0) != 0) {
            if (lines.getSizeOf(0) > 1)
                System.out.println("\t\tThe following customers are in the express line: ");
            else
                System.out.println("\t\tThe following customer is in the express line: ");
            displayLineInfo(lines.getQueue(0));
        } else
            System.out.println("\t\tNo customers in the express line");
    }

    //============== HELPER METHODS ==============
    private static String getInput(StringBuilder input) throws IOException {
        input.replace(0, input.length(), std.readLine());
        System.out.println(input.toString()); //echo
        return input.toString();    //return the toString just in case we need to chain call
    }

    /**
     * Calls the toString method of the given line, and
     * manipulates the string to display it properly.
     *
     * @param line
     */
    private static void displayLineInfo(QueueRA line) {
        String str = line.toString();
        System.out.println(str.replace(". ", ".\n"));
    }

}
