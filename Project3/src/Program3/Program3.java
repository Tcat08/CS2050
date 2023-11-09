/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Description: Main class for carrying out infix to postfix and making a constructor for Program 3 objects
 */

package Program3;

import java.util.Objects;

public class Program3 { //constructor
    public Program3 (){ //no arguments needed
        String s = "";
    }

    public void showPostfixString(String s) { //method that's used for infix to postfix

        System.out.print(s); //first shows input
        System.out.print(" --> "); //to separate input from output

        // output:

        int k = s.length();       // k is length of the input string

        char blank = ' '; //other defined characters necessary to evaluate
        char rightPar = ')';
        char leftPar = '(' ;
        char decimal = '.';
        char plus = '+';
        char minus = '-';
        char multiply = '*';
        char divide = '/';
        char modulo = '%';

        int leftParCount = 0; //to resolve parenthesis balance difference, number must remain 0

        ArrayStackClass stack1 = new ArrayStackClass(); //new object from class simulating stacks (not using built in java stack)

        for (int i = 0; i < k; i++) { //loop to run for each character. i is index #
            char c = s.charAt(i); //c is actual index @ i
            char c1; //c1 will be one index after c
            if (c == s.charAt(k - 1)) c1 = '.'; //if c is at the last index k, c1 cannot exist so it defaults to a . which is configured properly
            else c1 = s.charAt(i + 1); //besides that c1 is char after c

            if(c != blank) {

                if (c == leftPar) ++leftParCount;

                if (Character.isDigit(c)) {
                    if (Character.isDigit(c1) || c1 == decimal) System.out.print(c);
                    if (!Character.isDigit(c1) && c1 != decimal) System.out.print(c + " ");
                }

                if (c == decimal) {
                    if (Character.isDigit(c1)) System.out.print(c);

                    try {
                        // Check for an illegal argument condition
                        if (!Character.isDigit(c1)) {
                            throw new IllegalArgumentException(" [Incorrect Decimal] ");
                        }
                    } catch (IllegalArgumentException e) {

                        System.out.print(e.getMessage());
                    }

                    // Rest of the program
                }

                if (Character.isLetter(c)) {

                    System.out.print(c + " ");
                } //print decimals, digits, and letters without adding them to the stack first

                if (c == rightPar) {

                    try {
                        // Check for an illegal argument condition
                        if (leftParCount == 0) {
                            throw new IllegalArgumentException(" [Right parenthesis appears before left] ");
                        }
                    } catch (IllegalArgumentException e) {

                        System.out.print(e.getMessage());
                    }

                    while (!Objects.equals(stack1.peek(), "(")) {
                        String poppedElement = stack1.pop();
                        System.out.print(poppedElement);
                    } //end popping stack until top is a left parenthesis
                    --leftParCount;

                } //end running loop if c is a right parenthesis

                if (c == leftPar || c == plus || c == minus || c == multiply ||c == divide ||c == modulo) {
                    stack1.push(String.valueOf(c));

                } //end else: adding operators and left parenthesis to an array


                try {
                    // Check for an illegal argument condition
                    if (c!= decimal && c != rightPar && c != leftPar && c != plus && c != minus && c != multiply && c != divide && c != modulo && !Character.isDigit(c) && !Character.isLetter(c)) {
                        throw new IllegalArgumentException(" [Invalid Character Removed] ");
                    }

                    // Continue with the program after the check
                } catch (IllegalArgumentException e) {

                    System.out.print( e.getMessage());
                }
            } // end if !blank


        } // end for loop 2: printing letters and adding operators to an array

        try {
            // Check for an illegal argument condition
            if (leftParCount !=0) {
                throw new IllegalArgumentException(" [Not enough right parenthesis. Left Parenthesis ignored] ");
            }
        } catch (IllegalArgumentException e) {

            System.out.print( e.getMessage());
        }


        while (!stack1.isEmpty()) {
            if (!Objects.equals(stack1.peek(), String.valueOf(rightPar)) && !Objects.equals(stack1.peek(),( String.valueOf(leftPar) ) )   ){
                String poppedElement = stack1.pop();
                System.out.print(poppedElement);
                System.out.print(" ");

            } //end testing if the top of the stack is a parenthesis
            if (!stack1.isEmpty() && (Objects.equals(stack1.peek(), String.valueOf(rightPar)) || Objects.equals(stack1.peek(),( String.valueOf(leftPar))))) stack1.pop(); //pop the remaining parenthesis without printing them

        } //end while 1: printing and popping from stack1

        System.out.println();

    } //end postfixString method

} //end Program3.java

