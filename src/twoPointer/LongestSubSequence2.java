package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    완성 못함
 */
public class LongestSubSequence2 {
    static int []result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []array = new int[n];
        result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = 1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }



        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, result[i]);
        }

        System.out.print(max);
    }

}
