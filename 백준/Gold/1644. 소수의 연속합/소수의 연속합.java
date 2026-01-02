import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main { // 1644
    // 1. n까지의 소수를 에라토스테네스의 체로 구한다.
    // 2. 가장 앞에서부터 left와 right사이의 누적합을 구한다. 합이 일치하면 count++;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.print(sumOfPrimeNumbers(n, getPrimeNumber(n)));
    }

    private static int sumOfPrimeNumbers(int target, List<Integer> primeNumbers) {
        if (primeNumbers.isEmpty()) {
            return 0;
        }

        int left = 0, right = 0;
        int sum = 0, count = 0;

        while (true) {
            if (sum >= target) {
                if (sum == target) {
                    count++;
                }
                sum -= primeNumbers.get(left++);
            }
            else {
                if (right == primeNumbers.size()) {
                    break;
                }
                sum += primeNumbers.get(right++);
            }
        }

        return count;
    }

    private static List<Integer> getPrimeNumber(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

}
