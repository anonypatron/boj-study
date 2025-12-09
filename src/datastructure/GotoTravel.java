package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GotoTravel {
    static int []parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = parents[Integer.parseInt(st.nextToken())];
        boolean isPossible = true;

        for (int i = 1; i < m; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if (start != parents[tmp]) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            System.out.print("YES");
        }
        else {
            System.out.print("NO");
        }
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x < y) {
            parents[y] = x;
        }
        else {
            parents[x] = y;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

}
