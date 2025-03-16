import java.util.*;
import java.io.*;

public class 외판원  {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static long[][] arr, dp;
    static long INF = Long.MAX_VALUE / 2;
    static long answer = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new long[n][n];
        dp = new long[1 << n][n];

        // DP 배열 초기화
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][0] = 0; // 시작점 설정

        // 거리 행렬 입력 받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
                if (i != j && arr[i][j] == 0) {
                    arr[i][j] = INF; // 이동 불가능한 경우 큰 값으로 설정
                }
            }
        }

        tsp();
        System.out.println(answer == INF ? -1 : answer); // 이동 불가능하면 -1 반환
    }

    public static void tsp() {
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) != 0 && dp[mask][u] != INF) { // 유효한 경로라면
                    for (int v = 0; v < n; v++) {
                        if ((mask & (1 << v)) == 0 && arr[u][v] != INF) { // v가 방문되지 않았고, 유효한 거리라면
                            int nextMask = mask | (1 << v);
                            if (dp[mask][u] + arr[u][v] < INF) { // 오버플로우 방지
                                dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][u] + arr[u][v]);
                            }
                        }
                    }
                }
            }
        }

        // 모든 도시를 방문한 상태에서 시작점으로 돌아오는 최소 비용 찾기
        for (int v = 1; v < n; v++) {
            if (arr[v][0] != INF && dp[(1 << n) - 1][v] != INF) { // 유효한 경로인지 확인
                answer = Math.min(answer, dp[(1 << n) - 1][v] + arr[v][0]);
            }
        }
    }
}
