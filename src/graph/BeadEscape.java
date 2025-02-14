package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BeadEscape {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Bead {
        Point red, blue;
        int count;

        public Bead(Point red, Point blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    static Point goal;
    static char[][] matrix;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        visited = new boolean[N][M][N][M];

        Point redBead = null, blueBead = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                matrix[i][j] = str.charAt(j);
                if (matrix[i][j] == 'R') {
                    redBead = new Point(i, j);
                } else if (matrix[i][j] == 'B') {
                    blueBead = new Point(i, j);
                } else if (matrix[i][j] == 'O') {
                    goal = new Point(i, j);
                }
            }
        }

        System.out.print(bfs(redBead, blueBead));
    }

    private static int bfs(Point red, Point blue) {
        Queue<Bead> queue = new LinkedList<>();
        queue.add(new Bead(red, blue, 1));
        visited[red.x][red.y][blue.x][blue.y] = true;

        while (!queue.isEmpty()) {
            Bead bead = queue.poll();
            if (bead.count > 10) {
                break; // 10번 초과 시 실패
            }

            for (int i = 0; i < 4; i++) {
                Point[] moved = move(bead.red, bead.blue, i);
                Point newRed = moved[0];
                Point newBlue = moved[1];

                // 파란 구슬이 구멍에 빠지면 실패
                if (newBlue == null) continue;
                // 빨간 구슬만 구멍에 빠지면 성공
                if (newRed == null) return bead.count;

                // 방문하지 않은 경우만 추가
                if (!visited[newRed.x][newRed.y][newBlue.x][newBlue.y]) {
                    visited[newRed.x][newRed.y][newBlue.x][newBlue.y] = true;
                    queue.add(new Bead(newRed, newBlue, bead.count + 1));
                }
            }
        }

        return -1;
    }

    private static Point[] move(Point red, Point blue, int dir) {
        int redX = red.x, redY = red.y;
        int blueX = blue.x, blueY = blue.y;

        boolean redGoal = false, blueGoal = false;

        // 빨간 구슬 이동
        while (matrix[redX + dx[dir]][redY + dy[dir]] != '#' && matrix[redX][redY] != 'O') {
            redX += dx[dir];
            redY += dy[dir];
            if (matrix[redX][redY] == 'O') {
                redGoal = true;
                break;
            }
        }

        // 파란 구슬 이동
        while (matrix[blueX + dx[dir]][blueY + dy[dir]] != '#' && matrix[blueX][blueY] != 'O') {
            blueX += dx[dir];
            blueY += dy[dir];
            if (matrix[blueX][blueY] == 'O') {
                blueGoal = true;
                break;
            }
        }

        // 파란 구슬이 구멍에 빠지면 실패
        if (blueGoal) return new Point[]{null, null};
        // 빨간 구슬이 구멍에 빠지면 성공
        if (redGoal) return new Point[]{null, new Point(blueX, blueY)};

        // 같은 위치에 있을 경우 처리
        if (redX == blueX && redY == blueY) {
            if (dir == 0) { // 위쪽
                if (red.x < blue.x) blueX++;
                else redX++;
            } else if (dir == 1) { // 아래쪽
                if (red.x > blue.x) blueX--;
                else redX--;
            } else if (dir == 2) { // 왼쪽
                if (red.y < blue.y) blueY++;
                else redY++;
            } else { // 오른쪽
                if (red.y > blue.y) blueY--;
                else redY--;
            }
        }

        return new Point[]{new Point(redX, redY), new Point(blueX, blueY)};
    }

}
