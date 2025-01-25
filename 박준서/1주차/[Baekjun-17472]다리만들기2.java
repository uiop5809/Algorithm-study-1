import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static final int[] dx = {0,0,1,-1};
	private static final int[] dy = {-1,1,0,0};
	private static final int MAXNUM = 12;
	
	private static int[][] metrix = new int[MAXNUM][MAXNUM];
	private static int[][] visit = new int[MAXNUM][MAXNUM];
	private static int[] unionFind = new int[MAXNUM];
	
	private static String[] in;
	private static int n,m,ans=Integer.MAX_VALUE,islandCnt=1;
	
	private static PriorityQueue<Block> pq = new PriorityQueue<>(1,new BlockComp());
	
	static class Block{
		int x,y,d;
		Block(int x, int y, int d){
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}
	static class BlockComp implements Comparator<Block>{
		@Override
		public int compare(Block a, Block b) {
			if(a.d<b.d) return -1;
			else return 1;
		}
	}
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");
		n = Integer.parseInt(in[0]);
		m = Integer.parseInt(in[1]);
		for(int i = 1;i<=n;i++) {
			in = br.readLine().split(" ");
			for(int j = 0;j<m;j++) {
				metrix[i][j+1]=Integer.parseInt(in[j]);
			}
		}
		for(int i = 1 ;i<MAXNUM;i++) {
			unionFind[i]=i;
		}
		
		for(int i = 1;i<=n;i++) 
			for(int j = 1;j<=m;j++) 
				if(metrix[i][j] != 0 && visit[i][j] == 0)
					measureIsland(i, j, islandCnt++);
		

		for(int i = 1;i<=n;i++) 
			for(int j = 1;j<=m;j++) 
				if(metrix[i][j] != 0)
					findRoute(i, j);
		
		 
		ans=Math.min(ans, kruskal());
		
		if(ans == Integer.MAX_VALUE)ans=-1;
		sb.append(ans).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	public static void measureIsland(int y, int x,int num) {
		metrix[y][x] = num;
		for(int i = 0;i<4;i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(nx<1||ny<1||nx>m||ny>n||metrix[ny][nx] == 0||
					visit[ny][nx] == 1)continue;
			visit[ny][nx] = 1;
			metrix[ny][nx] = num;
			measureIsland(ny, nx, num);
		}
	}
	public static void findRoute(int y, int x) {
		for(int i = 0;i<4;i++) {
			int step=1;
			int ny = y+dy[i];
			int nx = x+dx[i];
			boolean out=false;
			while(metrix[ny][nx]==0) {
				if(nx<=0||ny<=0||nx>m||ny>n) {
					out=true;
					break;
				}
				ny+=dy[i];
				nx+=dx[i];
				step++;
			
			}
			if(out || step<3 || metrix[y][x] == metrix[ny][nx])continue;
			pq.add(new Block(metrix[y][x],metrix[ny][nx],step));
		}
	}
	
	public static int kruskal() {
		int cnt=0;
		while(!pq.isEmpty()) {
			int is1=pq.peek().x;
			int is2=pq.peek().y;
			int d = pq.peek().d;
			pq.poll();
			int is1P=findParent(is1);
			int is2P=findParent(is2);
			if(is1P==is2P) {
				continue;
			}
			union(is1P,is2P);
			cnt+=d-1;
			
		}
		
		if(isAllUnioned())return cnt;
		else return Integer.MAX_VALUE;
	}
	
	public static int[][] initList(int[][] arr) {
		for(int i = 1;i<=arr.length;i++)
			Arrays.fill(arr[i],0);
		return arr;
	}
	public static void printList(int[][] arr) {
		for(int i = 1;i<=n;i++) {
			for(int j = 1;j<=m;j++) {
				System.out.printf("%d ",metrix[i][j]);
			}System.out.println();
		}
	}
	
	public static int findParent(int f) {
		if(f==unionFind[f]) return f;
		else return unionFind[f] = findParent(unionFind[f]);
	}
	public static void union(int a, int b) {
		int findA=findParent(a);
		int findB=findParent(b);
		if(findA!=findB)unionFind[findA]=findB;
	}
	public static boolean isAllUnioned() {
		int p = findParent(1);
		for(int i = 2;i<islandCnt;i++) {
			if(findParent(i)!=p)
				return false;
		}
		return true;
	}
	
}