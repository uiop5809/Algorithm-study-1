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

    // N: 입력되는 숫자의 개수, ans: 최종 결과 (최대 합)
    private static int N, ans;
    // cost 배열: 각 숫자(또는 비용) 값들을 저장
    private static int[] cost;
    // dp 배열: dp[i][j]는 오른쪽(끝 부분)에서 i개, 왼쪽(처음 부분)에서 j개 선택하여 지금까지 계산된 최대 합을 저장
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();

        cost = new int[N];
        dp = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            readLine();
            cost[i] = nextInt();
        }

        // dp 배열의 테두리 초기화
        for (int i = 1; i <= N; i++) {
            // 오른쪽에서 숫자를 하나도 선택하지 않고 왼쪽(처음)에서 i개 선택했을 때의 경우
            dp[0][i] = Math.max(dp[0][i], dp[0][i - 1] + cost[i - 1] * i);
            // 왼쪽에서 숫자를 하나도 선택하지 않고 오른쪽(끝)에서 i개 선택했을 때의 경우
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][0] + cost[N - i] * i);
        }

        // dp 배열 채우기: i는 오른쪽(끝)에서 선택한 개수, j는 왼쪽(처음)에서 선택한 개수
        // 총 선택 횟수는 i+j이며, 이 값이 현재 선택 순서(가중치)로 사용된다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int manhDist = i + j; // 현재까지 선택한 총 개수, 순서에 따른 가중치 역할
                // 총 선택 개수가 N을 초과하면 더 이상 선택할 수 없으므로 내부 반복을 종료
                if (manhDist > N)
                    break;

                // 두 가지 선택 중 최대값을 구함
                // 1. 오른쪽(끝)에서 숫자를 추가 선택하는 경우:
                // dp[i-1][j] 값에 cost[N-i] (오른쪽 끝에서 i번째 숫자)의 가중치(manhtDist)를 더한다.
                // 2. 왼쪽(처음)에서 숫자를 추가 선택하는 경우:
                // dp[i][j-1] 값에 cost[j-1] (왼쪽에서 j번째 숫자)의 가중치(manhtDist)를 더한다.
                dp[i][j] = Math.max(
                        dp[i - 1][j] + cost[N - i] * manhDist,
                        dp[i][j - 1] + cost[j - 1] * manhDist);
            }
        }

        // 가능한 모든 경우 중 전체 숫자 선택 시 (i + j == N) 최대 합 찾기
        // i는 오른쪽에서 선택한 개수, 따라서 왼쪽에서는 N-i개 선택한 경우를 확인
        for (int i = 0; i <= N; i++) {
            ans = Math.max(ans, dp[i][N - i]);
        }

        bw.write(ans + "\n");
        printAnswer();
    }
}