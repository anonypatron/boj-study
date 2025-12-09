package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Parentheses { // 9012
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            boolean flag = false;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    stack.push(str.charAt(i));
                }
                else {
                    if (stack.isEmpty()) {
                        flag = true;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
            }

            if (!flag) {
                sb.append(stack.isEmpty() ? "YES" : "NO").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }

        }

        System.out.print(sb);
    }

}
