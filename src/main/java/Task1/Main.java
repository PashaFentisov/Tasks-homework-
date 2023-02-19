package Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter the value: ");
        int n = new Scanner(System.in).nextInt();
        long temp = n / 2;
        while (true) {
            if (temp * temp == n) {
                System.out.println(temp);
                break;
            }
            if (temp * temp > n) {
                temp = temp / 2;
            } else if (temp * temp < n) {
                while (true) {
                    temp++;
                    if (temp * temp > n) {
                        temp--;
                        System.out.println(temp);
                        return;
                    }
                    if (temp * temp == n) {
                        System.out.println(temp);
                        return;
                    }
                }
            }
        }
    }
}
