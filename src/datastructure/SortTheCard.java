package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SortTheCard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int result = 0;

        while (n-- > 0) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();

            pq.add(a + b);
            result += a + b;
        }

        System.out.print(result);
    }
}
