import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 2887
    static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static class Planet implements Comparable<Planet>{
        int number, value;
        public Planet(int number, int value) {
            this.number = number;
            this.value = value;
        }
        @Override
        public int compareTo(Planet o) {
            return this.value - o.value;
        }
    }
    static List<Edge> edges;
    static int[] parents, rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Planet[] arrX = new Planet[n];
        Planet[] arrY = new Planet[n];
        Planet[] arrZ = new Planet[n];
        parents = new int[n];
        rank = new int[n];
        edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            arrX[i] = new Planet(i, x);
            arrY[i] = new Planet(i, y);
            arrZ[i] = new Planet(i, z);
        }

        Arrays.sort(arrX);
        Arrays.sort(arrY);
        Arrays.sort(arrZ);

        addEdges(arrX);
        addEdges(arrY);
        addEdges(arrZ);
        Collections.sort(edges);

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            rank[i] = 1;
        }

        int result = 0, cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.weight;
                if (++cnt == n - 1) {
                    break;
                }
            }
        }

        System.out.print(result);
    }

    private static boolean union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a == b) {
            return false;
        }

        if (rank[a] < rank[b]) {
            parents[a] = b;
            rank[b] += rank[a];
        }
        else {
            parents[b] = a;
            rank[a] += rank[b];
        }
        return true;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void addEdges(Planet[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            Planet cur = arr[i];
            Planet next = arr[i + 1];
            int weight = Math.abs(cur.value - next.value);
            edges.add(new Edge(cur.number, next.number, weight));
        }
    }

}
