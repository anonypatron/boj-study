package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CupLamen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Queue<Integer> minQ = new PriorityQueue<>();
        Queue<int []> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) { // 데드라인이 같으면
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            pq.add(new int[]{ deadLine, reward });
        }

        int result = 0, deadLine = 1;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] == deadLine) {
                minQ.add(pq.poll()[1]);
            }

            while (minQ.size() > deadLine) {
                minQ.poll();
            }
            deadLine++;
        }

        while (!minQ.isEmpty()) {
            result += minQ.poll();
        }

        System.out.print(result);
    }

}
