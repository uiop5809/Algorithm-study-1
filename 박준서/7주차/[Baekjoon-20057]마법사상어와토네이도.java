import java.io.*;
import java.util.StringTokenizer;

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

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };

    private static int[][] sandx = {
            { 0, 0, -1, -1, -3, -1, -1, -2, -2, -2 },
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
            { 0, 0, 1, 1, 3, 1, 1, 2, 2, 2 },
            { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }
    };
    private static int[][] sandy = {
            { 1, -1, 2, -2, 0, 1, -1, 1, -1, 0 },
            { 0, 0, 1, 1, 3, 1, 1, 2, 2, 2 },
            { 1, -1, 2, -2, 0, 1, -1, 1, -1, 0 },
            { 0, 0, -1, -1, -3, -1, -1, -2, -2, -2 }
    };

    // 각 위치에 퍼지는 모래의 퍼센트 (단위: %)
    // 인덱스 0~8까지 사용하며, 마지막 남은 모래(알파)는 별도로 처리함
    private static int[] sandval = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

    private static int N, ans; // N: 격자 크기, ans: 격자 밖으로 나간 모래의 총량
    private static int[][] matrix; // 격자 상태 저장

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = nextInt();
            }
        }

        tornado();

        printAnswer();
    }

    // 토네이도의 이동 경로 시뮬레이션
    public static void tornado() throws Exception {
        // 시작 위치는 격자 중앙
        int x = N / 2, y = N / 2;
        // 초기 방향 (0: 왼쪽)
        int d = 0;
        // 현재 이동 횟수 (좌우 또는 상하로 몇 칸 이동할지)
        int moveCnt = 1;

        while (true) {
            // 동일 이동 횟수로 2번 진행 (토네이도가 왼쪽, 아래, 오른쪽, 위 순으로 이동)
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < moveCnt; j++) {
                    // 현재 위치에서 d 방향으로 한 칸 이동하며 모래 분산
                    spread(x, y, d);
                    // 필요 시 디버깅용: printMatrix();
                    // 토네이도 이동 (x,y 업데이트)
                    x += dx[d];
                    y += dy[d];
                }
                // d 방향 변경 (시계방향으로 회전)
                d = (d + 1) % 4;
            }

            // 이동 횟수 1 증가. 만약 증가한 moveCnt가 N과 같으면 마지막 행 이동 처리
            if (++moveCnt == N) {
                for (int j = 0; j < moveCnt; j++) {
                    spread(x, y, d);
                    x += dx[d];
                    y += dy[d];
                }
                break; // 마지막 이동 후 시뮬레이션 종료
            }
        }
        // 격자 밖으로 나간 모래의 총합(ans)를 출력
        bw.write(ans + "\n");
    }

    // 모래를 확산시키는 메서드
    // 매개변수: 현재 위치 (x, y)와 토네이도의 이동 방향 d
    public static void spread(int x, int y, int d) {
        // d 방향으로 한 칸 이동한 위치 (모래를 제거하고 분배할 위치)
        int nowx = x + dx[d];
        int nowy = y + dy[d];
        // 만약 해당 위치가 격자 범위를 벗어나거나 모래가 없다면 종료
        if (OOB(nowx, nowy) || matrix[nowy][nowx] == 0)
            return;

        // 이동한 위치의 모래 양
        int Sand = matrix[nowy][nowx];
        // 모래를 모두 제거
        matrix[nowy][nowx] = 0;

        int Temp = Sand; // 분배 전 원래 모래 총량을 저장
        // 인덱스 0~8까지 각 위치에 해당하는 모래를 퍼센트에 따라 전파
        for (int i = 0; i < 9; i++) {
            // 각 위치의 좌표 (현재 위치 기준 오프셋을 적용)
            int nx = x + sandx[d][i];
            int ny = y + sandy[d][i];
            int Per = sandval[i]; // 해당 위치의 확산 비율
            // 퍼센트에 따른 모래의 양 계산
            int Plus = (Temp * Per) / 100;
            // 만약 target 칸이 격자 범위를 벗어나면 ans에 누적
            if (OOB(nx, ny))
                ans += Plus;
            else
                matrix[ny][nx] += Plus; // 격자 내라면 해당 칸에 모래 추가
            // 모래 분배한 만큼 빼기
            Sand -= Plus;
        }

        // 남은 모래(알파 칸)를 분배할 위치 계산 (인덱스 9 사용)
        int nx = x + sandx[d][9];
        int ny = y + sandy[d][9];
        // 범위를 벗어나면 ans에 누적, 아니면 해당 칸에 추가
        if (OOB(nx, ny))
            ans += Sand;
        else
            matrix[ny][nx] += Sand;
    }

    // 주어진 (x, y)가 격자 범위를 벗어났는지 확인하는 메서드
    public static boolean OOB(int x, int y) {
        return (x < 0 || y < 0 || x >= N || y >= N);
    }
}