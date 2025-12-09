package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Top {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<int []> dq = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        int []array = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int index = n;
        int []result = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            while (!dq.isEmpty() && dq.peekLast()[1] <= array[i]) {
                result[dq.pollLast()[0]] = i + 1;
            }

            dq.addLast(new int[] { index--, array[i]});
        }

        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append(' ');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
