package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CycleGame { // 20040
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int result = 0;
        parents = new int[n];

        for (int i = 0 ; i < n; i++) {
            parents[i] = i;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            result++;
            if (find(x) == find(y)) {
                break;
            }
            union(x, y);
        }

        System.out.print(m == -1 ? "0" : result);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        parents[y] = x;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

}
