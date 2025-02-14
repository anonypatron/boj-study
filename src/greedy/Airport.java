package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport {
    static int []parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int result = 0;
        parent = new int[G + 1];

        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < P; i++) {
            int index = Integer.parseInt(br.readLine());

            if (find(index) == 0) {
                break;
            }

            result++;
            union(find(index), find(index) - 1);
        }

        System.out.print(result);
    }

    public static void union(int a, int b) {
        parent[a] = b;
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

}
