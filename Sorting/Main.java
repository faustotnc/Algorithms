import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        
        int[] unsorted = {3, 6, -4, 0, 3, -5, 1, 2, 8, 22, 8, 12};
        int[] sorted = SelectionSort.sort(unsorted);

        System.out.println(Arrays.toString(sorted));
    }
}
