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

    private static int TC, N, M;
    private static int[] parent;

    public static void main(String args[]) throws Exception {
        TC = nextInt();
        for (int t = 1; t <= TC; t++) {
            bw.write(String.format("Scenario %d:\n", t));
            N = nextInt();
            M = nextInt();

            parent = new int[N];
            for (int i = 1; i < N; i++)
                parent[i] = i;

            // 간선 정보 입력 및 union 연산 수행
            while (M-- != 0)
                union(nextInt(), nextInt());

            M = nextInt();
            // 각 쿼리에 대해 두 노드가 같은 집합에 속하는지 확인
            while (M-- != 0)
                bw.write((isUnion(nextInt(), nextInt()) ? 1 : 0) + "\n");

            if (t != TC)
                bw.write("\n");
        }
        bwEnd();
    }

    // 두 노드가 같은 집합에 속하는지 확인하는 메소드
    public static boolean isUnion(int a, int b) {
        return find(a) == find(b);
    }

    // 노드의 루트를 찾는 메소드 (경로 압축 최적화 포함)
    public static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    // 두 집합을 합치는 메소드
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}
