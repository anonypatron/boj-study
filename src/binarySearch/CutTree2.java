package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CutTree2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int []trees = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        int left = 0, right = trees[n - 1], answer = 0;
        while (left <= right) {
            int middle = left + (right - left) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (trees[i] > middle) {
                    sum += trees[i] - middle;
                }
            }

            if (sum >= m) {
                left = middle + 1;
                answer = middle;
            }
            else {
                right = middle - 1;
            }
        }

        System.out.print(answer);
    }

}
