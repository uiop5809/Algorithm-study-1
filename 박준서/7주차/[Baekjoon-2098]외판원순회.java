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

    // INF 값: 경로가 존재하지 않을 때 사용하는 매우 큰 값 (문제에서 비용의 최대범위보다 크게 설정)
    private static int INF = (int) 1e9;

    // N: 도시(노드)의 개수, ans: 최종 최소 비용, all: 모든 도시를 방문했을 때의 bitmask 값
    private static int N, ans, all;

    // cost[i][j]: 도시 i에서 도시 j로 이동하는 비용을 저장하는 2차원 배열
    private static int[][] cost;

    // DP[i][mask]: 현재 도시 i에 있으며, mask에 해당하는(비트마스크로 표현된) 방문 상태에서
    // 남은 경로들의 최소 비용을 저장하는 메모이제이션 배열.
    // mask의 각 비트는 각 도시의 방문 여부를 나타낸다.
    private static int[][] DP;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();

        cost = new int[N][N];
        DP = new int[N][(1 << N)];// DP 배열의 두번째 차원 크기는 2^N 만큼의 방문 상태를 모두 표현할 수 있도록 함.
        all = (1 << N) - 1;

        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < N; j++) {
                cost[i][j] = nextInt();
            }
        }

        // TSP 재귀 함수를 호출하여, 시작 도시 0에서 출발하며, 도시 0이 방문된 상태 (비트마스크 1)
        // 반환된 결과가 최소 비용이 된다.
        bw.write(TSP(0, 1) + "\n");

        printAnswer();
    }

    // TSP 재귀 함수: 현재 도시 'now'와 방문 상태가 담긴 bitmask 'cnt'를 인자로 받아,
    // 남은 경로를 포함한 최소 이동 비용을 반환한다.
    public static int TSP(int now, int cnt) {
        // 기저 사례: 모든 도시를 방문한 경우
        if (cnt == all) {
            // 시작 도시(0)로 돌아갈 수 있는 경로가 있다면 그 비용을 반환,
            // 만약 없으면 INF(매우 큰 값) 반환으로 경로 불가능 처리.
            if (cost[now][cnt] > 0)
                return cost[now][cnt];
            return INF;
        }

        // 메모이제이션: 이미 계산한 상태라면 저장된 값을 바로 반환
        if (DP[now][cnt] != 0)
            return DP[now][cnt];

        // 아직 계산되지 않은 상태라면 INF로 초기화
        DP[now][cnt] = INF;

        // 다음 방문할 도시 선택: 전체 도시 i에 대해
        for (int i = 0; i < N; i++) {
            // 현재 도시에서 i로 이동할 수 있는 경로가 없으면 continue
            if (cost[now][i] == 0)
                continue;
            // 만약 도시 i가 이미 방문된 상태면 건너뛰기 (비트마스크에서 해당 비트 확인)
            if ((cnt & (1 << i)) != 0)
                continue;

            // 도시 i를 방문하는 경우
            // 재귀 호출로 도시 i를 현재 도시로 삼고, 방문 상태 갱신 (cnt에 i번 도시 추가)
            int tmp = TSP(i, cnt | (1 << i));
            // 현재 상태에서 최소 비용 갱신: 현재 이동비용(cost[now][i])과 재귀 호출 결과(tmp)를 더한 값
            DP[now][cnt] = Math.min(DP[now][cnt], cost[now][i] + tmp);
        }
        return DP[now][cnt];
    }
}