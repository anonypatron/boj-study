package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class MakeBigger { // 2812
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        Deque<Integer> stack = new ArrayDeque<>();
        int removeCount = 0;

        for (int i = 0; i < N; i++) {
            int x = str.charAt(i) - '0';

            if (stack.isEmpty()) {
                stack.addLast(x);
                continue;
            }

            while (!stack.isEmpty() && stack.peekLast() < x && removeCount < K) {
                stack.removeLast();
                removeCount++;
            }

            stack.addLast(x);
        }

        while (removeCount < K) {
            stack.removeLast();
            removeCount++;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }

        System.out.print(sb);
    }

}
