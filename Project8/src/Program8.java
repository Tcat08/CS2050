/*
Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Program 8 Description: Sorting words using a buffered reader to read words and the structure of a binary search tree to sort them in order.
 */

import java.io.*;
import java.io.IOException;

public class Program8 {
    private String inputFilePath;
    private String outputFilePath;

    public Program8(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public String readWordsFromFile() {
        StringBuilder allWordsAsString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!word.isEmpty()) {
                        allWordsAsString.append(word).append(" ");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allWordsAsString.toString();
    }

    public void writeSortedStringToFile(String sortedString) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(sortedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\cebce\\Connors Files\\Coding\\CS 2050\\Unfinished\\Project8\\DraculaSample2.txt"; // Replace with the path to your input file
        String outputFilePath = "draculaSample2Output.txt"; // Replace with the path to your output file

        Program8 program = new Program8(inputFilePath, outputFilePath);

        // Step 1: Read the file and filter/format the words
        String allWordsAsString = program.readWordsFromFile();

        // Step 2: Insert the words into a binary search tree (assuming you have a BST class)
        BinarySearchTree<String> bst = program.getBinarySearchTree(allWordsAsString);

        // Step 3: Perform post-order traversal to generate a sorted string
        String sortedString = bst.postOrderTraversal();

        // Step 4: Write the sorted string to the output file
        program.writeSortedStringToFile(sortedString);

        // Perform operations on the binary search tree
        System.out.println("Number of Nodes: " + bst.getNodeCount());
        System.out.println("Tree height: " + bst.getHeight());
        System.out.println("Max number of Nodes with the same tree height: " + bst.maxNodes()); //show number of nodes possible with a tree of the same height if that tree was balanced

        // Predefined word to search
        String wordToSearch = "example"; // Testing search. Replace with the word you want to search (doesn't say where word is yet, only if it IS there)
        SubChildNode<String> result = bst.search(wordToSearch);

        if (result != null) {
            System.out.println("Found: " + result.getData()); //if search word is found
        } else {
            System.out.println("Key not found."); //if search word is not found
        }
    }

    private BinarySearchTree<String> getBinarySearchTree(String words) {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        String[] wordArray = words.split("\\s+");
        for (String word : wordArray) {
            bst.insert(word);
        }
        return bst;
    }



}

