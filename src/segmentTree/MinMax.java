package segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinMax {
    static int[] arr, minTree, maxTree;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(minQuery(start, end)).append(' ').append(maxQuery(start, end)).append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    private static void init() {
        minTree = new int[(N + 1) * 4];
        maxTree = new int[(N + 1) * 4];
        minBuild(1, 1, N);
        maxBuild(1, 1, N);
    }

    private static int minBuild(int node, int start, int end) {
        if (start == end) {
            return minTree[node] = arr[start];
        }
        int mid = start + (end - start) / 2;

        return minTree[node] = Math.min(minBuild(node * 2, start, mid), minBuild(node * 2 + 1, mid + 1, end));
    }

    private static int maxBuild(int node, int start, int end) {
        if (start == end) {
            return maxTree[node] = arr[start];
        }
        int mid = start + (end - start) / 2;

        return maxTree[node] = Math.max(maxBuild(node * 2, start, mid), maxBuild(node * 2 + 1, mid + 1, end));
    }

    private static int minQuery(int start, int end) {
        return minquery(1, 1, N, start, end);
    }

    private static int minquery(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return Integer.MAX_VALUE;
        }
        if (queryStart <= start && queryEnd >= end) {
            return minTree[node];
        }

        int mid = start + (end - start) / 2;
        return Math.min(minquery(node * 2, start, mid, queryStart, queryEnd), minquery(node * 2 + 1, mid + 1, end, queryStart, queryEnd));
    }

    private static int maxQuery(int start, int end) {
        return maxQuery(1, 1, N, start, end);
    }

    private static int maxQuery(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return 0;
        }
        if (queryStart <= start && queryEnd >= end) {
            return maxTree[node];
        }

        int mid = start + (end - start) / 2;
        return Math.max(maxQuery(node * 2, start, mid, queryStart, queryEnd), maxQuery(node * 2 + 1, mid + 1, end, queryStart, queryEnd));
    }

}
