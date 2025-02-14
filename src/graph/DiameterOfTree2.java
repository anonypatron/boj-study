package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DiameterOfTree2 {
    static class Node {
        int next, weight;
        Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
    static List<List<Node>> graph;
    static boolean []visited;
    static int maxDiameter, furthest;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            String []chars = br.readLine().split(" ");
            for (int j = 1; j < chars.length - 2; j += 2) {
                int next = Integer.parseInt(chars[j]);
                int weight = Integer.parseInt(chars[j + 1]);
                graph.get(i).add(new Node(next, weight));
            }
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        dfs(furthest, 0);

        System.out.print(maxDiameter);
    }

    private static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDiameter) {
            maxDiameter = distance;
            furthest = node;
        }

        for (Node child : graph.get(node)) {
            if (!visited[child.next]) {
                dfs(child.next, distance + child.weight);
            }
        }
    }

}
