package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lie {
    static List<List<Integer>> graph;
    static int []parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        graph = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            parents[Integer.parseInt(st.nextToken())] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int numberOfPeoples = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            graph.get(i).add(x);

            if (numberOfPeoples > 1) {
                for (int j = 0; j < numberOfPeoples - 1; j++) {
                    int y = Integer.parseInt(st.nextToken());
                    graph.get(i).add(y);
                    union(x, y);
                }
            }
        }

        int count = 0;
        for (List<Integer> list : graph) {
            boolean flag = false;
            for (Integer value : list) {
                if (find(value) == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                count++;
            }
        }

        System.out.print(count);
    }

    private static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a > b) {
            parents[a] = b;
        }
        else {
            parents[b] = a;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

}
