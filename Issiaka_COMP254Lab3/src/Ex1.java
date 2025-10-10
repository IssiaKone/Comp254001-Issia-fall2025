import java.util.Scanner;

/**
 * COMP-254 Lab 3 – Exercise 1
 * Recursive product of two positive integers using only addition and subtraction.
 */
 class RecursiveProduct {
    public static int product(int m, int n) {
        if (n == 0) return 0;
        return m + product(m, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first positive integer (m): ");
        int m = sc.nextInt();
        System.out.print("Enter second positive integer (n): ");
        int n = sc.nextInt();

        if (m < 0 || n < 0) {
            System.out.println("Please enter non-negative integers only.");
            return;
        }

        int result = product(m, n);
        System.out.printf("The product of %d and %d is %d%n", m, n, result);
        sc.close();
    }
}
