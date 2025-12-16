package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AccountBook {
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        arr = new long[n + 1];
        tree = new long[(n + 1) * 4];

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int p, q, x;

            switch (command) {
                case 1:
                    p = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    update(n, p, x);
                    break;
                case 2:
                    p = Integer.parseInt(st.nextToken());
                    q = Integer.parseInt(st.nextToken());
                    sb.append(query(n, p, q)).append('\n');
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void update(int n, int p, int x) {
        update(1, 1, n, p, x);
    }

    private static long update(int node, int start, int end, int index, int value) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            arr[index] += value;
            return tree[node] += value;
        }
        int mid = start + (end - start) / 2;
        return tree[node] = update(node * 2, start, mid, index, value) + update(node * 2 + 1, mid + 1, end, index, value);
    }

    private static long query(int n, int start, int end) {
        return query(1, 1, n, start, end);
    }

    private static long query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return 0L;
        }
        if (queryStart <= start && queryEnd >= end) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        return query(node * 2, start, mid, queryStart, queryEnd) + query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);
    }

}
