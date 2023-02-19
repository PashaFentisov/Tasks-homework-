package Task2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int size = scan.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        Arrays.sort(arr);
        System.out.print("Enter the index: ");
        int k = scan.nextInt();
        if (k < 0 || k >= size) {
            throw new IllegalArgumentException();
        }
        System.out.println("Array - " + Arrays.toString(arr));
        System.out.println("Element - " + arr[k]);
    }
}
