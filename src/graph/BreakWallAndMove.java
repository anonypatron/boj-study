package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallAndMove {
//    static class Point {
//        int x, y;
//        Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
    static class Person {
        int x, y, count;
        boolean destroyed;
        public Person (int x, int y, int count, boolean destroyed) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.destroyed = destroyed;
        }
    }
    static boolean [][][]visited;
    static int [][]matrix;
    static int []dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M][2];
        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        System.out.print(bfs(new Person(0, 0, 1, false)));
    }

    private static int bfs(Person person) {
        Queue<Person> q = new LinkedList<>();
        visited[0][0][0] = true;
        q.add(person);

        while (!q.isEmpty()) {
            Person p = q.poll();

            if (p.x == N - 1 && p.y == M - 1) return p.count;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                /*
                    1. 벽일 때와 벽이 아닐 때를 나누너서 계산
                        2. 벽을 부쉈을 때와 부수지 않았을 때를 나누어서 돌리기
                 */
                if (checkRange(nx, ny)) { // 범위 안에 들어옴
                    if (matrix[nx][ny] == 0) { // 벽이 아님
                        if (!p.destroyed) { // 아직 부순적이 없음
                            visited[nx][ny][0] = true;
                        }
                        else { // 이미 한 번 부숨
                            visited[nx][ny][1] = true;
                        }
                        q.add(new Person(nx, ny, p.count + 1, p.destroyed));
                    }
                    else if (matrix[nx][ny] == 1) { // 벽이 아님
                        if (!p.destroyed) { // 아직 부수적이 없음
                            q.add(new Person(nx, ny, p.count + 1, true));
                            visited[nx][ny][1] = true;
                        }
                        // 이미 부순적이 있으면 못 부수기 때문에 넘어가기
                    }
                } // end if
            } // end for
        } // end while
        return -1;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
