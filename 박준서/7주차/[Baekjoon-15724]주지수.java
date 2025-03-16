import java.io.*;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    private static int N, M, K;
    private static int[][] matrix;

    public static void main(String args[]) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = nextInt();
        M = nextInt();

        matrix = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            readLine();
            for (int j = 1; j <= M; j++)
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1] + nextInt()
                        - matrix[i - 1][j - 1];
        }

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            readLine();
            int y1 = nextInt();
            int x1 = nextInt();
            int y2 = nextInt();
            int x2 = nextInt();
            // 2차원 누적 합을 이용한 부분 합 계산
            bw.write(matrix[y2][x2] - matrix[y2][x1 - 1] - matrix[y1 - 1][x2] + matrix[y1 - 1][x1 - 1] + "\n");
        }

        printAnswer();
    }
}
