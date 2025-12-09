package cumulativeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sequence { // 2559
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int arr[] = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int current = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            current += arr[i];
            max = current;
        }

        for (int i = 1; i <= n - k; i++) {
            current = current + arr[i + k - 1] - arr[i - 1];
            max = Math.max(max, current);
        }

        System.out.println(max);
    }

}
