/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Description: Main class for carrying out infix to postfix and making a constructor for Program4 Out objects
 */

import java.util.Objects;
import java.util.Stack;

public class Program5<T> {
    private Stack<T> stack;
    public Program5() {
        stack = new Stack<>();
    }

    // Push an element onto the stack
    public void push(T item) {
        stack.push(item);
    }

    // Pop and return the top element from the stack
    public T pop() {
        return stack.pop();
    }

    // Peek at the top element without removing it
    public T peek() {
        return stack.peek();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main (String[] args) { //method that's used for infix to postfix
        String s;
        for (int i1 = 0; true; i1 += 1) {
            if (i1==0) {s = "4 + 6 * 3.1";}
            else if (i1==1) {s = "4 + (6 * 3.1)";}
            else if (i1==2) {s = "(4 + 6) * 3.1";}
            else if (i1==3) {s = "2 & 3 @ 4";}
            else if (i1==4) {s = "2 + 3 % 4";}
            else if (i1==5) {s = "2.0 + 3 * 4";}
            else if (i1==6) {s = "(2.0 + 3 / 4";}
            else if (i1==7) {s = "3 + 4 * 2 / ( 1 - 5 )";}
            else if (i1==8) {s = "3 + 4.0 * 2 / ( 1. - 5 )";}
            else if (i1==9) {s = "42 / 9.2 / 3.1 * 6";}
            else    {break;}

            System.out.print(s); //first shows input
            System.out.print(" --> "); //to separate input from output

            // output:

            int k = s.length();       // k is length of the input string

            char blank = ' '; //other defined characters necessary to evaluate
            char rightPar = ')';
            char leftPar = '(';
            char decimal = '.';
            char plus = '+';
            char minus = '-';
            char multiply = '*';
            char divide = '/';
            char modulo = '%';

            int leftParCount = 0; //to resolve parenthesis balance difference, number must remain 0

            Program5<String> stringStack = new Program5<>(); //new object from class simulating stacks (not using built in java stack)

            for (int i = 0; i < k; i++) { //loop to run for each character. "i" is index #
                char c = s.charAt(i); //c is actual index @ i
                char c1; //c1 will be one index after c
                if (c == s.charAt(k - 1))
                    c1 = '.'; //if c is at the last index k, c1 cannot exist, so it defaults to a '.' which is configured properly I guess. it can be whatever works but space didnt work.
                else c1 = s.charAt(i + 1); //besides that c1 is char after c

                if (c != blank) { //dont evaluate c spaces

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

                        while (!Objects.equals(stringStack.peek(), "(")) {
                            String poppedElement = stringStack.pop();
                            System.out.print(poppedElement);
                        } //end popping stack until top is a left parenthesis
                        --leftParCount;

                    } //end running loop if c is a right parenthesis

                    if (c == leftPar || c == plus || c == minus || c == multiply || c == divide || c == modulo) {
                        stringStack.push(String.valueOf(c));

                    } //end else: adding operators and left parenthesis to an array


                    try {
                        // Check for an illegal argument condition
                        if (c != decimal && c != rightPar && c != leftPar && c != plus && c != minus && c != multiply && c != divide && c != modulo && !Character.isDigit(c) && !Character.isLetter(c)) {
                            throw new IllegalArgumentException(" [Invalid Character Removed] ");
                        }

                        // Continue with the program after the check
                    } catch (IllegalArgumentException e) {

                        System.out.print(e.getMessage());
                    }
                } // end if !blank


            } // end for loop 2: printing letters and adding operators to nodes

            try {
                // Check for an illegal argument condition
                if (leftParCount != 0) {
                    throw new IllegalArgumentException(" [Not enough right parenthesis. Left Parenthesis ignored] ");
                }
            } catch (IllegalArgumentException e) {

                System.out.print(e.getMessage());
            }


            while (!stringStack.isEmpty()) {
                if (!Objects.equals(stringStack.peek(), String.valueOf(rightPar)) && !Objects.equals(stringStack.peek(), (String.valueOf(leftPar)))) {
                    String deletedElement = stringStack.pop();
                    System.out.print(deletedElement);
                    System.out.print(" ");

                } //end testing if the top of the list is a parenthesis
                if (!stringStack.isEmpty() && (Objects.equals(stringStack.peek(), String.valueOf(rightPar)) || Objects.equals(stringStack.peek(), (String.valueOf(leftPar)))))
                    stringStack.pop(); //pop the remaining parenthesis without printing them

            } //end while 1: printing and deleting from list

            System.out.println();
        } //end while that inputs each string

    } //end showPostfixString method

} //end Program4.java

