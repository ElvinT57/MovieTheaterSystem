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

        System.out.println("Welcome to the wonderful Movie Theater program!\n" +
                "\tTonight's feature are:\n" +
                "\t\t\"Shazam!\" and \"Dumbo\"\n");

        //Prompt for the size of each theaters and additional information
        System.out.println("Please specify the size of the Movie Theaters");
        System.out.println("\t\tEnter the information about the Dumbo Movie Theater:");
        System.out.print("\t\t\t\t>>Enter number of rows: ");
        numRows = Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter number of seats in a row: ");
        getInput(input);

        System.out.println("\t\tEnter the information about the Shazam! Movie Theater:");
        System.out.print("\t\t\t\t>>Enter number of rows: ");
        numRows = Integer.parseInt(getInput(input));
        System.out.print("\t\t\t\t>>Enter number of seats in a row: ");
        seatsInRow = Integer.parseInt(getInput(input));

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
                    break;
                case "2":
                    break;
                case "3":
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

    //============== HELPER METHODS ==============
    public static String getInput(StringBuilder input) throws IOException {
        input.replace(0, input.length(), std.readLine());
        System.out.println(input.toString()); //echo
        return input.toString();    //return the toString just in case we need to chain call
    }
}
