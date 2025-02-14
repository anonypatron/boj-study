package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tomato {
    public static class Tensor {
        int x, y, z;
        Tensor(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static int [][][]matrix;
    static boolean [][][]visited;
    static int []dx = {-1, 0, 0, 1, 0, 0};
    static int []dy = {0, -1, 0, 0, 1, 0};
    static int []dz = {0, 0, -1, 0, 0, 1};
    static int N, M, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        matrix = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    matrix[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int result = 0;
        Queue<Tensor> tensorQ = getTomatoTensors();

        while(!tensorQ.isEmpty()) {
            if (isFinish()) {
                System.out.print(result);
                return;
            }

            bfs(tensorQ);
            result++;
        }

        System.out.print("-1");
    }

    public static void bfs(Queue<Tensor> tensorQ) {
        List<Tensor> list = new ArrayList<>();

        while (!tensorQ.isEmpty()) {
            Tensor t = tensorQ.poll();

            for (int i = 0; i < 6; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                int nz = t.z + dz[i];

                if (checkRange(nx, ny, nz) && !visited[nx][ny][nz] && matrix[nx][ny][nz] == 0) {
                    visited[nx][ny][nz] = true;
                    list.add(new Tensor(nx, ny, nz));
                }
            }
        }

        for (Tensor t : list) {
            matrix[t.x][t.y][t.z] = 1;
            tensorQ.add(t);
        }

    }

    public static boolean isFinish() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < H; k++) {
                    if (matrix[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkRange(int x, int y, int z) {
        return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
    }

    public static Queue<Tensor> getTomatoTensors() {
        Queue<Tensor> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < H; k++) {
                    if (matrix[i][j][k] == 1) {
                        q.add(new Tensor(i, j, k));
                    }
                }
            }
        }

        return q;
    }

}
