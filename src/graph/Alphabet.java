package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {
    static char [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = str.charAt(j);
            }
        }

        /*
            비트 마스킹으로 풀 수 있음(비트 연산으로 int 하나로 boolean[26]대체 가능)
         */
        boolean [] alphabet = new boolean[26];
        alphabet[matrix[0][0] - 'A'] = true;

        System.out.print(dfs(0, 0, 1, alphabet));
    }

    private static int dfs(int x, int y, int depth, boolean []alphabet) {
        int maxCount = depth;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (checkRange(nx, ny) && !alphabet[matrix[nx][ny] - 'A']) {
                alphabet[matrix[nx][ny] - 'A'] = true;
                maxCount = Math.max(dfs(nx, ny, depth + 1, alphabet), maxCount);
                alphabet[matrix[nx][ny] - 'A'] = false;
            }
        }

        return maxCount;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}


