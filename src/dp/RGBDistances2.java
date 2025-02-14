package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGBDistances2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [][]arr = new int[n][3];
        int [][]dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.arraycopy(arr[0], 0, dp[0], 0, 3);

        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        dp[i][j] = Math.max(arr[i - 1][j + 1] + arr[i + 1][j + 2], arr[i - 1][j + 2] + arr[i + 1][j + 1]) + arr[i][j];
                        break;
                    case 1:
                        dp[i][j] = Math.max(arr[i - 1][j - 1] + arr[i + 1][j + 1], arr[i - 1][j + 1] + arr[i + 1][j - 1]) + arr[i][j];
                        break;
                    case 2:
                        dp[i][j] = Math.max(arr[i - 1][j - 2] + arr[i + 1][j - 1], arr[i - 1][j - 1] + arr[i + 1][j - 2]) + arr[i][j];
                        break;
                }
            }
        }

    }

}
