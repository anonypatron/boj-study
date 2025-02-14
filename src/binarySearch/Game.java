package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    완성 못함
 */
public class Game {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int winRate = y * 100 / x;
//        if (winRate >= 99) { // 승률이 이미 99% 이상이라면 더 이상 변화 불가능
//            System.out.println(-1);
//            return;
//        }

        long left = 0, right = 1000000000, answer = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long newWinRate = (y + mid) * 100 / (x + mid);

            if (newWinRate > winRate) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }

}
