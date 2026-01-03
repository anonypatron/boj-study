import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 12015
    // 숫자를 하나씩 받으면서 현재 가장 마지막보다 크면 뒤에 넣기
    // 현재 마지막 숫자보다 낮으면 앞에서 이분 탐색을 통해 위치를 찾고 교체하기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] lis = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        lis[0] = Integer.parseInt(st.nextToken());

        int idx = 0; // 현재 마지막 요소를 가리키는 인덱스
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());

            if (lis[idx] < x) {
                lis[++idx] = x;
            }
            else { // 이분탐색 시작
                int left = 0, right = idx;

                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (lis[mid] < x) {
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                lis[left] = x;
            }
        }

        System.out.print(idx + 1);
    }

}
