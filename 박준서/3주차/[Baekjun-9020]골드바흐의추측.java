import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    
    private static final int MAXNUM = 10001;

    private static String[] in;
    private static int n;  // 테스트 케이스 개수
    private static boolean[] prime = new boolean[MAXNUM];  // 소수 판별 배열

    public static void main(String args[]) throws Exception {
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);

        // 에라토스테네스의 체를 이용한 소수 판별
        Arrays.fill(prime, true);  // 모든 수를 소수로 초기화
        prime[0] = false;prime[1] = false;  // 0과 1은 소수가 아님
        for(int i = 2;i<MAXNUM;i++) {
            if(!prime[i])continue;  // 이미 소수가 아닌 것으로 판별된 수는 건너뜀
            // i의 배수들을 모두 소수가 아닌 것으로 표시
            for(int j = i*2;j<MAXNUM;j+=i) {
                prime[j] = false;
            }
        }
        
        // 각 테스트 케이스 처리
        for(int i = 0;i<n;i++) {
            in = br.readLine().split(" ");
            int num = Integer.parseInt(in[0]);
            // num/2부터 시작하여 두 소수의 합으로 표현 가능한 경우를 찾음
            // 차이가 가장 작은 두 소수를 찾기 위해 num/2부터 감소하며 탐색
            for(int j = num/2;j>0;j--) {
                if(prime[j] && prime[num-j]) {
                    sb.append(j).append(" ").append(num-j).append("\n");
                    break;
                }
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
