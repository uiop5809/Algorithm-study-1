import java.io.*;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static int MAXNUM = 300001;
	
	private static String[] in;
	private static int N, M, K, X;
	private static ArrayList<ArrayList<Integer>> vec = new ArrayList<>();
	private static int[] visited = new int[MAXNUM];
	private static ArrayList<Integer> city = new ArrayList<Integer>();
	
	static class Block{
		public int a, b;
		
		Block(int a, int b){
			this.a = a;
			this.b = b;
		}
	}
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");

		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		K = Integer.parseInt(in[2]);
		X = Integer.parseInt(in[3]);
		
		for(int i = 0;i <= N;i++)vec.add(new ArrayList<Integer>());
		
		for(int i = 0;i < M;i++) {
			in = br.readLine().split(" ");
			int a = Integer.parseInt(in[0]), b = Integer.parseInt(in[1]);
			vec.get(a).add(b); // 유향 그래프
		}
		
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(X,0));
		visited[X] = -1; // 처음 출발지 안바꾸면 오류발생 사이클 있는 테케가 있는듯
		while(!q.isEmpty()) {
			int now = q.peek().a;
			int d = q.peek().b;
			q.poll();
			
			for(int next:vec.get(now)) {
				if(visited[next] != 0) continue;
				visited[next] = d + 1;
				q.add(new Block(next, d + 1));
			}
		}
		
		for(int i = 1;i <= N;i++) {
			if(visited[i] == K) {
				city.add(i);
			}
		}
		
		if(city.isEmpty()) {
			sb.append("-1").append("\n");
		}
		else {
			for(int c:city) {
				sb.append(c).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}