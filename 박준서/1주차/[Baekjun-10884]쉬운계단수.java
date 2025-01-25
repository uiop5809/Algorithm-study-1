import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static final int[] dx = {0,1,1,1,0,-1,-1,-1};
	private static final int[] dy = {-1,-1,0,1,1,1,0,-1};
	private static final long MOD = 1000000000;
	private static final long MAXNUM = 21;
	
	public static void main(String args[]) throws Exception {
		String[] in = br.readLine().split(" ");
		int n = Integer.parseInt(in[0]);
		int ans = 0;
		long[][]DP = new long[10][101];
		for(int i = 0;i<10;i++) {
			Arrays.fill(DP[i], 0);
			if(i==0)continue;
			DP[i][1] = 1;
		}
		for(int i = 2;i<=n;i++) {
			for(int j = 0;j<10;j++) {
				if(j>0)
					DP[j][i]+=DP[j-1][i-1];
					DP[j][i]%=MOD;
				if(j<9)
					DP[j][i]+=DP[j+1][i-1];
					DP[j][i]%=MOD;
								
			}
		}
		for(int i = 0;i<10;i++) {
			ans+=DP[i][n];
			ans%=MOD;
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();

	}
}