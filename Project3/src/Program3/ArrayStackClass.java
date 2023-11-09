/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Description: Class to simulate a stack by a constructor, push, pop, is empty, and peek
 */

package Program3;
public class ArrayStackClass {
    private String[] stack; //stack variable that is string array
    private int size; //size variable that is size (1 greater than index)

    public ArrayStackClass() { //constructor (for no argument object)
        stack = new String[0]; // Initialize with an initial capacity of 0
        size = 0;
    }

    // Push a string onto the stack
    public void push(String item) { //adds string to top
        if (size == stack.length) {
            // If the stack is full, create a new larger array
            String[] newStack = new String[stack.length + 1];
            // Copy elements from the old array to the new one
            System.arraycopy(stack, 0, newStack, 0, size);
            stack = newStack;
        }
        stack[size++] = item;
    }

    // Pop a string from the stack and return it
    public String pop() { //takes string off top
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        String poppedItem = stack[--size];
        stack[size] = null; // Clear the reference to the popped item
        // If the size becomes less than a quarter of the array length, shrink the array
        if (size > 0) {
            String[] newStack = new String[stack.length - 1];
            System.arraycopy(stack, 0, newStack, 0, size);
            stack = newStack;
        }
        return poppedItem;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the stack
    /*public int size() { //test size
        // turn on code if size needs to be tested
        return size;
    }*/

    public String peek(){ //see top string

        return stack[size - 1];
    }

}

