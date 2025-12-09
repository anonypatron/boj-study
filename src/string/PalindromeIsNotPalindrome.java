package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeIsNotPalindrome { // 15927
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(find(br.readLine()));
    }

    private static int find(String str) {
        if (isSameChar(str)) {
            return -1;
        }

        if (isPalindrome(str)) {
            return str.length() - 1;
        }

        return str.length();
    }

    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isSameChar(String str) {
        char c = str.charAt(0);

        for (int i = 1; i < str.length(); i++) {
            if (c != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
