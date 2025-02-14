package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LectureTour {
    static class Data {
        int value, deadLine;

        public Data(int value, int deadLine) {
            this.value = value;
            this.deadLine = deadLine;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Data> list = new ArrayList<>();

        int maxDeadLine = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            int deadLine = Integer.parseInt(st.nextToken());
            list.add(new Data(value, deadLine));
            maxDeadLine = Math.max(maxDeadLine, deadLine);
        }

        list.sort((o1, o2) -> {
            if (o2.deadLine == o1.deadLine) {
                return o2.value - o1.value;
            }
            return o2.deadLine - o1.deadLine;
        });
        PriorityQueue<Data> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);

        int result = 0, index = 0;
        for (int i = maxDeadLine; i > 0; i--) {
            while (index < n && list.get(index).deadLine >= i) { // 현재 마감일과 같으면 pq에 넣어두기
                pq.add(list.get(index));
                index++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll().value;
            }
        }

        System.out.print(result);
    }

}
