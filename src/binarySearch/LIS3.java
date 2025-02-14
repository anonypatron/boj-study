package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []lis = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        lis[0] = Integer.parseInt(st.nextToken());

        int lisIndex = 1; // 넣을 위치

        for (int i = 1; i < n; i++) {
            int current = Integer.parseInt(st.nextToken());

            if (lis[lisIndex - 1] < current) { // 가장 크면 그냥 넣기
                lis[lisIndex++] = current;
            }
            else {
                int left = 0, right = lisIndex - 1;

                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if (lis[mid] < current) {
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }

                lis[left] = current;
            }

        }

        System.out.print(lisIndex);
    }

}
