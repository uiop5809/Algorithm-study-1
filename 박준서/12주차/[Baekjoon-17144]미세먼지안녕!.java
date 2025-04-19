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
        bw.flush();
        bw.close();
    }

    // 상하좌우 이동을 위한 방향 배열
    // 좌, 우, 상, 하
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    private static int R, C, T, cnt; // R: 행 수, C: 열 수, T: 시간, cnt: 미세먼지 총량
    private static int[] robot = { -1, -1 }; // 공기청정기 위치 (상단, 하단)
    private static int[][] matrix; // 방의 상태를 저장할 2차원 배열

    public static void main(String args[]) throws Exception {
        R = nextInt();
        C = nextInt();
        T = nextInt();

        matrix = new int[R][C];
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++) {
                matrix[i][j] = nextInt();
                if (matrix[i][j] == -1) // 공기청정기 위치 저장
                    if (robot[0] == -1) // 첫 번째 공기청정기(상단)
                        robot[0] = i;
                    else // 두 번째 공기청정기(하단)
                        robot[1] = i;
                else // 미세먼지인 경우 총량에 추가
                    cnt += matrix[i][j];
            }

        // T초 동안 시뮬레이션 실행
        while (T-- != 0) {
            spread(); // 미세먼지 확산
            conditioner(); // 공기청정기 작동
        }

        bw.write(cnt + "\n");
        bwEnd();
    }

    // 미세먼지 확산 메소드
    public static void spread() {
        int[][] nextMat = new int[R][C]; // 확산 후 상태를 저장할 임시 배열

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int rest = matrix[i][j]; // 현재 칸의 미세먼지 양
                int dust = rest / 5; // 확산될 미세먼지 양 (5분의 1)

                if (rest < 5) { // 확산할 양이 없는 경우 (5보다 작으면 확산 안됨)
                    nextMat[i][j] += matrix[i][j];
                    continue;
                }

                for (int k = 0; k < 4; k++) { // 상하좌우 네 방향으로 확산
                    int ny = i + dy[k];
                    int nx = j + dx[k];

                    // 범위를 벗어나거나 공기청정기가 있는 위치면 확산 안함
                    if (OOB(ny, nx) || matrix[ny][nx] == -1)
                        continue;

                    rest -= dust; // 확산된 양만큼 현재 칸에서 감소
                    nextMat[ny][nx] += dust; // 인접 칸에 확산된 양 추가
                }
                nextMat[i][j] += rest; // 남은 미세먼지 양을 현재 칸에 저장
            }
        }
        matrix = nextMat; // 확산 후 상태로 배열 업데이트
    }

    // 공기청정기 작동 메소드
    public static void conditioner() {
        // 상단 공기청정기 순환 (반시계 방향)
        cnt -= matrix[robot[0] - 1][0];

        // 왼쪽 세로 줄 위로 이동
        for (int y = robot[0] - 1; y >= 1; y--)
            matrix[y][0] = matrix[y - 1][0];
        // 위쪽 가로 줄 왼쪽으로 이동
        for (int x = 0; x < C - 1; x++)
            matrix[0][x] = matrix[0][x + 1];
        // 오른쪽 세로 줄 아래로 이동
        for (int y = 0; y < robot[0]; y++)
            matrix[y][C - 1] = matrix[y + 1][C - 1];
        // 아래쪽 가로 줄 오른쪽으로 이동
        for (int x = C - 1; x > 1; x--)
            matrix[robot[0]][x] = matrix[robot[0]][x - 1];
        matrix[robot[0]][1] = 0;

        // 하단 공기청정기 순환 (시계 방향)
        cnt -= matrix[robot[1] + 1][0];

        // 왼쪽 세로 줄 아래로 이동
        for (int y = robot[1] + 1; y < R - 1; y++)
            matrix[y][0] = matrix[y + 1][0];
        // 아래쪽 가로 줄 왼쪽으로 이동
        for (int x = 0; x < C - 1; x++)
            matrix[R - 1][x] = matrix[R - 1][x + 1];
        // 오른쪽 세로 줄 위로 이동
        for (int y = R - 1; y > robot[1]; y--)
            matrix[y][C - 1] = matrix[y - 1][C - 1];
        // 위쪽 가로 줄 오른쪽으로 이동
        for (int x = C - 1; x > 1; x--)
            matrix[robot[1]][x] = matrix[robot[1]][x - 1];
        matrix[robot[1]][1] = 0;
    }

    // 좌표가 범위를 벗어나는지 확인
    public static boolean OOB(int y, int x) {
        return (y < 0 || x < 0 || y >= R || x >= C);
    }
}
