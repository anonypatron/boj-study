package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Good {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int []goods = new int[n];
        for (int i = 0; i < n; i++) {
            goods[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(goods);

        int answer = 0;

        if (goods.length < 3) {
            System.out.print(answer);
            return;
        }

        for (int i = 0; i < goods.length; i++) {
            if (isGood(goods, i)) {
                answer++;
            }
        }

        System.out.print(answer);
    }

    /*
        음수인 경우도 있음
     */
    public static boolean isGood(int []goods, int targetIndex) {
        int left = 0, right = goods.length - 1;

        while (left < right) {
            if (left == targetIndex) {
                left++;
                continue;
            }
            if (right == targetIndex) {
                right--;
                continue;
            }

            int currentSum = goods[left] + goods[right];

            if (currentSum == goods[targetIndex]) {
                return true;
            }

            if (currentSum > goods[targetIndex]) {
                right--;
            }
            else {
                left++;
            }

        }

        return false;
    }

}
