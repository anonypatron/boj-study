package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Card {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Long []arr = new Long[n];
        Long []count = new Long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);

        Long prev = arr[0];
        count[0] = 1L;

        int maxIndex = 0;
        Long value = 1L;

        for (int i = 1; i < n; i++) {
            if (arr[i - 1].equals(arr[i])) {
                count[i] = count[i - 1] + 1;
            }
            else {
                count[i] = 1L;
            }

            if (value < count[i]) {
                value = count[i];
                maxIndex = i;
            }
        }

        System.out.print(arr[maxIndex]);
    }
}
