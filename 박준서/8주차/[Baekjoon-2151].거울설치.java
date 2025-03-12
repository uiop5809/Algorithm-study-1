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

    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { -1, 0, 1, 0 };

    private static char[][] matrix;
    private static boolean[][] visited;
    private static int N;
    private static int ans = (int) 1e9;
    private static int sx = -1, sy = -1, ex, ey; // 시작점과 끝점 좌표

    static class Block {
        int x, y, c; // x좌표, y좌표, 비용

        public Block(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();

        matrix = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = line[j];
                if (line[j] == '#') { // 시작점과 끝점 찾기
                    if (sx == -1 && sy == -1) {
                        sy = i;
                        sx = j;
                    } else {
                        ey = i;
                        ex = j;
                    }
                }
            }
        }

        PriorityQueue<Block> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.c, o2.c));
        pq.add(new Block(sx, sy, 0));
        visited[sy][sx] = true;

        while (!pq.isEmpty()) {
            Block now = pq.poll();
            int x = now.x;
            int y = now.y;
            int cost = now.c;

            // 도착점에 도착하면 최소 비용 갱신
            if (x == ex && y == ey) {
                ans = Math.min(ans, cost - 1);
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 장애물이 아니고 방문하지 않은 칸으로 이동
                while (!OOB(ny, nx) && !visited[ny][nx] && matrix[ny][nx] != '*') {
                    if (matrix[ny][nx] == '!' || matrix[ny][nx] == '#') { // 도착점이나 중간점에 도착하면 방문 표시하고 큐에 추가
                        visited[ny][nx] = true;
                        pq.add(new Block(nx, ny, cost + 1));
                    }
                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }

        bw.write(ans + "\n");

        printAnswer();
    }

    public static boolean OOB(int y, int x) {
        return (y < 0 || x < 0 || y >= N || x >= N);
    }
}
