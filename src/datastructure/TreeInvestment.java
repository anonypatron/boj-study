package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeInvestment {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class TreeInfo {
        Point p;
        int age;
        public TreeInfo(Point p, int age) {
            this.p = p;
            this.age = age;
        }
    }
    static List<List<Queue<Integer>>> trees;
    static List<TreeInfo> deadTrees;
    static int [][]nutrients, s2d2;
    static int []dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int []dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // NxN 배열 초기화
        trees = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            trees.add(new ArrayList<>());
        }
        for (List<Queue<Integer>> list : trees) {
            for (int i = 0; i < N; i++) {
                list.add(new PriorityQueue<>());
            }
        }

        nutrients = new int[N][N];
        s2d2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrients[i][j] = 5;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                s2d2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            trees.get(x).get(y).add(Integer.parseInt(st.nextToken()));
        }

        while (K-- > 0) {
            deadTrees = new ArrayList<>();
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.print(sumNumberOfTrees());
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrients[i][j] += s2d2[i][j];
            }
        }
    }

    private static void autumn() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Queue<Integer> nextQ = new PriorityQueue<>();
                Queue<Integer> currentQ = trees.get(i).get(j);

                while (!currentQ.isEmpty()) {
                    int age = currentQ.poll();
                    nextQ.add(age);

                    if (age % 5 == 0) {
                        for (int x = 0; x < dx.length; x++) {
                            int newX = i + dx[x];
                            int newY = j + dy[x];

                            if (checkRange(newX, newY)) {
                                trees.get(newX).get(newY).add(1);
                            }
                        }
                    }
                }

                trees.get(i).set(j, nextQ);
            }
        }
    }

    private static void summer() {
        for (TreeInfo tree : deadTrees) {
            nutrients[tree.p.x][tree.p.y] += tree.age / 2;
        }
    }

    private static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                List<Integer> list = new ArrayList<>();
                Queue<Integer> treeQ = trees.get(i).get(j);

                while (!treeQ.isEmpty()) {
                    int age = treeQ.poll();

                    if (nutrients[i][j] >= age) { // 양분을 먹을 수 있으면
                        list.add(age + 1);
                        nutrients[i][j] -= age;
                    }
                    else { // 못먹으면 죽은 나무 리스트에 올리기
                        deadTrees.add(new TreeInfo(new Point(i, j), age));
                    }
                }

                for (Integer value : list) { // 양분을 먹은 나무들을 다시 pq에 넣어줌
                    treeQ.add(value);
                }
            }
        }
    }

    private static int sumNumberOfTrees() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += trees.get(i).get(j).size();
            }
        }
        return sum;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
