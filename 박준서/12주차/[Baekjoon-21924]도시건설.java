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

    private static int N, M, cnt = 1;
    private static long ans;
    private static ArrayList<int[]> graph;
    private static int[] parent;

    public static void main(String args[]) throws Exception {
        N = nextInt();
        M = nextInt();

        parent = new int[N];
        graph = new ArrayList<>();
        for (int i = 1; i < N; i++)
            parent[i] = i;
        // 간선 정보 입력 및 총 비용 계산
        for (int i = 0; i < M; i++) {
            graph.add(new int[] { nextInt() - 1, nextInt() - 1, nextInt() });
            ans += graph.get(i)[2];
        }

        // 간선을 비용 기준으로 오름차순 정렬
        graph.sort((a, b) -> {
            return a[2] - b[2];
        });

        int p = 0;
        // 크루스칼 알고리즘 수행
        while (cnt < N && p < M) {
            int[] now = graph.get(p++);
            if (union(now[0], now[1])) {
                ans -= now[2];
                cnt++;
            }
        }
        // 모든 정점이 연결되지 않았다면 -1 출력
        if (cnt < N)
            ans = -1;
        bw.write(ans + "\n");
        bwEnd();
    }

    // 노드의 루트를 찾는 메소드 (경로 압축 최적화 포함)
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    // 두 집합을 합치는 메소드
    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return false;
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
        return true;
    }
}
