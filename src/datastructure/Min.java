package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Min {
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(n);
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(query(n, start, end)).append('\n');
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
        return tree[node] = Math.min(build(node * 2, start, mid),
                build(node * 2 + 1, mid + 1, end));
    }

    private static int query(int n, int start, int end) {
        return query(1, 1, n, start, end);
    }

    private static int query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return Integer.MAX_VALUE;
        }
        if (queryStart <= start && queryEnd >= end) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        return Math.min(query(node * 2, start, mid, queryStart, queryEnd),
                query(node * 2 + 1, mid + 1, end, queryStart, queryEnd));
    }

}
