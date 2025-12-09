package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AandB2 {
    static boolean flag;
    static String target;
    static int len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        len = target.length();
        StringBuilder sb = new StringBuilder(br.readLine());

        find(sb);
        System.out.println(flag ? 1 : 0);
    }

    private static void find(StringBuilder cur) {
        if (flag) return;

        if (len == cur.length()) {
            if (cur.toString().equals(target)) {
                flag = true;
            }
            return;
        }

        if (cur.charAt(cur.length() - 1) == 'A' && cur.charAt(0) == 'B') { // 둘 다 가능한 경우
            cur.deleteCharAt(cur.length() - 1);
            find(cur);
            cur.append('A');

            cur.reverse().deleteCharAt(cur.length() - 1);
            find(cur);
            cur.append('B').reverse();
        }
        else if (cur.charAt(cur.length() - 1) == 'A') { // 첫 번째 연산만 가능한 경우
            cur.deleteCharAt(cur.length() - 1);
            find(cur);
            cur.append('A');
        }
        else if (cur.charAt(0) == 'B') { // 두 번쨰 연산만 가능한 경우
            cur.reverse().deleteCharAt(cur.length() - 1);
            find(cur);
            cur.append('B').reverse();
        }
    }

}
