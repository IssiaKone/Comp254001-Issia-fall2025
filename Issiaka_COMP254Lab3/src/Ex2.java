import java.util.Scanner;

/**
 * COMP-254 Lab 3 – Exercise 2
 * Recursive palindrome checker.
 */
class PalindromeChecker {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("\\s+", "");
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word or phrase: ");
        String input = sc.nextLine();
        boolean result = isPalindrome(input);

        if (result)
            System.out.println("'" + input + "' is a palindrome.");
        else
            System.out.println("'" + input + "' is not a palindrome.");
        sc.close();
    }
}
