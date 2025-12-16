package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindProductOfInterval {
    static Long[] arr, tree;
    static final Long MODULAR = 1000000007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new Long[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        initTree(n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int index = Integer.parseInt(st.nextToken());
                Long value = Long.parseLong(st.nextToken());
                change(n, index, value);
            }
            else {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                sb.append(query(n, start, end) % MODULAR).append('\n');
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void initTree(int n) {
        tree = new Long[(n + 1) * 4];
        build(1, 1, n);
    }

    private static Long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = start + (end - start) / 2;
        return tree[node] = build(node * 2, start, mid) *
                build(node * 2 + 1, mid + 1, end) % MODULAR;
    }

    private static void change(int n, int index, Long value) {
        change(1, 1, n, index, value);
    }

    private static Long change(int node, int start, int end, int index, Long value) {
        if (index < start || index > end) {
            return tree[node];
        }
        if (start == end) {
            arr[index] = value;
            return tree[node] = value;
        }
        int mid = start + (end - start) / 2;
        return tree[node] = change(node * 2, start, mid, index, value) *
                change(node * 2 + 1, mid + 1, end, index, value) % MODULAR;
    }

    private static Long query(int n, int start, int end) {
        return query(1, 1, n, start, end);
    }

    private static Long query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return 1L;
        }
        if (queryStart <= start && queryEnd >= end) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;

        return query(node * 2, start, mid, queryStart, queryEnd) *
                query(node * 2 + 1, mid + 1, end, queryStart, queryEnd) % MODULAR;
    }

}
