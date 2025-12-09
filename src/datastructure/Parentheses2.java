package datastructure;

import java.io.*;
import java.util.Stack;

public class Parentheses2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            String str = br.readLine();

            for  (int i = 0; i < str.length(); i++) {
                char c =  str.charAt(i);

                if (c == '(') {
                    stack.push(c);
                }
                else if (!stack.isEmpty() && c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                else {
                    flag = false;
                    break;
                }
            }

            if (flag && stack.isEmpty()) {
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
        }

        System.out.print(sb.deleteCharAt(sb.length()-1));
    }
}
