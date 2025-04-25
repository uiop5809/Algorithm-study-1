import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int goal = Integer.parseInt(br.readLine());
		int[][] dp = new int[2001][2001];
		
		for (int i = 0; i < goal+1; i++)Arrays.fill(dp[i],Integer.MAX_VALUE);
		dp[1][0] = 0;
		
		Queue<EmoInfo> q = new LinkedList<>();
		
		q.add(new EmoInfo(1,0,0));
		while(!q.isEmpty()) {
			EmoInfo info = q.poll();
			
			int sCnt = info.screenEmo;
			int cCnt = info.clipBoardEmo;
			int cnt = info.cnt;
			
			if(sCnt > 0 && dp[sCnt-1][cCnt] > cnt+1) {
				dp[sCnt-1][cCnt] = cnt+1;
				q.add(new EmoInfo(sCnt-1,cCnt,cnt+1));
			}
			
			if(dp[sCnt][sCnt] > cnt+1) {
				dp[sCnt][sCnt] = cnt+1;
				q.add(new EmoInfo(sCnt,sCnt,cnt+1));
			}
			if(sCnt+cCnt < 2000 &&  dp[sCnt+cCnt][cCnt]> cnt+1) {
				dp[sCnt+cCnt][cCnt] = cnt+1;
				q.add(new EmoInfo(sCnt+cCnt,cCnt,cnt+1));
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i <= 2000; i++) {
			ans = Math.min(ans, dp[goal][i]);
		}
		System.out.println(ans);
		
	}
	
	static class EmoInfo{
		int screenEmo;
		int clipBoardEmo;
		int cnt;
		public EmoInfo(int screenEmo, int clipBoardEmo, int cnt) {
			super();
			this.screenEmo = screenEmo;
			this.clipBoardEmo = clipBoardEmo;
			this.cnt = cnt;
		}
	}
}
