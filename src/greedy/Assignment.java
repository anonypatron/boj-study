package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment {
    static class Data{
        int deadLine, value;

        public Data(int deadLine, int value) {
            this.deadLine = deadLine;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        List<Data> list = new ArrayList<>();

        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.add(new Data(d, v));
            maxDeadline = Math.max(maxDeadline, d);
        }

        list.sort((o1, o2) -> {
            if (o2.deadLine == o1.deadLine) {
                return o2.value - o1.value;
            }
            return o2.deadLine - o1.deadLine;
        });
        PriorityQueue<Data> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);

        int index = 0, result = 0;
        for (int i = maxDeadline; i > 0; i--) {
            while (index < n && list.get(index).deadLine >= i) {
                pq.add(list.get(index)); // 점수만 추가
                index++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll().value;
            }
        }

        System.out.print(result);
    }

}
