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

    private static int[] dx = { 0, 0, -1, 1 };
    private static int[] dy = { -1, 1, 0, 0 };

    private static int N, M, T;
    private static int[][] matrix;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();
        T = nextInt();

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = nextInt();
            }
        }

        // T번의 회전 명령을 차례로 처리
        for (int t = 0; t < T; t++) {
            readLine();
            int x = nextInt(); // x: x의 배수 번째 원판을 회전
            int d = nextInt(); // d: 회전 방향 (0이면 시계 반대, 1이면 시계 방향)
            int k = nextInt(); // k: 회전 칸 수

            // x의 배수 번째 원판들을 찾아 회전시킴
            for (int i = x - 1; i < N; i += x) {
                matrix[i] = rotate(matrix[i], d, k);
            }

            // 인접하면서 같은 숫자를 갖는 칸을 제거하는 작업
            // 만약 인접한 동일 숫자가 하나라도 있으면 해당 칸의 숫자를 0으로 만듦
            if (!removeNear()) {
                // 만약 제거할 숫자가 없다면 남아있는 숫자들의 평균을 구하여 조정함
                adjustNumbers();
            }
        }

        bw.write(calc() + "\n");
        printAnswer();
    }

    // 주어진 1차원 배열(원판)을 회전시키는 메서드
    // 매개변수: 배열 arr, 회전 방향 d, 회전 칸수 k
    // d가 0이면 시계 반대방향(오른쪽으로 이동), d가 1이면 시계 방향(왼쪽으로 이동)
    static int[] rotate(int[] arr, int d, int k) {
        int[] newArr = new int[M]; // 회전 후 결과를 저장할 새로운 배열
        k %= M; // k가 M을 초과할 경우를 대비해 modulo 연산 수행
        if (d == 0) { // d == 0인 경우: 시계 반대 방향 회전 (원소들이 오른쪽으로 이동)
            for (int j = 0; j < M; j++) {
                newArr[(j + k) % M] = arr[j];
            }
        } else { // d == 1인 경우: 시계 방향 회전 (원소들이 왼쪽으로 이동)
            for (int j = 0; j < M; j++) {
                newArr[(j - k + M) % M] = arr[j];
            }
        }
        return newArr;
    }

    // 원판에서 인접한 칸 중 동일한 숫자가 있는 칸을 찾아 제거(0으로 만듦)
    // 인접한 칸은 4방향(좌, 우, 위, 아래)이며, 열의 경우 원형(좌우 사이클)임.
    static boolean removeNear() {
        boolean[][] toRemove = new boolean[N][M]; // 제거할 칸을 표시하기 위한 배열
        boolean found = false; // 제거 가능한 칸이 존재하는지 여부

        // 모든 원판과 모든 숫자에 대해 검사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 칸의 숫자가 0이면 건너뜀
                if (matrix[i][j] == 0)
                    continue;
                // 4방향(좌, 우, 위, 아래) 인접 칸 확인
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d]; // 인접 행 번호
                    int ny = j + dy[d]; // 인접 열 번호

                    // 열은 원형이므로, 열 인덱스가 범위를 넘으면 반대편으로 변경
                    if (ny < 0)
                        ny = M - 1;
                    else if (ny >= M)
                        ny = 0;

                    // 행은 원형이 아니므로 범위를 벗어나면 무시
                    if (nx < 0 || nx >= N)
                        continue;

                    // 현재 칸과 인접 칸의 숫자가 같으면 둘 다 제거 대상 표시
                    if (matrix[i][j] == matrix[nx][ny]) {
                        toRemove[i][j] = true;
                        toRemove[nx][ny] = true;
                        found = true;
                    }
                }
            }
        }

        // 제거 대상로 표시된 칸의 숫자를 0으로 변경
        if (found) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (toRemove[i][j])
                        matrix[i][j] = 0;
                }
            }
        }

        return found; // 인접한 동일 숫자 제거가 발생하면 true, 아니면 false 반환
    }

    // 인접한 같은 숫자가 없는 경우, 전체 남은 숫자들의 평균을 구하여 조정하는 메서드
    // 평균보다 큰 숫자는 1 감소, 작은 숫자는 1 증가시킴
    static void adjustNumbers() {
        double avg = (double) calc() / (double) count(); // 현재 원판에 있는 숫자들의 평균 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 숫자가 0인 칸은 제외하고 평균과 비교하여 조정
                if (matrix[i][j] > avg) {
                    matrix[i][j]--; // 평균보다 크면 감소
                } else if (matrix[i][j] < avg && matrix[i][j] != 0) {
                    matrix[i][j]++; // 평균보다 작으면 증가
                }
            }
        }
    }

    // 현재 원판에 남아있는 모든 숫자의 합을 계산하여 반환하는 메서드
    public static int calc() {
        int ret = 0;
        for (int[] row : matrix) {
            for (int it : row)
                ret += it;
        }
        return ret;
    }

    // 원판에서 0보다 큰 숫자가 몇 개 있는지 개수를 세는 메서드
    public static int count() {
        int ret = 0;
        for (int[] row : matrix) {
            for (int it : row)
                if (it > 0)
                    ret++;
        }
        return ret;
    }
}