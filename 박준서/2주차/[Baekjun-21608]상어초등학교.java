import java.io.*;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static int MAXNUM = 21;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	
	private static String[] in;
	private static int N;
	private static int[][] matrix = new int[MAXNUM][MAXNUM];
	private static HashMap<Integer, Integer[]> hm = new HashMap<>();
	
	static class Block implements Comparable<Block>{
		int x, y, empty, like;

		public Block(int x, int y, int empty, int like) {
			this.x = x;
			this.y = y;
			this.empty = empty;
			this.like = like;
		}

		@Override
		public int compareTo(Block o) {
			if(this.like < o.like)return 1;
			else if(this.like == o.like) {
				
				if(this.empty < o.empty)return 1;
				else if(this.empty == o.empty) {
					
					if(this.y > o.y)return 1;
					else if(this.y == o.y) {
						
						if(this.x > o.x)return 1;
					}
				}
			}
			return -1;
		}
		
	}
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);

		for(int i = 0;i < N * N;i++) {
			in = br.readLine().split(" ");
			int stu = Integer.parseInt(in[0]);
			int li1 = Integer.parseInt(in[1]);
			int li2 = Integer.parseInt(in[2]);
			int li3 = Integer.parseInt(in[3]);
			int li4 = Integer.parseInt(in[4]);
			
			hm.put(stu, new Integer[] {li1, li2, li3, li4});
			setPoint(stu);
		}
		
		sb.append(calc()).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	public static void setPoint(int stu){
		Integer friend[] = hm.get(stu);
		
		ArrayList<Block> candidate = new ArrayList<>();
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) {
				int friendCnt = 0;
				int emptyCnt = 0;
				for(int k = 0;k < 4;k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

					if(Arrays.asList(friend).contains(matrix[ny][nx])) friendCnt++;
					if(matrix[ny][nx] == 0) emptyCnt++;
				}
				candidate.add(new Block(j, i, emptyCnt, friendCnt));
			}
		}
		Collections.sort(candidate);
		
		for(Block b:candidate) {
			if(matrix[b.y][b.x] == 0) {
				matrix[b.y][b.x] = stu;
				return;
			}
		}
	}
	
	public static int calc() {
		int sum=0;
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < N;j++) {
				int cnt = 0;
				Integer friend[] = hm.get(matrix[i][j]);
				
				for(int k = 0;k < 4;k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N)continue;
					
					if(Arrays.asList(friend).contains(matrix[ny][nx]))cnt++;
				}
				
				if(cnt == 0) continue;
				sum += Math.pow(10, cnt - 1);
			}
		}
		return sum;
	}
}