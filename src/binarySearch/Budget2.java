package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Budget2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int []locals = new int[n];

        for (int i = 0; i < n; i++) {
            locals[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        Arrays.sort(locals);

        int left = 0, right = locals[locals.length - 1], answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long figures = 0;

            for (int i = 0; i < n; i++) {
                if (mid >= locals[i]) {
                    figures += locals[i];
                }
                else {
                    figures += mid;
                }
            }

            if (figures > m) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
                answer = mid;
            }

        }

        System.out.print(answer);
    }
}
