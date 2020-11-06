
/**
 * The {@code Insertion Sort} algorithm separates the array into two sections:
 * 1) A section with sorted elements.
 * 2) A section with unsorted elements.
 * 
 * The algorithm works by progressively inserting one element from the
 * unsorted section and sorting that element into the sorted section.
 * 
 * The worst-case time complexity of this algorithm is {@code O(n^2)}
 */
public class InsertionSort {
    
    /**
     * Sorts an array of integers using the Insertion Sort algorithm
     * @param array The array to be sorted
     * @return A sorted array
     */
    public static int[] sort(int[] array) {
        
        // Sort all the elements of the array one-by-one,
        // starting with the 2nd element.
        for (int i = 1; i < array.length; i++) {
            // The `key` element will be checked against
            // all the elements that precede it, until
            // an element that is smaller that it is found.
            int key = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (key >= array[j]) break;
                
                // Swaps the (i-j)th element with
                // the key element.
                array[j + 1] = array[j];
                array[j] = key;
            }
        }

        // Returns a sorted array
        return array;
    }
}
