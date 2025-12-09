package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SnakeAndLadderGame {
    static int []dx = {1, 2, 3, 4, 5, 6};
    static int []board, dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        board = new int[101];
        dist = new int[101];

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            board[start] = end;
        }

        System.out.print(bfs());
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int next = current + dx[i];
                if (next > 100) {
                    continue;
                }

                next = board[next];

                if (dist[next] == 0) {
                    dist[next] = dist[current] + 1;
                    q.add(next);

                    if (next == 100) {
                        return dist[next];
                    }
                }
            }
        }

        return -1;
    }

}
