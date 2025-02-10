import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    
    private static final int MAXNUM = 100001;

    private static Long[] route = new Long[MAXNUM];  // 도시간 거리 저장 배열
    private static Long[] oil = new Long[MAXNUM];    // 각 도시의 기름값 저장 배열
    private static String[] in;
    private static int n;  // 도시의 개수
    private static Long cnt=Long.MAX_VALUE,ans = 0L;  // cnt: 현재까지의 최소 기름값, ans: 총 비용

    public static void main(String args[]) throws Exception {
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);

        // 도시간 거리 입력
        in = br.readLine().split(" ");
        for(int i = 0;i<n-1;i++) route[i] = Long.parseLong(in[i]);
        
        // 각 도시의 기름값 입력
        in = br.readLine().split(" ");
        for(int i = 0;i<n;i++) oil[i] = Long.parseLong(in[i]);
        
        // 그리디 알고리즘으로 최소 비용 계산
        // 현재까지 방문한 도시 중 가장 싼 기름값으로 다음 도시까지 이동
        for(int i = 0;i<n-1;i++) {
            cnt = Math.min(cnt, oil[i]);  // 현재까지의 최소 기름값 갱신
            ans += cnt*route[i];  // 최소 기름값 * 거리 = 현재 구간의 비용
        }
        
        sb.append(ans).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
