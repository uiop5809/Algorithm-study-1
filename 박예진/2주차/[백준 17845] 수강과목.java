import java.util.*;
import java.io.*;

class Main
{
	static int N, K; // 최대 공부시간, 과목수
	static int[] I; // 중요도
	static int[] T; // 공부시간
	static int[][] dp; 

    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());

    	I = new int[K + 1];
    	T = new int[K + 1];
    	for(int i = 1; i <= K; i++) {
    		st = new StringTokenizer(br.readLine());
    		I[i] = Integer.parseInt(st.nextToken());
    		T[i] = Integer.parseInt(st.nextToken());
    	}

    	// 해결
    	dp = new int[K + 1][N + 1]; // 중요도, 공부시간
    	knapsack();

      // 출력
    	System.out.println(dp[K][N]);

    }

    static void knapsack(){
    	// 과목수
    	for(int i = 1; i <= K; i++) {
    		// 공부시간
    		for(int j = 1; j <= N; j++) {
    			// 담을 수 있는 경우
    			if (j >= T[i]) {
    				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - T[i]] + I[i]);
    			}
    			// 담을 수 없는 경우
    			else dp[i][j] = dp[i - 1][j];
    		}
    	}
    }

}
