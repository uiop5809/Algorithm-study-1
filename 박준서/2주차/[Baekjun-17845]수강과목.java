import java.io.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	
	private static String[] in;
	private static int[][] DP = new int[1001][10001];
	private static Block[] lecture = new Block[1001];
	private static int N,K;
	
	static class Block{
		int im,time;
		Block(int im, int time){
			this.im=im;
			this.time=time;
		}
	}
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		K = Integer.parseInt(in[1]);
		
		for(int i = 1;i<=K;i++) {
			int im,time;
			in = br.readLine().split(" ");
			im = Integer.parseInt(in[0]);
			time = Integer.parseInt(in[1]);
			lecture[i] = new Block(im,time);
		}
		
		for(int i = 1;i<=K;i++) {
			for(int j = 1;j<=N;j++) {
				if(lecture[i].time>j) {
					DP[i][j] = DP[i-1][j];
				}else {
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-lecture[i].time]+lecture[i].im);
				}
			}
		}
		
		sb.append(DP[K][N]).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}