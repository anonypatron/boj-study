package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Dummy {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Snake {
        ArrayDeque<Point> route =  new ArrayDeque<>();
        int direction;

        public Snake(int direction) {
            this.direction = direction;
        }
    }
    static class Command {
        int time;
        char cmd;

        public Command(int time, char cmd) {
            this.time = time;
            this.cmd = cmd;
        }
    }

    static int [][]matrix;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        int k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            matrix[x][y] = 2;
        }

        ArrayDeque<Command> commandQueue = new ArrayDeque<>();
        int l = Integer.parseInt(br.readLine());
        while (l-- > 0) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            commandQueue.addLast(new Command(time, c));
        }

        // 뱀 : 1 사과 : 2
        Snake snake = new Snake(1);
        snake.route.addLast(new Point(0,0));
        matrix[0][0] = 1;

        int result = 0;
        while (!conflict(snake)) {
            Point next = getNextPoint(snake);

            if (matrix[next.x][next.y] == 0) { // 사과가 아니면
                Point p = snake.route.removeLast();
                matrix[p.x][p.y] = 0;
            }
            snake.route.addFirst(new Point(next.x, next.y));
            matrix[next.x][next.y] = 1;
            result++;

            if (!commandQueue.isEmpty() && commandQueue.peekFirst().time == result) {
                rotate(snake, commandQueue.pollFirst().cmd);
            }
        }

        System.out.print(result + 1);
    }

    private static void rotate(Snake snake, char direction) {
        if (direction == 'D') {
            snake.direction = (snake.direction + 1) % 4;
        }
        else {
            snake.direction = (snake.direction + 3) % 4;
        }
    }

    private static boolean conflict(Snake snake) {
        Point next = getNextPoint(snake);
        return next.x < 0 || next.y < 0 || next.x >= N || next.y >= N || matrix[next.x][next.y] == 1;
    }

    private static Point getNextPoint(Snake snake) {
        Point point;

        switch(snake.direction) {
            case 0:
                point = snake.route.peekFirst();
                return new Point(point.x - 1, point.y);
            case 1:
                point = snake.route.peekFirst();
                return new Point(point.x, point.y + 1);
            case 2:
                point = snake.route.peekFirst();
                return new Point(point.x + 1, point.y);
            case 3:
                point = snake.route.peekFirst();
                return new Point(point.x, point.y - 1);
        }
        return null;
    }

}
