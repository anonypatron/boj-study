package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfIntervals {
    static int [][]dp, matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sumOfInterval();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(find(x1 - 1, y1 - 1, x2 - 1, y2 - 1)).append("\n");
        }

        System.out.print(sb);
    }

    public static int find(int x1, int y1, int x2, int y2) {
        int sum = 0;
        for (int i = x1; i <= x2; i++) {
            sum += dp[i][y2] - dp[i][y1] + matrix[i][y1];
        }
        return sum;
    }

    public static void sumOfInterval() {
        for (int i = 0; i < matrix.length; i++) {
            int currentSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                currentSum += matrix[i][j];
                dp[i][j] = currentSum;
            }
        }
    }

}
