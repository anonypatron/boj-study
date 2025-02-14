package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LIS4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int []arr = new int[n];
        int []dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            int current = arr[i];
            for (int j = 0; j < i; j++) {
                if (current > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }
        sb.append(answer).append("\n");

        Stack<Integer> stack = new Stack<>();
        int target = answer, prev = n - 1;

        while (target != 0) {
            for (int index = prev; index >= 0; index--) {
                if (dp[index] == target) {
                    stack.push(arr[index]);
                    prev = index;
                    target--;
                    break;
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
