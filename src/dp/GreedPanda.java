package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedPanda {
    static int [][]arr, dp;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j);
            }
        }

        System.out.print(find());
    }

    public static int dfs(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (checkRange(newX, newY) && arr[newX][newY] > arr[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(newX, newY) + 1);
            }
        }

        return dp[x][y];
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < arr.length && y >= 0 && y < arr.length;
    }

    public static int find() {
        int max = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

}
