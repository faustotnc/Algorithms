
/**
 * The selection sort algorithm separates the array into two sections:
 * 1) A section with sorted elements
 * 2) A section with unsorted elements
 * 
 * The algorithm works by looking for the smallest item in the
 * unsorted portion or the array, and placing that item at the
 * end of the sorted portion.
 * 
 * The worst-case time complexity of Selection Sort is O(n^2)
 */
public class SelectionSort {

    /**
     * Sorts an array of integers using the `Selection` algorithm.
     * @param array The array to be sorted
     * @return A sorted array
     */
    public static int[] sort(int[] array) {
        // Loop through the elements of the list, except
        // for the last element since at that point the
        // list will already be sorted.
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;

            // Look for the smallest item in the rest of the array
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
            }
            
            // Place the new smallest item at the
            // top of the sorted list
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }

        // Return the sorted list
        return array;
    }
}
