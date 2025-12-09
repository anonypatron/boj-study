package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sudoku { // 2580
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<Point> points = new ArrayList<>();
    static int[][] result;
    static int K;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] matrix = new int[9][9];
        result = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 0) {
                    points.add(new Point(i, j));
                    K++;
                }
            }
        }

        backTracking(matrix, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void backTracking(int[][] matrix, int pointIndex) {
        if (flag) {
            return;
        }
        if (pointIndex == points.size()) {
            cloneMatrix(matrix);
            flag = true;
            return;
        }

        Point cur = points.get(pointIndex);
        int x = cur.x;
        int y = cur.y;

        for (int num = 1; num <= 9; num++) {
            if (isPossible(matrix, cur, num)) {
                matrix[x][y] = num;
                backTracking(matrix, pointIndex + 1);
                matrix[x][y] = 0;
            }
        }

    }

    private static boolean isPossible(int[][] matrix, Point point, int num) {
        int x = point.x;
        int y = point.y;

        for (int i = 0; i < 9; i++) {
            if (matrix[x][i] == num || matrix[i][y] == num) {
                return false;
            }
        }

        // 정사각형 확인
        int zone = findZone(point);
        switch (zone) {
            case 1:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 5:
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 6:
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 7:
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 8:
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
            case 9:
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (matrix[i][j] == num) {
                            return false;
                        }
                    }
                }
                break;
        }
        return true;
    }

    private static int findZone(Point point) {
        int x = point.x;
        int y = point.y;

        if (x < 3 && y < 3) {
            return 1;
        }
        if (x < 3 && y < 6) {
            return 2;
        }
        if (x < 3 && y < 9) {
            return 3;
        }
        if (x < 6 && y < 3) {
            return 4;
        }
        if (x < 6 && y < 6) {
            return 5;
        }
        if (x < 6 && y < 9) {
            return 6;
        }
        if (x < 9 && y < 3) {
            return 7;
        }
        if (x < 9 && y < 6) {
            return 8;
        }
        return 9;
    }

    private static void cloneMatrix(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = matrix[i][j];
            }
        }
    }

}
