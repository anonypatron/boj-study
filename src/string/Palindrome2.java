package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    1. 찬스를 사용하지 않고 회문 -> 회문(0)
    2. 찬스를 사용하고 회문 -> 유사 회문(1)
    3. 나머지 -> (2)
 */
public class Palindrome2 { // 17609
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            sb.append(find(br.readLine())).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static int find(String str) {
        return Math.min(isPalindrome(str, 0), isPalindrome(str, 1));
    }

    private static int isPalindrome(String str, int state) {
        int left = 0, right = str.length() - 1;
        boolean flag = true;

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                if (!flag) { // 찬스를 쓰고 다른 경우
                    return 2;
                }

                if (state == 0) {
                    left++;
                }
                else {
                    right--;
                }
                flag = false;
            }
            else {
                left++;
                right--;
            }
        }

        return flag ? 0 : 1;
    }

}
