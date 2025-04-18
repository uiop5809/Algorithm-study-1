import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int size,stair,ans = 0;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		stair = Integer.parseInt(st.nextToken());
		arr = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) ;
			}
		}
	}
	
	
	private static void process() {
		for (int i = 0; i < size; i++) {
			int[] wayX = new int[size];
			int[] wayY = new int[size];
			for (int j = 0; j < size; j++) {
				wayX[j] = arr[i][j];
				wayY[j] = arr[j][i];
			}
			
			processBridge(wayY);
			processBridge(wayX);
			
		}
	}

	
	private static void processBridge(int[] way) {
		// 다리가 놓아지는 곳을 보여주는 visited
		boolean[] visited = new boolean[size];
		for (int i = 0; i < size -1 ; i++) {
			int next = way[i+1];
			int pre = way[i];
			if (pre-next == 1) {
				for (int j = 1; j <= stair; j++) {
					int idx = i + j;
					if (idx < 0 || idx >= size || way[idx] != next ||  visited[idx]) return;
					visited[idx] = true;
				}
			} else if (next-pre == 1) {
				for (int j = 0; j < stair; j++) {
					int idx = i - j;
					if (idx < 0 || idx >= size || way[idx] != pre ||  visited[idx]) return;
					visited[idx] = true;
				}
			}else if (way[i]==way[i+1]) continue;
			else return;
		}
		ans++;
		return ;
	}
	


	private static void print() {System.out.println(ans);}
}
