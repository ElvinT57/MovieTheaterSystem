# MovieTheaterSystem
DSA Project 

Spring ’19                                               Data Structures and Algorithms                                   Dr. Hristescu

Project: : Movie Theater System

 

Timeline:  

Assigned: Friday, March 29

 

Monday, April 1: Declare working groups (1<=sizeof(group)<=2) by emailing me and CC-ing your project partner. No sharing is allowed between groups.  Groups may dissolve (members of a group may decide later on to work on their own and not as part of a group), but no new groups may form after this deadline.

 

Thursday, April 10: beginning of class: Hand in high-level description of your project design.

The high level description of your project design should include:

    - what is the most frequent operation that is expected to be performed during the entire execution of the program and how your choices optimize that frequent operation.

    - what ADT(s) you plan to use and why

    - what instances of ADT(s) you plan to use and what you plan to use them for

    - for each menu option, present the data flow and the instances affected (include what input is needed, what collections are affected and how)

    - the classes you will be designing (name, description, functionality)

    - if working in a group, which group member will work on each part of the project (the work has to be evenly distributed)

 

Monday, April 22: beginning of class: Hand in progress report (level of completion for the functionality listed in your design document).

 

Monday, April 29: Hand in and submit electronically project (code, Java doc, extensive runs - printout and e-submission of code - firm deadline 1:55pm  – no projects will be considered after the deadline and the electronic submission will be closed).

 

What to submit:

1. E-submission (only one submission per group):

Save in a folder called Project the Driver.java and all .java files needed to run your program– including exception classes and interfaces as well as node classes for linked structure implementations, output file and Rationale file.

All the steps below (replacing Your_Name(s) and yourloginname with the correct information):

Step1: Position yourself in the directory that includes your Project folder.

Step2: Run

              script Your_Name(s)_submission 

Step3:

cp -r Project ~hristescu/DSA/Project/yourloginname

Step4: Run

~hristescu/DSA/Project/Grades/fix_perms.csh

Step5: Press ControlD to end the script

Step6: Run

              printj Your_Name(s)_submission 

 

Check to make sure that the files that were listed by Step4 are the correct ones and they compile.

2. On paper (in the smallest possible format):

   - the program source code (if working in a group, clearly mark which group member worked on what module of the project).

   - the Java documentation generated with javadoc (as explained in the project statement)

   - some sample runs of the program including the one on the provided input data.

   - rationale write-up that addresses:

       -  what ADTs you used and the rationale for your choices

       -  what instances of ADTs you used, why you used them and what you used them for

       -  for each menu option, present the data flow and the instances affected

-    the classes you have used and their functionality

-    what is the most frequent operation that is expected to be performed during the entire execution of the program and how your choices optimize that frequent operation. The choices could include the choice of a specific ADT or specific implementation.

   -the printed Your_Name(s)_submission script file from 1.E-submission Step 6 above.

 

Note: for the Rationale write-up take the high level description that you have already submitted and add/modify it to include required info. If you have made changes to the design since your submission of the high level description, you need to include the up-to-date description.

 

Tuesday, April 30: Present project in lab (PowerPoint presentation).

The PowerPoint Presentation should address:

    - what ADTs you used and the rationale for your choices. This should include a discussion about what is the most frequent operation that will be performed during the execution of the program and how your choices optimize that frequent operation.

    - what instances of ADTs you used, why you used them and what you used them for

    - for each menu option, present the data flow and the instances affected

    - the classes you have used and their functionality

    - which group member worked on what module of the project (if working in a group)

    - what you learned from working on the project

    - what you learned from working in teams (if working in a group)

    - what you would do differently if you had to do this project all over again

Note: If working in a group, all group members have to participate equally in the presentation.

Write a program that models a simplified movie theater environment. The movie theater shows 2 movies:  Shazam! and Dumbo. When the user runs this program, the program should ask the user to enter information about the size of the 2 movie theaters (number of rows and the number of seats in each row) and the price of the ticket.  The movie theater system is initialized using this information.

After entering this information, the user should be able to use the system through an interactive menu that allows customers to enter the movie theater, buy tickets for one of the 2 movies and leave the theater.

 

The user of this program should be offered the following 8 choices through an interactive menu:

0.       End the program.

1.       Customer(s) enter(s) Movie Theater.

2.       Customer buys ticket(s).

3.       Customer(s) leave(s) the theater.

4.       Display info about customers waiting for tickets.

5.       Display seating chart for Shazam! Movie Theater.

6.       Display seating chart for Dumbo Movie Theater.

7.       Display number of tickets sold and total earnings.

   

Here are the rules for movie theater environment:

1.       There are 2 movie theaters; one for the Shazam! movie, the other for the Dumbo movie.

2.       There are 2 regular ticket lines and one express line. Anyone can use the regular line. Only customers with children age 11 and under can use the express line.

3.       Customers can enter the movie theater system through menu option 1. Customers are uniquely identified by their names. If there is a customer with this name in the movie theater, then the customer will be asked to submit a different name. In addition to the name, the customer has to specify the size of the party and whether it includes children age 11 or under.  The system should then place the customer in the shortest line that they are allowed to be in. If one of the regular lines is at least twice as short as the express line, then the customer will be placed in that regular line.

4.       Customers who are at the front of the ticket lines can buy tickets when option 2 is selected. The first time option 2 is chosen the user of the application will be asked to pick the first line that will serve customers. The options are Express, Reg1 and Reg2. The lines will be served in a round-robin manner with the lines ordered as follows: Express -> Reg1 -> Reg2 -> Express. If there is no customer in the line whose turn it is to be served, then the customer in the next line will be served.  If there are not enough seats to accommodate the whole party, the customer will be offered the choice of either viewing the other movie (if there are enough seats to seat the party) or leaving the movie theater. Otherwise, the system should assign seats identified by the row number and seat number to the customer’s party.  Note:  The seats may not always be adjacent.

5.       For option 3, the party identified using the name of the customer who bought the ticket(s) will leave the movie theater. The seats used by the party will be freed.

6.       The application should offer information about the customers who are in the ticket lines (option 4) and about the customers who are in the 2 movie theaters (option 5 and 6).

7.       The application should offer information about the number of tickets sold for both movies and the total earnings (option 7).

8.       When the program is ended (option 0), all customers (in ticket lines or in the two movie theaters) have to leave and the movie theater closes.

 

In designing the solution for this problem, keep in mind that correctness in not the only requirement.

To receive full credit, your solution should:

·         be correct

·         be efficient

·         use the most appropriate (efficient) ADTs

·         not waste memory

·         use good programming style

·         scale

Your program should work with any #rows and #seats specified for the 2 movie theaters.

Your solution should not waste memory. Choose the most efficient ADT(s) from your OWN implementation of List(s), Stack, Queue, or DEQ ADT and document your choices in a Rationale write-up. You are not allowed to use arrays, ArrayLists and other Java Class Libraries.

You can assume correct input data. You are welcome to add input error-checking, but it is not required.

You have to comment your code and generate Java Documentation using Javadoc on elvis (instructions). Use the guidelines in http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html. Documentation is 20% of the grade. Include in your final submission the small-format prints of the Java Docs for all the classes that are part of your project (including ADT implementations, but not interfaces or exceptions).

 

Use an extensive testing suite for your program, including this one. You can find a sample run on this input in this file. DO NOT print THESE GIVEN files and waste paper. You have to run your program on the 3 input data files that have: express, regular1, regular2 as the first lane to check out a customer (the files have other differences, too). Include in your submission the sample runs of YOUR program on this given input suite as well as any input that you consider important in testing your solution.
