package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
    1. 숫자를 스택에 넣음
        1-1. 넣을 때 스택이 비어있지 않고 스택의 값이 넣으려고 하는 값보다 작으면 pop하고 넣음
        -> 앞에서 부터 작은 숫자들이 빠져나감
 */
public class MakeBig {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char []num = br.readLine().toCharArray();

        Stack<Integer> stack = new Stack<>();
        int idx = 0;

        for (char c : num) {
            int x = Character.getNumericValue(c);

            while (!stack.isEmpty() && stack.peek() < x && idx < k) {
                stack.pop();
                idx++;
            }
            stack.push(x);
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        System.out.print(sb.substring(0, num.length - k));
    }

}
