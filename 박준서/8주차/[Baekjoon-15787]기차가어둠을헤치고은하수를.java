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

    private static int N, M; // N: 기차의 수, M: 명령의 수
    private static int[] trains; // 각 기차의 상태 (비트 마스크로 표현)

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();

        trains = new int[N];

        for (int i = 0; i < M; i++) {
            readLine();
            int a = nextInt(), b = nextInt() - 1, c; // 명령 종류, 기차 번호, 추가 정보

            switch (a) {
                case 1: // 1번 기차에 c번칸에 사람 태우기
                    c = nextInt() - 1;
                    trains[b] |= (1 << c); // c번칸에 1을 설정
                    break;
                case 2: // 2번 기차에 c번칸에 사람 내리기
                    c = nextInt() - 1;
                    trains[b] &= ~(1 << c); // c번칸에 0을 설정
                    break;
                case 3: // 3번 기차 한 칸씩 왼쪽으로 이동
                    trains[b] <<= 1; // 왼쪽으로 한 칸 이동
                    trains[b] &= (1 << 20) - 1; // 20비트로 제한 (오버플로 방지)
                    break;
                case 4: // 4번 기차 한 칸씩 오른쪽으로 이동
                    trains[b] >>>= 1; // 오른쪽으로 한 칸 이동
                    break;
            }
        }

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < N; i++)
            s.add(trains[i]); // 각 기차의 상태를 집합에 추가

        bw.write(s.size() + "\n");

        printAnswer();
    }
}
