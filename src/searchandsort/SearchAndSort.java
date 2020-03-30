package searchandsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchAndSort {

    private static Random rng = new Random();
    private static final int SIZE_THRESHOLD = 12;

    /**
     * The "makeList" method is used to create the list
     * that is used in all of the of the following methods
     * @param size Number of values in the list
     * @return     The completed list
     */
    public static List<Integer> makeList(int size) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int n = 10 + rng.nextInt(90);
            result.add(n);
        } // for

        return result;
    } // makeList( int )
    
    /**
     * The "printList" method converts the list created
     * by "makeList" into a string and prints that string.
     * @param values Numbers in the list
     */

    public static void printList(List<Integer> values) {
        if (values.size() < SIZE_THRESHOLD) {
            for (int n : values) {
                System.out.printf("%4d", n);
            } // for
            System.out.println();
        } // if
        else {
            for (int n : values) {
                System.out.printf("%4d\n", n);
            } // for
        } // else
    } // printList( List<Integer> )


    /**
     * The "linearSearch" method searches the list created
     * by "makeList" for a specified value.
     * This method searches for the specified value by starting
     * at the first value in the list comparing it to the
     * specified value and adding 1 to the value of the variable index
     * this is repeated until all values in the list have been checked.
     * The method then returns the result.
     * If no values match the specified value the method returns -1.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     * @param target Specified value
     * @return       The number of instances of target
     */
    
    public static int linearSearch(List<Integer> values,
            int target) {
        int result = -1;

        int index = 0;
        while (index < values.size() && result < 0) {
            if (target == values.get(index)) {
                result = index;
            } // if
            index = index + 1;
        } // while
        return result;
    } // linearSearch( List<Integer>, int )

    
    
    
    /**
     * The "binarySearch" method searches the list created
     * by "makeList" for a specified value.
     * This method searches for the specified value by starting
     * at the middle value in the sorted list and compares it to
     * the specified value then the list is divided in half depending
     * on if the specified value is greater than or less than the
     * selected value.
     * This is repeated until either only the specified value remains or
     * nothing remains.
     * The method then returns the result.
     * If no values match the specified value the method returns -1. 
     * @param values Numbers in the list
     * @param target Specified value
     * @return       The number of instances of the target
     */
    
    public static int binarySearch(List<Integer> values,
            int target) {
        int result = -1;

        int lo = 0;
        int hi = values.size() - 1;

        while (lo < hi && result < 0) {
            int mid = (lo + hi) / 2;
            if (target == values.get(lo)) {
                result = lo;
            } // if
            else if (target == values.get(mid)) {
                result = mid;
            } // else if
            else if (target == values.get(hi)) {
                result = hi;
            } // else if
            else if (target < values.get(mid)) {
                hi = mid - 1;
            } // else if
            else {
                lo = mid + 1;
            } // else
        } // while

        return result;
    } // binarySearch( List<Integer>, int )
    
    /**
     * The "swap" method is used to swap two selected values.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     * @param i Value number one
     * @param j Value number two
     */
    
    public static void swap(List<Integer> values, int i, int j) {
        int temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    } // swap( List<Integer>, int, int )
    
    /**
     * The "findPosMin" method is used to find the position of the minimum 
     * value in a list.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     * @param start The starting value position
     * @return      The position of the current assumed minimum value
     */
    
    public static int findPosMin(List<Integer> values, int start) {
        int bestGuessSoFar = start;
        for (int i = start + 1; i < values.size(); i++) {
            if (values.get(i) < values.get(bestGuessSoFar)) {
                bestGuessSoFar = i;
            } // if
        } // for
        return bestGuessSoFar;
    } // findPosMin( List<Integer>, int )
    
    /**
     * The "selectionSort" method sorts a list of values.
     * The method does this by selecting the minimum unsorted value
     * and swapping it to the correct position.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     */
    
    public static void selectionSort(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            int j = findPosMin(values, i);
            swap(values, i, j);
        } // for
    } // selectionSort( List<Integer> 
    
    /**
     * The "insert" method is used to insert the value from one position into
     * the position of another value.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     * @param next The next value
     */
    
    public static void insert(List<Integer> values, int next) {

        int i = next;
        while (i > 0 && values.get(i) < values.get(i - 1)) {
            swap(values, i, i - 1);
            i = i - 1;
        } // while

    } // insert( List<Integer>, int 
    
    /** 
     * The "insertionSort" method sorts a list of values.
     * The method does this by selecting a value and the comparing it
     * to the value left fo the selected value.
     * If the value is larger that the selected value than in moves farther
     * left in the list.
     * If the selected value is lower or there are no more values to the
     * left in the list than the selected value is inserted into the
     * position new position.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     */

    public static void insertionSort(List<Integer> values) {
        for (int i = 1; i < values.size(); i++) {
            insert(values, i);
        } // for
    } // insertionSort( List<Integer> )
    
    /**
     * The "merge" method takes two halves of a list and merges them into
     * one list.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     * @param prefixStart Start of the first half of the list
     * @param suffixStart Start of the second half of the list
     * @param suffixEnd End of the list
     */

    public static void merge(List<Integer> values, int prefixStart,
            int suffixStart, int suffixEnd) {
        List<Integer> temp = new ArrayList<>();

        int i = prefixStart;
        int j = suffixStart;

        while (i < suffixStart && j < suffixEnd) {
            if (values.get(i) < values.get(j)) {
                temp.add(values.get(i));
                i++;
            } // if
            else {
                temp.add(values.get(j));
                j++;
            } // else
        } // while

        while (i < suffixStart) {
            temp.add(values.get(i));
            i++;
        } // while

        while (j < suffixEnd) {
            temp.add(values.get(j));
            j++;
        } // while

        i = prefixStart;
        for (int index = 0; index < temp.size(); index++) {
            values.set(i, temp.get(index));
            i++;
        } // for
    } // merge( List<Integer>, int, int )
    
    /**
     * The "mergeSort" method sorts a list of values.
     * The method does this by dividing a list into smaller and smaller
     * pieces and then rebuilding the list incrementally while comparing
     * sorting the values at each stage of the rebuilding process.
     * Code from https://github.com/leontabak/
     * csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
     * @param values Numbers in the list
     */

    public static void mergeSort(List<Integer> values) {
        for (int stepSize = 2; stepSize < values.size(); stepSize *= 2) {
            for (int i = 0; i < values.size(); i += stepSize) {
                int prefixStart = i;
                int suffixStart = i + stepSize / 2;
                int suffixEnd = Math.min(values.size(), i + stepSize);
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // for
            if (stepSize > values.size() / 2) {
                int prefixStart = 0;
                int suffixStart = stepSize;
                int suffixEnd = values.size();
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // if
            //printList(values);
        } // for
    } // mergeSort( List<Integer> )

    
    public static void main(String[] args) {
        System.out.println("Searching and sorting algorithms");
        
        // The following code tests the sorting methods.
        // Code from https://github.com/leontabak/
        // csc144-ge0/blob/master/src/searchandsort/SearchAndSort.java
        
        System.out.println("Selection sort.");
        List<Integer> data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        selectionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Insertion sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        insertionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Merge sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        mergeSort(data);
        printList(data);
       
          
    } // main( String [] )
} // SearchAndSort
