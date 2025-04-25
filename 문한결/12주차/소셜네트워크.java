import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소셜네트워크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k, m, t;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            init();
            System.out.println("Scenario " + i + ":");
            execute();
            System.out.println();
        }
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (find(a) != find(b)) union(a, b);
        }

        m = Integer.parseInt(br.readLine());
    }

    static void execute() throws Exception {
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(find(a) == find(b) ? 1 : 0);
        }
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}
