package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfPart {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int []arr = new int[n];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int sum = 0, result = Integer.MAX_VALUE;

        while (right < n) {
            sum += arr[right];

            while (sum >= s) {
                result = Math.min(result, arr[right]);
                sum -= arr[left];
                left++;
            }

            right++;
        }

        System.out.print(result == Integer.MAX_VALUE ? 0 : result);
    }

}
