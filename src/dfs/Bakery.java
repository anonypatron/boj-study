package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bakery { // 3109
    static int [][]matrix;
    static int []dx = {-1, 0 ,1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 벽은 1
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '.') {
                    matrix[i][j] = 1;
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            if (dfs(i, 0)) {
                result++;
            }
        }

        System.out.print(result);
    }

    public static boolean dfs(int x, int y) {
        if (y == M - 1) {
            return true;
        }

        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + 1;

            if (checkRange(newX, newY) && matrix[newX][newY] == 1) {
                matrix[newX][newY] = 0;
                if (dfs(newX, newY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
