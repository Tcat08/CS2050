/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen

Description: Linked List Methods to create a LinkedListClass objects for the main Program4 (and the outputs from there)
Node is a made up variable type with the Node class with data and a next node to link the element to the next node in the list
Linked List Class is the public class here
Methods include append (add element to end) delete (remove element with given data) deleteLast (remove the end node and data with it)
display (to show the full list in order) getLast (basically peek at the end of the list) and listIsEmpty (to return true or false if the list is empty)
Other methods to add later would be appendStart or appendIndex to add elements to the start or a specific location
These methods would have to work in a certain order to keep things linked throughout the process like we went over in class
These methods are unnecessary for a linked list that only needs to work like a stack
 */

package Program4;

class Node {
    String data; //uses a string for data of the list element
    Node next; //connects element to next node

    Node(String data) {
        this.data = data; //node constructor, first node is the data
        this.next = null; //if there is 1 node to start there is no node to link it to. starts null
    }//end node constructor
} //end node class description

public class MyLinkedStack {
    private Node head; //new node using constructor

    // Constructor to initialize an empty linked list
    public MyLinkedStack() {
        head = null; //constructor simply starts with a null head to initialize
    }

    // Method to insert a new node at the end of the list
    public void append(String data) { //add to the end
        Node newNode = new Node(data); //make new node after head
        if (head == null) { //if the current head is empty
            head = newNode; //the head is the appended node
            return;
        }
        Node current = head; //current becomes the head temporarily
        while (current.next != null) { //if current is not null (so it's not the last node)
            current = current.next; //move current down the line of nodes until it reaches the end
        }
        current.next = newNode; //the null node at the end becomes the new node created with the data in it
    }

    // Method to delete a node by its value
    public void delete(String data) { //deletes nodes with matching data to the argument (useful, not needed for Program4 though)
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }


    public String deleteLast() {  // Basically linked list's pop - Method to delete the last node in the linked list and return its data
        if (head == null) {
            return null; // If the list is empty, nothing to delete
        }

        Node deletedDataNode = null;

        if (head.next == null) {
            // If there is only one node in the list, set head to null
            deletedDataNode = head;
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            deletedDataNode = current.next;
            current.next = null;
        }

        return deletedDataNode.data; //returns deletedDataNode and deletes it and .data returns it as a string because data is declared as a string in the class
    }


    public void display() {  // Method to display the elements of the linked list (not needed for Program4, but necessary in general)
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println(" ");
    }

    // Method to get the last node in the linked list
    public String getLast() { //returns last node data (like peek)
        if (head == null) {
            return null; // If the list is empty, return null
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }

    public boolean listIsEmpty() { //if the head is null, the head is the end and the list is empty
        return head == null;
    }
}
