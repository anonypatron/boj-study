package implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    1. 치킨집을 어떻게 고를 것인가?
    2. 치킨집을 m개까지 고르고 다 골랐으면 거리를 계산 -> 최솟값을 업데이트 한다.
 */
public class DeliveryChicken {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Point> houses = new ArrayList<>();
    static List<Point> chickens = new ArrayList<>();
    static boolean []visited;
    static int [][]matrix;
    static int N, M, answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 2) {
                    chickens.add(new Point(i, j));
                }
                else if (matrix[i][j] == 1) {
                    houses.add(new Point(i, j));
                }
            }
        }

        visited = new boolean[chickens.size()];
        find(0, 0);

        System.out.print(answer);
    }

    public static void find(int index, int depth) {
        if (depth == M) {
            calc();

            return;
        }

        for (int i = index; i < chickens.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                find(i + 1, depth + 1);
                visited[i] = false;
            }
        }

    }

    public static void calc() {
        int sum = 0;
        for (Point house : houses) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (visited[i]) {
                    int distance = Math.abs(house.x - chickens.get(i).x) + Math.abs(house.y - chickens.get(i).y);
                    min = Math.min(min, distance);
                }
            }
            sum += min;
        }

        answer = Math.min(sum, answer);
    }

}
