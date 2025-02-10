import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int MAXNUM = 17;
	
	private static int[][][] DP = new int[3][MAXNUM][MAXNUM];
	private static int[][] metrix = new int[MAXNUM][MAXNUM];
	private static String[] in;
	private static int metSize,ans;
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");
		metSize = Integer.parseInt(in[0]);
		
		for(int i = 0;i<metSize;i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0;j<metSize;j++) {
				metrix[i+1][j+1] = Integer.parseInt(in[j]);
			}
		}
		
		DP[0][1][2] = 1; // 0: 가로  1: 대각선  2: 세로
		for(int i = 1;i<=metSize;i++) {
			for(int j = 1;j<=metSize;j++) {
				if(metrix[i][j] == 1)continue;
				DP[0][i][j]+= DP[0][i][j-1]+DP[1][i][j-1];
				DP[2][i][j]+= DP[1][i-1][j]+DP[2][i-1][j];
				if(metrix[i-1][j] == 1 || metrix[i][j-1] == 1)continue;
				DP[1][i][j]+= DP[0][i-1][j-1]+DP[1][i-1][j-1]+DP[2][i-1][j-1];
			}
		}

		ans = DP[0][metSize][metSize]+DP[1][metSize][metSize]+DP[2][metSize][metSize];
		bw.write(ans+"\n");
		bw.flush();
		bw.close();

	}
}