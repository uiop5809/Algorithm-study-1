import java.util.*;
import java.io.*;

public class 도시건설 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] road;
    static long answer;
    static int[] parent;
    static long minCost, maxCost;
    static int[] input;

    public static void main(String[] args) throws Exception {
        init();
        execute();
        System.out.println(answer);
    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        parent = new int[n + 1];
        road = new int[m][3];
        for (int i = 1; i <= n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 3; j++) road[i][j] = input[j];
            maxCost += input[2];
        }
    }

    static void execute() {
        Arrays.sort(road, Comparator.comparingInt(arr -> arr[2]));
        for (int i = 0; i < m; i++) {
            int[] abc = road[i];
            int a = abc[0];
            int b = abc[1];
            int cost = abc[2];
            a = find(a);
            b = find(b);
            if (a != b) {
                union(a, b);
                minCost += cost;
            }
        }
        answer = maxCost - minCost;
        int a = find(1);
        for (int i = 1; i <= n; i++) {
            if (a != find(i)) {
                answer = -1;
                break;
            }
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
