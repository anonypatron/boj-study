package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.*;

public class FishingKing {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Info {
        Point point;
        int dir;
        public Info(Point point, int dir) {
            this.point = point;
            this.dir = dir;
        }
    }
    static class Shark {
        int r, c, speed, direction, size;
        public Shark(int r, int c, int speed, int direction, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }
    static List<Shark> sharks = new ArrayList<>();
    static int[][] duplicate;
    static int R, C, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            sharks.add(new Shark(r, c, speed, direction, size));
        }

        int currentIdx = -1, sumOfSharkSizes = 0;
        while (currentIdx++ != C) {
            duplicate = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    duplicate[i][j] = -1;
                }
            }

            sumOfSharkSizes += fishing(currentIdx);
            move();
        }
        String str = Instant.now().toString().substring(0, 11);
        System.out.print(sumOfSharkSizes);
    }

//    private static void printSharks() {
//        for (Shark shark : sharks) {
//            System.out.println("r : " + shark.r + " c : " + shark.c + " speed : " + shark.speed + " direction : " + shark.direction + " size : " + shark.size);
//        }
//    }

    // 현재 위치와 방향을 알려주면 1칸 이동하기
    private static Info straightMoving(Point current, int direction) {
        int x = current.x;
        int y = current.y;
        Point nextPoint = null;

        if (direction == 1) {
            nextPoint = new Point(x - 1, y);
            if (!checkRange(nextPoint.x, nextPoint.y)) {
                nextPoint.x += 2;
                direction = 2;
            }
        }
        else if (direction == 2) {
            nextPoint = new Point(x + 1, y);
            if (!checkRange(nextPoint.x, nextPoint.y)) {
                nextPoint.x -= 2;
                direction = 1;
            }
        }
        else if (direction == 3) {
            nextPoint = new Point(x, y + 1);
            if (!checkRange(nextPoint.x, nextPoint.y)) {
                nextPoint.y -= 2;
                direction = 4;
            }
        }
        else if (direction == 4) {
            nextPoint = new Point(x, y - 1);
            if (!checkRange(nextPoint.x, nextPoint.y)) {
                nextPoint.y += 2;
                direction = 3;
            }
        }

        return new Info(nextPoint, direction);
    }

    private static void move() {
        for (Shark s : sharks) {
            int count = s.speed;
            int dir = s.direction;
            Point current = new Point(s.r, s.c);

            while (count > 0) {
                Info info = straightMoving(current, dir);
                current = info.point;
                dir = info.dir;

                count--;
            }

            s.r = current.x;
            s.c = current.y;
            s.direction = dir;
        }

        for (int i = 0; i < sharks.size(); i++) {
            Shark s = sharks.get(i);
            int x = s.r, y = s.c;

            if (duplicate[x][y] != -1) { // 기존의 자리에 상어가 있으면 비교
                if (sharks.get(duplicate[x][y]).size < s.size) {
                    sharks.remove(sharks.get(duplicate[x][y]));
                    duplicate[x][y] = i;
                }
            }
            else {
                duplicate[x][y] = i;
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private static int fishing(int idx) {
        Queue<Shark> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Shark o1, Shark o2) {
                return o1.r - o2.r;
            }
        });

        for (Shark shark : sharks) {
            if (shark.c == idx) {
                pq.add(shark);
            }
        }

        if (!pq.isEmpty()) {
            Shark shark = pq.poll();
            sharks.remove(shark);
            return shark.size;
        }
        return 0;
    }

}
