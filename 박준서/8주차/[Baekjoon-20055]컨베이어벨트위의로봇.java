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

    private static int N, M, cnt, ans; // N: 컨베이어 벨트의 길이, M: 제거해야 할 칸 수, cnt: 현재 제거된 칸 수, ans: 현재 시간
    private static int[][] belt; // 컨베이어 벨트의 상태 (0: 빈 칸, 1 이상: 칸에 있는 물건)
    private static ArrayList<Integer> robots; // 로봇의 위치 (0부터 2N-1까지)

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();

        belt = new int[2][N];
        robots = new ArrayList<>();

        readLine();
        for (int j = 0; j < N; j++)
            belt[0][j] = nextInt(); // 위쪽 벨트
        for (int j = N - 1; j >= 0; j--)
            belt[1][j] = nextInt(); // 아래쪽 벨트

        // 로봇이 M개의 칸을 제거할 때까지 반복
        while (cnt < M) {
            roll();
            moveRobot();

            if (belt[0][0] != 0) {
                if (--belt[0][0] == 0)
                    cnt++;
                robots.add(0);
            }

            ans++;
        }

        bw.write(ans + "\n");

        printAnswer();
    }

    public static void roll() {
        int tmp = belt[1][0];

        // 벨트를 이동
        for (int i = 0; i < N - 1; i++)
            belt[1][i] = belt[1][i + 1];
        belt[1][N - 1] = belt[0][N - 1];
        for (int i = N - 1; i > 0; i--)
            belt[0][i] = belt[0][i - 1];
        belt[0][0] = tmp;

        ArrayList<Integer> nextRobots = new ArrayList<>();
        for (int i = 0; i < robots.size(); i++) {
            int next = (robots.get(i) + 1) % (2 * N); // 로봇의 다음 위치

            if (next == N) // 로봇이 중간 지점에 도착하면 제거
                continue;

            nextRobots.add(next);
        }
        robots = nextRobots;
    }

    public static void moveRobot() {
        Set<Integer> locations = new HashSet<>(); // 로봇의 위치를 중복 없이 저장
        ArrayList<Integer> nextRobots = new ArrayList<>();

        for (int i = 0; i < robots.size(); i++) {
            int next = (robots.get(i) + 1) % (2 * N); // 로봇의 다음 위치

            int row = next / N, col = next % N; // 로봇의 행과 열

            if (row == 1) // 아래쪽 벨트일 경우 열을 반대로 계산
                col = N - col - 1;

            if (next == N) // 로봇이 도착 지점에 도착하면 제거
                continue;

            if (belt[row][col] != 0 && !locations.contains(next)) { // 칸에 물건이 있고, 아직 방문하지 않은 경우
                locations.add(next); // 방문 표시

                if (--belt[row][col] == 0) // 물건 제거
                    cnt++; // 제거된 칸 수 증가

                nextRobots.add(next); // 로봇의 다음 위치를 저장
            } else { // 칸에 내구도가 없거나 이미 로봇이 있는 경우
                locations.add(robots.get(i)); // 현재 위치를 방문 표시
                nextRobots.add(robots.get(i)); // 로봇의 위치를 그대로 저장
            }
        }

        robots = nextRobots;
    }
}
