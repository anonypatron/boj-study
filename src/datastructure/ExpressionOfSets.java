package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ExpressionOfSets {
    static int []parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (command) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if (checkSameSet(a, b)) {
                        sb.append("YES").append("\n");
                    }
                    else {
                        sb.append("NO").append("\n");
                    }
                    break;
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static boolean checkSameSet(int a, int b) {
        return find(a) == find(b);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x > y) {
            parents[y] = x;
        } else {
            parents[x] = y;
        }
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

}
