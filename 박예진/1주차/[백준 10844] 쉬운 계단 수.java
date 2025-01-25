import java.util.*;
import java.io.*;

class Main
{
    static int N;
    static final int MOD = 1000000000;
    static int[][] dp;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	dp = new int[N + 1][10];
    	
    	dynamic();
    	
    	int res = 0;
    	for(int i = 0; i < 10; i++) {
    		res = (res + dp[N][i]) % MOD;
    	}
    	
    	System.out.println(res);
    }
    
    static void dynamic() {
    	// 첫번재 자리수는 오른쪽 맨 끝의 자리수이므로 경우의수 1개
    	for(int i = 1; i < 10; i++) {
    		dp[1][i] = 1;
    	}
    	
        // 두번째 자리수부터 N까지 탐색
    	for(int i = 2; i <= N; i++) {
        	// i번째 자릿수의 자릿값들을 탐색 (0~9)
    		for(int j = 0; j < 10; j++) {
            	// 0이면 이전 자릿수의 1만 가능
    			if (j == 0) 
    				dp[i][0] = dp[i - 1][1];
                // 9이면 이전 자릿수의 8만 가능
    			else if (j == 9) 
    				dp[i][9] = dp[i - 1][8];
                // 그 외면 이전 자릿수의 자릿값 +1, -1의 합
    			else 
    				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
    			
    			dp[i][j] %= MOD;
    		}
    	}
    }

}
