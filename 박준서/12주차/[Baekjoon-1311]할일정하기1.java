import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.close();
        bw.close();
    }

    private static final int INF = Integer.MAX_VALUE / 2; // 무한대 값 (오버플로우 방지)

    private static int N, ans;
    private static int[][] matrix; // 비용 행렬
    private static int[][] DP; // 동적 프로그래밍 배열

    public static void main(String args[]) throws Exception {
        N = nextInt();

        matrix = new int[N][N];
        DP = new int[N][(1 << N)]; // 상태를 비트마스크로 표현

        // 행렬 입력 및 DP 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                matrix[i][j] = nextInt();
            Arrays.fill(DP[i], INF); // DP 배열을 무한대로 초기화
        }

        ans = BDP(0, 0);

        bw.write(ans + "\n");
        bwEnd();
    }

    // 비트마스크를 이용한 동적 프로그래밍 함수
    public static int BDP(int now, int path) {
        // 기저 조건: 모든 행을 처리했을 때
        if (now == N)
            return 0;

        // 이미 계산된 상태라면 저장된 값 반환
        if (DP[now][path] != INF)
            return DP[now][path];

        int ans = INF;
        for (int i = 0; i < N; i++) {
            if ((path & (1 << i)) != 0)
                continue;
            // 현재 행에서 i열을 선택하고 다음 행으로 이동
            ans = Math.min(ans, BDP(now + 1, path | (1 << i)) + matrix[now][i]);
        }

        DP[now][path] = ans;
        return ans;
    }
}
