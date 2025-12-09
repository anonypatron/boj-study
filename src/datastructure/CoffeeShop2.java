package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoffeeShop2 {
    static long[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        initTree(n);

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (x > y) {
                sb.append(query(n, y, x));
            }
            else {
                sb.append(query(n, x, y));
            }
            sb.append('\n');
            update(n, a, b);
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void initTree(int n) {
        tree = new long[(n + 1) * 4];
        build(1, 1, n);
    }

    private static long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = start + (end - start) / 2;
        return tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    private static void update(int n, int a, long b) {
        update(1, 1, n, a, b);
    }

    private static long update(int node, int start, int end, int index, long value) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            arr[index] = value;
            return tree[node] = value;
        }

        int mid = start + (end - start) / 2;
        return tree[node] = update(node * 2, start, mid, index, value) + update(node * 2 + 1, mid + 1, end, index, value);
    }

    private static long query(int n, int queryStart, int queryEnd) {
        return query(1, 1, n, queryStart, queryEnd);
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
