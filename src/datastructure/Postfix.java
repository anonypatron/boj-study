package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. 알파벳이면 그냥 넣기
 * 2. 연산자가 나왔을 때
 *  2-1. 스택에 있는 연산자보다 우선순위가 높으면 스택에 넣음
 *  2-2. 스택에 있는 연산자보다 우선순위가 낮거나 같으면 스택에서 pop하고 스택에 넣기
 * 3. 다 끝나면 스택에 있는 내용 모두 pop
 *
 * 단, 오른쪽 괄호를 만나면 왼쪽 괄호 전까지의 내용을 pop, 왼쪽괄호 버리기
 * 괄호 풀어주기
 */
public class Postfix {
    static class Operator {
        char operator;
        int priority;
        Operator(char operator, int priority) {
            this.operator = operator;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Operator> stack = new ArrayDeque<>();
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if ('A' <= c && c <= 'Z') {
                sb.append(c);
                continue;
            }

            Operator current = new Operator(c, getPriority(c));

            if (current.operator == ')') {
                while (!stack.isEmpty() && stack.peekLast().operator != '(') {
                    sb.append(stack.removeLast().operator);
                }
                stack.removeLast(); // '('
            }
            else {
                while (!stack.isEmpty() && stack.peekLast().operator != '(' && stack.peekLast().priority >= current.priority) {
                    sb.append(stack.removeLast().operator);
                }
                stack.addLast(current);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast().operator);
        }

        System.out.print(sb);
    }

    private static int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        return 3;
    }

}
