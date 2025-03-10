import java.io.*;
import java.util.*;

public class Main {
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

    private static final int[] dx = { 1, 0, -1, 0 };
    private static final int[] dy = { 0, 1, 0, -1 };

    private static int N, Q;
    private static int[][] matrix;
    private static int[][] retMatrix;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        readLine();

        N = (1 << nextInt()); // N은 2의 제곱수
        Q = nextInt();

        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = nextInt();
            }
        }

        readLine();
        for (int i = 0; i < Q; i++) {
            int spinSize = (1 << nextInt()); // 회전 크기 (2의 제곱수)

            // 1일때 회전 X
            if (spinSize != 1) {
                retMatrix = new int[N][N];
                fireStorm(spinSize, 0, 0, N);
                matrix = retMatrix;
            }
            melt();
        }

        bw.write(calc() + "\n" + findIsland() + "\n");

        printAnswer();
    }

    public static void fireStorm(int L, int y, int x, int size) {
        // 현재 크기가 회전 크기보다 큰 경우, 4등분하여 재귀적으로 처리
        if (size > L) {
            fireStorm(L, y, x, size / 2);
            fireStorm(L, y + size / 2, x, size / 2);
            fireStorm(L, y, x + size / 2, size / 2);
            fireStorm(L, y + size / 2, x + size / 2, size / 2);
            return;
        }

        for (int i = 0; i < size / 2; i++) {
            spin(y + i, x + i, size - i * 2);
        }
    }

    public static void spin(int y, int x, int size) {
        for (int i = 0; i < size; i++) {
            retMatrix[y][x + i] = matrix[y + size - 1 - i][x];
            retMatrix[y + i][x + size - 1] = matrix[y][x + i];
            retMatrix[y + size - 1][x + i] = matrix[y + size - 1 - i][x + size - 1];
            retMatrix[y + i][x] = matrix[y + size - 1][x + i];
        }
    }

    public static void melt() {
        retMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int nearIce = 0; // 주변 얼음 수

                // 주변 얼음 수 계산
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (!OOB(ny, nx) && matrix[ny][nx] != 0)
                        nearIce++;
                }

                // 주변 얼음이 3개 미만이면 녹음
                if (nearIce < 3)
                    retMatrix[i][j] = Math.max(0, matrix[i][j] - 1);
                else
                    retMatrix[i][j] = matrix[i][j];
            }
        }
        matrix = retMatrix;
    }

    public static int calc() {
        int ret = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret += matrix[i][j];
            }
        }
        return ret;
    }

    public static int findIsland() {
        visited = new boolean[N][N];
        int ret = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (matrix[i][j] != 0 && !visited[i][j])
                    ret = Math.max(ret, dfs(i, j));

        return ret;
    }

    public static int dfs(int y, int x) {
        int ret = 1;

        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (OOB(ny, nx) || matrix[ny][nx] == 0 || visited[ny][nx])
                continue;

            ret += dfs(ny, nx);
        }

        return ret;
    }

    public static boolean OOB(int y, int x) {
        return (x < 0 || y < 0 || x >= N || y >= N);
    }
}
