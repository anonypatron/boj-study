package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutLanCable2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int []cables = new int[n];
        long left = 1, right = Long.MIN_VALUE;
        long answer = 0;

        for (int i = 0; i < n; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, cables[i]);
        }

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long count = 0;
            for (int cable : cables) {
                count += cable / mid;
            }

            if (count >= k) {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

}
