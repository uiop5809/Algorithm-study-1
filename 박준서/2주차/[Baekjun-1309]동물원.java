import java.io.*;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static final int MAXNUM = 100001;
	private static final int MOD = 9901;
	
	private static String[] in;
	private static int n,ans;
	private static int DP[][] = new int[3][MAXNUM] ;
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");

		n = Integer.parseInt(in[0]);
		DP[0][1] = DP[1][1] = DP[2][1] = 1; // 0: 왼쪽 1: 오른쪽 2: 둘다 없음
		for(int i = 2;i<=n;i++) {
			DP[0][i] += DP[1][i-1] + DP[2][i-1];
			DP[0][i] %= MOD;
			DP[1][i] += DP[0][i-1] + DP[2][i-1];
			DP[1][i] %= MOD;
			DP[2][i] += DP[0][i-1] + DP[1][i-1] + DP[2][i-1]; // 빈칸다음은 모든 케이스 가능
			DP[2][i] %= MOD;
		}

		ans=DP[0][n]+DP[1][n]+DP[2][n];
		
		sb.append(ans%MOD).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}