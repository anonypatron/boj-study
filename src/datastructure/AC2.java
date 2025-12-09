package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class AC2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Integer> deque;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int flag = 1;
            boolean isError = false;
            deque = new ArrayDeque<>();

            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String array =  br.readLine();

            parsing(deque, array); // 파싱 후 deque에 삽입

            for (int i = 0; i < command.length(); i++) {
                char c =  command.charAt(i);

                if (c == 'R') {
                    flag *= -1;
                }
                else {
                    if (deque.size() == 0) {
                        isError = true;
                        break;
                    }
                    else {
                        if (flag == 1) {
                            deque.removeFirst();
                        }
                        else {
                            deque.removeLast();
                        }
                    }
                }
            }

//            System.out.println("flag : " + flag);
            if (isError) {
                sb.append("error");
            }
            else {
                sb.append("[");
                if (flag == 1) { // 순방향으로 출력
                    for (Integer integer : deque) {
                        sb.append(integer).append(",");
                    }
                }
                else { // 역방향으로 출력
                    while (deque.size() > 0) {
                        sb.append(deque.removeLast()).append(",");
                    }
                }
                if (sb.charAt(sb.length() - 1) == ',') {
                    sb.deleteCharAt(sb.length() - 1);
                }

                sb.append("]");
            }
            sb.append("\n");
        }

        System.out.print(sb.deleteCharAt(sb.length() - 1));
    }

    public static void parsing(ArrayDeque<Integer> deque, String array) {
        StringBuilder sb = new StringBuilder(array);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(0);

        if (sb.length() == 0) {
            return;
        }

        String []chars = sb.toString().split(",");

        for (String c : chars) {
            deque.addLast(Integer.parseInt(c));
        }
    }

}
