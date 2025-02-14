package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ToEatOrToEeEaten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int []a = new int[n];
            int []b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a);
            Arrays.sort(b);

            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += find(a, b, i);
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }

    public static int find(int []a, int []b, int i) {
        int left = 0, right = b.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (b[mid] < a[i]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return left;
    }

}
