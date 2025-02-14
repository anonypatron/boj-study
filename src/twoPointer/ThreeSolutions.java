package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThreeSolutions {
    static long []answers = new long[3];
    static long diff = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long []solutions = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(solutions);

        for (int i = 0; i < n - 2; i++) {
            find(solutions, i);
        }

        for (long answer : answers) {
            System.out.print(answer + " ");
        }

    }

    public static void find(long[] solutions, int middleIndex) {
        int leftIndex = middleIndex + 1, rightIndex = solutions.length - 1;

        while (leftIndex < rightIndex) {
            long sum = solutions[leftIndex] + solutions[rightIndex] + solutions[middleIndex];

            if (Math.abs(sum) < diff) {
                answers[0] = solutions[middleIndex];
                answers[1] = solutions[leftIndex];
                answers[2] = solutions[rightIndex];
                diff = Math.abs(sum);
            }

            if (sum > 0) {
                rightIndex--;
            }
            else {
                leftIndex++;
            }
        }

    }

}
