import java.util.*;
import java.io.*;

class Main
{
	static int N;
	static int[] dp;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	dp = new int[N + 1];
    	
    	dynamic();
    	System.out.println(dp[N]);
    }
    
    static void dynamic() {
    	dp[0] = 1;
    	dp[1] = 3;
    	
    	for(int i = 2; i <= N; i++) {
    		dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
    	}
    }
    
}
