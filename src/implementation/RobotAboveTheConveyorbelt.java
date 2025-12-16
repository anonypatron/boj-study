package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RobotAboveTheConveyorbelt { // 20055
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Conveyor {
        Point point;
        int durability;
        boolean existsRobot;

        public Conveyor(Point point, int durability, boolean existsRobot) {
            this.point = point;
            this.durability = durability;
            this.existsRobot = existsRobot;
        }
    }
    static Conveyor[][] conveyor; // 2차원 배열에 넣고 돌리기
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyor = new Conveyor[2][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            conveyor[0][i] = new Conveyor(new Point(0, i), Integer.parseInt(st.nextToken()), false);
        }
        for (int i = N - 1; i >= 0; i--) {
            conveyor[1][i] = new Conveyor(new Point(1, i), Integer.parseInt(st.nextToken()), false);
        }

        int result = 0;
        while (checkFinish()) {
            rotate();
            move();
            putRobot();
            result++;
        }

        System.out.print(result);
    }

    private static void putRobot() {
        if (conveyor[0][0].durability > 0) {
            conveyor[0][0].existsRobot = true;
            conveyor[0][0].durability--;
        }
    }

    private static void move() {
        for (int i = N - 2; i >= 0; i--) {
            Conveyor current =  conveyor[0][i];
            Conveyor next  =  conveyor[0][i + 1];

            if (current.existsRobot && !next.existsRobot && next.durability > 0) {
                current.existsRobot = false;
                next.existsRobot = true;
                next.durability--;
            }
        }
        conveyor[0][N - 1].existsRobot = false;
    }

    private static void rotate() {
        Deque<Conveyor> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            dq.addLast(conveyor[0][i]);
        }
        for (int i = N - 1; i >= 0; i--) {
            dq.addLast(conveyor[1][i]);
        }
        dq.addFirst(dq.pollLast());

        for (int i = 0; i < N; i++) {
            conveyor[0][i] = dq.pollFirst();
        }
        for (int i = N - 1; i >= 0; i--) {
            conveyor[1][i] = dq.pollFirst();
        }

        conveyor[0][N - 1].existsRobot = false;
    }

    private static boolean checkFinish() {
        int zeroCount = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                if (conveyor[i][j].durability == 0) {
                    zeroCount++;
                }
            }
        }
        return zeroCount < K;
    }

}
