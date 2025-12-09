package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hansoo { // 1065
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n < 100) {
            System.out.print(n);
            return;
        }

        int result = 99;
        for (int i = 100; i <= n; i++) {
            if (isHansoo(i + "")) {
                result++;
            }
        }

        System.out.print(result);
    }

    private static boolean isHansoo(String str) {
        int diff = Character.getNumericValue(str.charAt(1)) - Character.getNumericValue(str.charAt(0));
        for (int i = 1; i < str.length() - 1; i++) {
            if (Character.getNumericValue(str.charAt(i + 1)) - Character.getNumericValue(str.charAt(i)) != diff) {
                return false;
            }
        }
        return true;
    }

}
