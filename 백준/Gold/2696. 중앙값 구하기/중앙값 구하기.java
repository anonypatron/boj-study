import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 2696
    // 예시 1 2 3 4 5
    // 작은 값들은 maxQ에넣고 가장 큰 값만 확인하기
    // 큰 값들은 minQ에 넣고 가장 작은 값 확인하기
    // 두 힙의 peek를 확인하고 만약 minQ에 있는값이 maxQ에 있는 값보다 작으면 둘을 스위치
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            Queue<Integer> minQ = new PriorityQueue<>();
            Queue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());

            int n = Integer.parseInt(br.readLine());
            int count = 0, printCounter = 0;

            sb.append((n + 1) / 2).append('\n');
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (count < n) {
                if (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                    continue;
                }

                maxQ.add(Integer.parseInt(st.nextToken()));
                count++;

                while (!maxQ.isEmpty() && !minQ.isEmpty() && maxQ.peek() > minQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(minQ.poll());
                }

                while (maxQ.size() - 2 >= minQ.size()) {
                    minQ.add(maxQ.poll());
                }

                if (count % 2 == 1) {
                    printCounter++;
                    sb.append(maxQ.peek()).append(' ');

                    if (printCounter % 10 == 0) {
                        sb.append('\n');
                    }
                }
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

}
