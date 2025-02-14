package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSolutions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int []solutions = new int[n];
        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions);

        int answerA = 0, answerB = 0;
        int currentMin = Integer.MAX_VALUE;
        int leftIndex = 0, rightIndex = n - 1;

        while (leftIndex < rightIndex) {
            int sum = solutions[leftIndex] + solutions[rightIndex];

            if (Math.abs(sum) < currentMin) {
                answerA = solutions[leftIndex];
                answerB = solutions[rightIndex];
                currentMin = Math.abs(sum);
            }

            if (sum > 0) {
                rightIndex--;
            }
            else {
                leftIndex++;
            }

        }

        System.out.print(answerA + " " + answerB);
    }
}
