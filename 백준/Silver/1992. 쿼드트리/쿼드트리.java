import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // 1992
    static StringBuilder sb = new StringBuilder();
    static int[][] video;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        video = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                video[i][j] = str.charAt(j) - '0';
            }
        }

        divide(0, 0, n);
        System.out.print(sb);
    }

    private static void divide(int x, int y, int size) {
        int color = video[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (video[i][j] != color) {
                    int half = size / 2;
                    
                    sb.append('(');
                    for (int dx = 0; dx < 2; dx++) {
                        for (int dy = 0; dy < 2; dy++) {
                            divide(x + dx * half, y + dy * half, half);
                        }
                    }
                    sb.append(')');
                    return;
                }
            }
        }

        if (color == 0) {
            sb.append(0);
        }
        else {
            sb.append(1);
        }
    }

}
