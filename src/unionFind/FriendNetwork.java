package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FriendNetwork {
    static Map<String, Integer> network;
    static int []parents, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int f = Integer.parseInt(br.readLine());
            int idx = 0, maxIndex = f * 2;

            network = new HashMap<>();
            parents = new int[maxIndex];
            count = new int[maxIndex];

            for (int i = 0; i < maxIndex; i++) {
                parents[i] = i;
                count[i] = 1;
            }

            for (int i = 0; i < f; i++) {
                String []str = br.readLine().split(" ");

                if (!network.containsKey(str[0])) {
                    network.put(str[0], idx++);
                }
                if (!network.containsKey(str[1])) {
                    network.put(str[1], idx++);
                }

                int x = findIndexByName(str[0]);
                int y = findIndexByName(str[1]);

                sb.append(union(x, y)).append('\n');
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static int union(int x, int y) {
        int a = find(x); // 1
        int b = find(y); // 3

        if (a != b) {
            parents[b] = a;
            count[a] += count[b];
        }

        return count[a];
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static int findIndexByName(String x) {
        return network.get(x);
    }

}
