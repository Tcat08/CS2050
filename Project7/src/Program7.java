/*
 * Connor Byers
 * Professor Cohen
 * CS2050
 * Program 7 description: Timing sorting algorithms, including Bubble, Selection, and insertion for both
 * 20000 integers and 10000 strings and the built-in java collection sort for the 10000 strings too
 * (because it's a copy from Project6)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program7 {
    public static void main(String[] args) {
        try {
            // Create an ArrayList to store the integers from the file
            ArrayList<Integer> numbersList = new ArrayList<>();

            // Read the integers from the file and populate the ArrayList
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cebce\\Connors Files\\Coding\\CS 2050\\Unfinished\\Project7\\NumbersInFile.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                numbersList.add(number);
            }
            reader.close();

            // Create two integer arrays of 20,000 elements
            int[] bubbleSortArray = new int[numbersList.size()];
            int[] selectionSortArray = new int[numbersList.size()];

            // Copy the elements from the ArrayList to the arrays
            for (int i = 0; i < numbersList.size(); i++) {
                bubbleSortArray[i] = numbersList.get(i);
                selectionSortArray[i] = numbersList.get(i);
            }

            // Measure the time for Bubble Sort
            long bubbleSortStartTime = System.nanoTime();
            bubbleSort(bubbleSortArray);
            long bubbleSortEndTime = System.nanoTime();
            long bubbleSortTime = bubbleSortEndTime - bubbleSortStartTime;
            System.out.println("Bubble Sort Time (nanoseconds): " + bubbleSortTime);
            System.out.println("To sort " + numbersList.size() + " Integers");

            // Measure the time for Selection Sort
            long selectionSortStartTime = System.nanoTime();
            selectionSort(selectionSortArray);
            long selectionSortEndTime = System.nanoTime();
            long selectionSortTime = selectionSortEndTime - selectionSortStartTime;
            System.out.println("Selection Sort Time (nanoseconds): " + selectionSortTime);
            System.out.println("To sort " + numbersList.size() + " Integers");


            // Create an array for insertion sort
            int[] insertionSortArray = new int[numbersList.size()];

            // Copy the elements from the ArrayList to the insertion sort array
            for (int i = 0; i < numbersList.size(); i++) {
                insertionSortArray[i] = numbersList.get(i);
            }

            // Measure the time for Insertion Sort
            long insertionSortStartTime = System.nanoTime();
            insertionSort(insertionSortArray);
            long insertionSortEndTime = System.nanoTime();
            long insertionSortTime = insertionSortEndTime - insertionSortStartTime;
            System.out.println("Insertion Sort Time for Integers (nanoseconds): " + insertionSortTime);
            System.out.println("To sort " + numbersList.size() + " Integers");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bubble Sort algorithm to sort an integer array
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort algorithm to sort an integer array
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }


        try {
            // Create an ArrayList to store the strings from the file
            ArrayList<String> stringsList = new ArrayList<>();

            // Read the strings from the file and populate the ArrayList
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\cebce\\Connors Files\\Coding\\CS 2050\\Unfinished\\Project6\\StringsInFIle"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringsList.add(line);
            }
            reader.close();

            // Create two string arrays of 10,000 elements
            String[] bubbleSortArray = new String[stringsList.size()];
            String[] selectionSortArray = new String[stringsList.size()];
            String[] insertionSortArray = new String[stringsList.size()];

            // Copy the elements from the ArrayList to the arrays
            stringsList.toArray(bubbleSortArray);
            stringsList.toArray(selectionSortArray);
            stringsList.toArray(insertionSortArray);

            // Measure the time for Bubble Sort
            long bubbleSortStartTime = System.nanoTime();
            bubbleSort(bubbleSortArray);
            long bubbleSortEndTime = System.nanoTime();
            long bubbleSortTime = bubbleSortEndTime - bubbleSortStartTime;
            System.out.println("Bubble Sort Time for Strings (nanoseconds): " + bubbleSortTime);
            System.out.println("To sort " + stringsList.size() + " Strings");

            // Measure the time for Selection Sort
            long selectionSortStartTime = System.nanoTime();
            selectionSort(selectionSortArray);
            long selectionSortEndTime = System.nanoTime();
            long selectionSortTime = selectionSortEndTime - selectionSortStartTime;
            System.out.println("Selection Sort Time for Strings (nanoseconds): " + selectionSortTime);
            System.out.println("To sort " + stringsList.size() + " Strings");

            // Sort the ArrayList using Collections.sort
            long collectionSortStartTime = System.nanoTime();
            Collections.sort(stringsList);
            long collectionSortEndTime = System.nanoTime();
            long collectionSortTime = collectionSortEndTime - collectionSortStartTime;
            System.out.println("Collections.sort Time for Strings (nanoseconds): " + collectionSortTime);
            System.out.println("To sort " + stringsList.size() + " Strings");

            // Sort the ArrayList using Insertion Sort
            long insertionSortStartTime = System.nanoTime();
            insertionSort(insertionSortArray);
            long insertionSortEndTime = System.nanoTime();
            long insertionSortTime = insertionSortEndTime - insertionSortStartTime;
            System.out.println("insertionSort Time for Strings (nanoseconds): " + insertionSortTime);
            System.out.println("To sort " + stringsList.size() + " Strings");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // Bubble Sort algorithm to sort a string array
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // Swap arr[j] and arr[j+1]
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort algorithm to sort a string array
    public static void selectionSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void insertionSort(String[] arr) {
        int n = arr.length;
        int intCount = 0;
        for (int i = 1; i < n; i++) {
            String key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            // Check if the element is an integer
            try {
                Integer.parseInt(key);
                intCount++;
            } catch (NumberFormatException e) {
                // Not an integer
            }
        }
    }

}

