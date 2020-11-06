
/**
 * The {@code Bubble Sort} algorithm loops through the given array
 * and checks the i-th element against the (i + 1)th element in the
 * array. If the i-th element is bigger than the (i + 1)th element,
 * then they get swapped. The process is repeated until sorted.
 * 
 * The worst-case time complexity of this algorithm is {@code O(n^2)}
 */
public class BubbleSort {
    
    /**
     * Sorts an array of integers using the Bubble Sort algorithm
     * @param array The array to be sorted
     * @return A sorted array
     */
    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // The Bubble Sort algorithm has the interesting property that the
            // biggest elements get automatically pushed to the end of the array, so
            // we don't need to check the (n - i) elements after every pass.
            for (int j = 0; j < array.length - i - 1; j++) {
                // Swaps the current element with the next elements if
                // the current element is larger than the next element
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        // Return the sorted array
        return array;
    }
}
