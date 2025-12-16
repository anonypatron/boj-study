package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfIntervals {
    static Long []tree, arr;
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new Long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        initTree();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int index = Integer.parseInt(st.nextToken());
                Long value = Long.parseLong(st.nextToken());
                update(index, value);
            }
            else {
                int startIndex = Integer.parseInt(st.nextToken());
                int endIndex = Integer.parseInt(st.nextToken());
                sb.append(query(startIndex, endIndex)).append('\n');
            }
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void initTree() {
        tree = new Long[(arr.length + 1) * 4];
        build(1, 1, N);
    }

    private static Long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (end - start) / 2 + start;
        return tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    private static void update(int index, Long value) {
        update(1, 1, N, index, value);
    }

    private static Long update(int node, int start, int end, int index, Long value) {
        if (index < start || index > end) { // 범위를 벗어난 경우
            return tree[node];
        }

        if (start == end) { // 리프노드인 경우
            arr[index] = value;
            return tree[node] = value;
        }

        int mid = (end - start) / 2 + start;
        return tree[node] = update(node * 2, start, mid, index, value) + update(node * 2 + 1, mid + 1, end, index, value);
    }

    private static Long query(int start, int end) {
        return query(1, 1, N, start, end);
    }

    private static Long query(int node, int start, int end, int queryStart, int queryEnd) {
        if  (queryEnd < start || queryStart > end) {
            return 0L;
        }

        if (queryStart <= start && queryEnd >= end) {
            return tree[node];
        }

        int mid = (end - start) / 2 + start;
        return query(node * 2, start, mid, queryStart, queryEnd) +  query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);
    }

}
