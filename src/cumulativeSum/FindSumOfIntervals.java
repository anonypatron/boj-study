package cumulativeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindSumOfIntervals { // 11659번
    static int[] arr, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        initTree(n);

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            sb.append(query(n, p, q)).append('\n');
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
        return tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    private static int query(int n , int p, int q) {
        return query(1, 1, n, p, q);
    }

    private static int query(int node, int start, int end, int p, int q) {
        if (p > end || q < start) {
            return 0;
        }
        if (p <= start && q >= end) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        return query(node * 2, start, mid, p, q) + query(node * 2 + 1, mid + 1, end, p, q);
    }

}
