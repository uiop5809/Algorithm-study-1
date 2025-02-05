import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    
    private static final int MAXNUM = 101;

    private static String[] in;
    private static int n,ans;  // n: 수의 개수, ans: 최종 최대공약수
    private static int[] nums = new int[MAXNUM];  // 입력받은 수들을 저장하는 배열

    public static void main(String args[]) throws Exception {
        // 입력 처리
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);
        // n개의 수 입력
        for(int i = 0;i<n;i++) {
            in = br.readLine().split(" ");
            nums[i] = Integer.parseInt(in[0]);
        }

        // 첫 두 수의 차이의 절댓값으로 시작
        ans = Math.abs(nums[0]-nums[1]);
        // 나머지 수들의 차이와 최대공약수를 구함
        for(int i = 2;i<n;i++)ans = gcd(ans,Math.abs(nums[i]-nums[i-1]));
        
        // 구한 최대공약수의 모든 약수를 출력
        for(int i = 2;i<=ans;i++) {
            if(ans%i==0)
                sb.append(i).append(" ");
        }
        
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // 유클리드 알고리즘을 이용한 최대공약수(GCD) 계산
    public static int gcd(int x, int y) {
        return y == 0?x:gcd(y,x%y);
    }
}
