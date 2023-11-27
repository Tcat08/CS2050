/* Name: Connor Byers
Class: CS2050
Prof: Blanche Cohen
Description: Program9 uses a buffered reader to read dracula and sort in a red black bst.
Also, it uses pretty print limited method to do the same thing as pretty print but for the first 3 layers and
the method can print an output for the existing tree for any limited number.
It also just outputs the results and pretty print to preview too.
 */



import java.io.*;
import java.io.IOException;


public class Program9 {
    private String inputFilePath;
    private String outputFilePath;

    public Program9(String inputFilePath, String outputFilePath) {
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
        }
        catch (IOException e) {
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
        String inputFilePath = "C:\\Users\\cebce\\Connors Files\\Coding\\CS 2050\\Unfinished\\Project9\\dracula.txt"; // Replace with the path to your input file
        String outputFilePath = "draculaOutput.txt"; // Replace with the path to your output file

        Program9 program = new Program9(inputFilePath, outputFilePath); //change

        // Step 1: Read the file and filter/format the words
        String allWordsAsString = program.readWordsFromFile();

        // Step 2: Insert the words into a search tree
        RedBlackTree rbt = program.getRedBlackTree(allWordsAsString);

        // Step 3: Perform post-order traversal to generate a sorted string
        String sortedString = rbt.postorder();

        // Step 4: Write the sorted string to the output file
        //program.writeSortedStringToFile(sortedString); //This will write the full output traversal

        // Step 5: Print the first 3 layers to the file
        try (PrintStream fileStream = new PrintStream(new FileOutputStream("PrettyPrinted_draculaOutput.txt"))) {
            rbt.prettyPrintLimitedString(rbt, 3, fileStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("|-----^Layers Printed^-----|");
        System.out.println();


        // Perform operations on the existing binary search tree
        System.out.println("Number of Nodes: " + rbt.getNodeCount());
        System.out.println("Tree height: " + rbt.getHeight());
        System.out.println("Max number of Nodes with the same tree height: " + rbt.maxNodes()); //show number of nodes possible with a tree of the same height if that tree was balanced

        // Predefined word to search
        String wordToSearch = "cat"; // Testing search. Replace with the word you want to search (doesn't say where word is yet, only if it IS there)
        Node result = rbt.searchTree(wordToSearch);

        if (result != null) {
            System.out.println("Found: " + result.getData()); //if search word is found
            System.out.println("[" + result.getData() + "]" + " exists " + rbt.getWordCount(wordToSearch) + " time(s) ");
        } else {
            System.out.println("Key not found."); //if search word is not found
        }




    }

    private RedBlackTree getRedBlackTree(String words) {
        RedBlackTree rbt = new RedBlackTree();
        String[] wordArray = words.split("\\s+");
        for (String word : wordArray) {
            rbt.insert(word);
        }
        return rbt;
    }





}