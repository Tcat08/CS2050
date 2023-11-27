/*
 * Name: Connor Byers
 * Prof: Blanche Cohen
 * Class: CS2050
 * Program 10 description: Timing sorting algorithms, including Bubble, Selection, insertion, and now Tim and Quick for both
 * 20000 integers
 * It's a copy of mostly Project7 with some added things which was a copy of Project6 with additions too
 * so the readers read from the same files still, I removed the String sorting though
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Program10 {
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
            int[] insertionSortArray = new int[numbersList.size()];
            int[] timSortArray = new int[numbersList.size()];
            int[] quickSortArray = new int[numbersList.size()];

            // Copy the elements from the ArrayList to the arrays
            for (int i = 0; i < numbersList.size(); i++) {
                bubbleSortArray[i] = numbersList.get(i);
                selectionSortArray[i] = numbersList.get(i);
                insertionSortArray[i] = numbersList.get(i);
                timSortArray[i] = numbersList.get(i);
                quickSortArray[i] = numbersList.get(i);
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

            // Measure the time for Insertion Sort
            long insertionSortStartTime = System.nanoTime();
            insertionSort(insertionSortArray);
            long insertionSortEndTime = System.nanoTime();
            long insertionSortTime = insertionSortEndTime - insertionSortStartTime;
            System.out.println("Insertion Sort Time for Integers (nanoseconds): " + insertionSortTime);
            System.out.println("To sort " + numbersList.size() + " Integers");

            //Measure the time for TimSort
            long timSortStartTime = System.nanoTime();
            timSort(timSortArray);
            long timSortEndTime = System.nanoTime();
            long timSortTime = timSortEndTime - timSortStartTime;
            System.out.println("TimSort Time (nanoseconds): " + timSortTime);
            System.out.println("To sort " + numbersList.size() + " Integers");

            //Measure the time for Quicksort
            long quickSortStartTime = System.nanoTime();
            quickSort(quickSortArray, 0, quickSortArray.length - 1);
            long quickSortEndTime = System.nanoTime();
            long quickSortTime = quickSortEndTime - quickSortStartTime;
            System.out.println("Quick Sort Time (nanoseconds): " + quickSortTime);
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
    public static void insertionSort(int[] arr) { //1 argument insertion is the one actually being tested
                                                  //The one with 3 arguments is for Timsort
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


    }

    public static void merge(int arr[], int l, int m, int r) { //This is for timsort, but
                                                               //I can also just measure merge sort with this
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temporary arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temporary arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temporary arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using merge()
    public static void mergeSort(int arr[], int l, int r) { //just in case I want to measure it in the future too
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Find pivot element such that:
            // elements smaller than pivot are on the left
            // elements greater than pivot are on the right
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Helper method to partition the array and return the index of the pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to the pivot
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void timSort(int[] arr) {
        int minRun = 32;
        int n = arr.length;

        // Sort individual subarrays of size minRun
        for (int i = 0; i < n; i += minRun) {
            int left = i;
            int right = Math.min((i + minRun - 1), (n - 1));
            insertionSort(arr, left, right);
        }

        // Start merging from size minRun (or 32 in this example)
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    // Helper method for insertion sort on subarrays
    private static void insertionSort(int[] arr, int left, int right) { //this is for timsort, so it can use a left and right int
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }


}

