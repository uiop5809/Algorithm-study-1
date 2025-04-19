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
        bw.close();
        bw.close();
    }

    private static int N, ans;
    private static ArrayList<Integer> arr;

    public static void main(String args[]) throws Exception {
        N = nextInt();

        arr = new ArrayList<>();

        for (int i = 0; i < N; i++)
            arr.add(nextInt());

        arr.sort(Comparator.naturalOrder());

        int arrMax = arr.get(N - 1); // 수열의 최대값
        int arrMin = arr.get(0); // 수열의 최소값

        // 최대 차이 계산
        for (int i = 1; i < N - 1; i++) {
            int tmp = arr.get(i);
            ans = Math.max(ans, Math.max(
                    Math.abs(tmp * 3 - (arrMin + tmp + arr.get(i + 1))), // 현재 값과 최소값, 다음 값의 차이
                    Math.abs(tmp * 3 - (arr.get(i - 1) + tmp + arrMax)))); // 현재 값과 이전 값, 최대값의 차이
        }

        // 결과 출력
        bw.write(ans + "\n");
        bwEnd();
    }
}
