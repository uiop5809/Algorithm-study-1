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

    private static int N, M, ans = 0; // N: 일 수, M: 목표 점수, ans: 가능한 경우의 수
    private static int[][] works; // 두 명의 사람의 일 점수 (2x3 배열)

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();

        works = new int[2][3];

        for (int i = 0; i < 2; i++) {
            readLine();
            for (int j = 0; j < 3; j++) {
                works[i][j] = nextInt();
            }
        }

        dfs(0, -1, 0);

        bw.write(ans + "\n");

        printAnswer();
    }

    public static void dfs(int day, int post, int cnt) {
        if (day == N) {
            if (cnt >= M)
                ans++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                // 이전 포지션과 같은 경우 점수를 절반으로 계산
                if (post == j)
                    dfs(day + 1, j, cnt + works[i][j] / 2);
                // 이전 포지션과 다른 경우 점수를 전부 계산
                else
                    dfs(day + 1, j, cnt + works[i][j]);
            }
        }
    }
}
