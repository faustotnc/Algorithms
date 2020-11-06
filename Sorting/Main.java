import java.util.Random;

public class Main {
    public static void main(String[] args) {
        
        // Randomly populates an array with 100,000
        // elements with range values in [-10000, 10000]
        int[] unsorted = new int[100000];
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            int neg = (r.nextInt(2) * -1);
            int num = r.nextInt(10000);
            unsorted[i] = (neg == 0) ?  num : num * -1;
        }

        // Execution time to sort the array using Bubble Sort
        int[] unsortedClone1 = unsorted.clone();
        long startTime1 = System.nanoTime();
        BubbleSort.sort(unsortedClone1);
        long endTime1 = System.nanoTime();
        System.out.println(((endTime1 - startTime1) / 1000000) + " MS");

        // Execution time to sort the array using Insertion Sort
        int[] unsortedClone3 = unsorted.clone();
        long startTime3 = System.nanoTime();
        InsertionSort.sort(unsortedClone3);
        long endTime3 = System.nanoTime();
        System.out.println(((endTime3 - startTime3) / 1000000) + " MS");

        // Execution time to sort the array using Selection Sort
        int[] unsortedClone2 = unsorted.clone();
        long startTime2 = System.nanoTime();
        SelectionSort.sort(unsortedClone2);
        long endTime2 = System.nanoTime();
        System.out.println(((endTime2 - startTime2) / 1000000) + " MS");
    }
}
