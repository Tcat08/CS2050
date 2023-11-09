/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Description: Using object s1, we use the ShowPostfixString to turn infix notations to postfix.
 */

package Program3;

public class Out {

    public static void main(String[] args) { //program has main to run

        Program3 s1 = new Program3(); //new object (empty string)

        s1.showPostfixString("4 + 6 * 3.1"); //simply put inputs here as a string and run this program
        s1.showPostfixString("4 + (6 * 3.1)");
        s1.showPostfixString("(4 + 6) * 3.1");
        s1.showPostfixString("2 + 3 * 4");
        s1.showPostfixString("2 & 3 @ 4");
        s1.showPostfixString("2 + 3 % 4");
        s1.showPostfixString("2.0 + 3 * 4");
        s1.showPostfixString("(2.0 + 3 / 4");
        s1.showPostfixString("3 + 4 * 2 / ( 1 - 5 )");
        s1.showPostfixString("3 + 4.0 * 2 / ( 1. - 5 )");
        s1.showPostfixString("42 / 9.2 / 3.1 * 6");



    } //end main
} //end class out
