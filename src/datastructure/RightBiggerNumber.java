package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RightBiggerNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<int []> dq = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        int []result = new int[n];

        Arrays.fill(result, -1);
        int index = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (n-- > 0) {
            int input = Integer.parseInt(st.nextToken());

            while (!dq.isEmpty() && dq.peekLast()[1] < input) {
                result[dq.pollLast()[0]] = input;
            }

            dq.addLast(new int[] {index++, input});
        }

        for (Integer i : result) {
            sb.append(i).append(" ");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
