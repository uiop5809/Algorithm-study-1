import java.util.*;
import java.io.*;


public class 스파이 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int arr[][];
    static int input[];
    static int answer;
    static boolean isVisted[][];

    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        arr = new int[2][3];
        for (int i = 0; i < 2; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 3; j++)
                arr[i][j] = input[j];

        }
        isVisted = new boolean[2][3];
        dfs(0, 0, -1, 0);
        System.out.println(answer);


    }

    static void dfs(int cnt, int sum, int x, int y) {

        if (cnt == N) {
            if (sum >= M)
                answer++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int sumAll = 0;
                if (x == j)
                    sumAll = (sum + arr[i][j] / 2);
                else
                    sumAll = (sum + arr[i][j]);
                dfs(cnt + 1, sumAll, j, i);
            }
        }
    }

}
