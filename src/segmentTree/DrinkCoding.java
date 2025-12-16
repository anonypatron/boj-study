package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DrinkCoding {
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] sizes = line.split(" ");
            int n = Integer.parseInt(sizes[0]);
            int k = Integer.parseInt(sizes[1]);

            arr = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = reader(Integer.parseInt(st.nextToken()));
            }
            initTree(n);

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                int i, j, v;

                switch (command) {
                    case 'C':
                        i = Integer.parseInt(st.nextToken());
                        v = reader(Integer.parseInt(st.nextToken()));
                        update(n, i, v);
                        break;
                    case 'P':
                        i = Integer.parseInt(st.nextToken());
                        j = Integer.parseInt(st.nextToken());
                        sb.append(query(n, i, j));
                        break;
                }
            }
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void initTree(int n) {
        tree = new int[(n + 1) * 4];
        build(1, 1, n);
    }

    private static int build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = start + (end - start) / 2;
        return tree[node] = build(node * 2, start, mid) * build(node * 2 + 1, mid + 1, end);
    }

    private static void update(int n, int i, int v) {
        update(1, 1, n, i, v);
    }

    private static int update(int node, int start, int end, int index, int value) {
        if (index < start || index > end) {
            return tree[node];
        }

        if (start == end) {
            arr[index] = value;
            return tree[node] = value;
        }
        int mid = start + (end - start) / 2;
        return tree[node] = update(node * 2, start, mid, index, value) * update(node * 2 + 1, mid + 1, end, index, value);
    }

    private static char query(int n, int i, int j) {
        int x = query(1, 1, n, i, j);
        if (x < 0) {
            return '-';
        }
        else if (x == 0) {
            return '0';
        }
        return '+';
    }

    private static int query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return 1;
        }
        if (queryStart <= start && queryEnd >= end) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        return query(node * 2, start, mid, queryStart, queryEnd) * query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);
    }

    private static int reader(int x) {
        if (x < 0) {
            return -1;
        }
        else if (x == 0) {
            return 0;
        }
        return 1;
    }

}
