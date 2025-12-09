package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SumOfPrime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []primes = findPrime();

        int left = 0, right = 0;

        while (right < n) {

            right++;
        }
    }

    private static int[] findPrime() {
        int limit = 4000000;
        boolean[] isPrime = new boolean[limit + 1];

        ArrayList<Integer> primes = new ArrayList<>();

        // 초기화: 모든 숫자를 소수로 가정
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // 에라토스테네스의 체 실행
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 소수만 리스트에 추가
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // 리스트를 배열로 변환
        return primes.stream().mapToInt(i -> i).toArray();
    }

}
