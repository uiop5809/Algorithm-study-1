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
	
	static class Block{
		int x,y;
		Block(int y,int x){
			this.y=y;
			this.x=x;
		}
	}
	
	public static void main(String args[]) throws Exception {
		Block[] bing = new Block[25];
		int[][] bingGo = new int [2][5];
		int[] diagonal = new int [2];
		int n,ans=0,cnt=0,bingCnt=0;
		for(int i = 0;i<5;i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0;j<5;j++) {
				n = Integer.parseInt(in[j]);
				bing[n-1] = new Block(i,j);
			}
		}
		for(int i = 0;i<5;i++) {
			String[] in = br.readLine().split(" ");
			for(int j = 0;j<5;j++) {
				if(ans!=0)continue;
				cnt++;
				n = Integer.parseInt(in[j]);
				int nowY = bing[n-1].y;
				int nowX = bing[n-1].x;
				if(++bingGo[0][nowX]>=5) bingCnt++;
				if(++bingGo[1][nowY]>=5) bingCnt++;
				if(nowX==nowY)
					if(++diagonal[0]>=5) bingCnt++;
				if(nowX+nowY==4)
					if(++diagonal[1]>=5) bingCnt++;
				if(bingCnt>=3)ans=cnt;
			}
		}
		
		
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();

	}
}