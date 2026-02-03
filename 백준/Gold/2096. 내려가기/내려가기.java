import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 2096
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            minDp[0][i] = arr[0][i];
            maxDp[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        minDp[i][j] = Math.min(minDp[i - 1][j], minDp[i - 1][j + 1]) + arr[i][j];
                        maxDp[i][j] = Math.max(maxDp[i - 1][j], maxDp[i - 1][j + 1]) + arr[i][j];
                        break;
                    case 1:
                        minDp[i][j] = Math.min(Math.min(minDp[i - 1][j - 1], minDp[i - 1][j]), minDp[i - 1][j + 1]) + arr[i][j];
                        maxDp[i][j] = Math.max(Math.max(maxDp[i - 1][j - 1], maxDp[i - 1][j]), maxDp[i - 1][j + 1]) + arr[i][j];
                        break;
                    case 2:
                        minDp[i][j] = Math.min(minDp[i - 1][j - 1], minDp[i - 1][j]) + arr[i][j];
                        maxDp[i][j] = Math.max(maxDp[i - 1][j - 1], maxDp[i - 1][j]) + arr[i][j];
                        break;
                }
            }
        }

        int max = Math.max(Math.max(maxDp[n - 1][0], maxDp[n - 1][1]), maxDp[n - 1][2]);
        int min = Math.min(Math.min(minDp[n - 1][0], minDp[n - 1][1]), minDp[n - 1][2]);

        System.out.printf("%d %d", max, min);
    }

}
