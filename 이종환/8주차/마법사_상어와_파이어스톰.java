import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법사_상어와_파이어스톰 {
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static int[][] arr;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<>();
	
	static int size;
	static int total = 0;
	static int largest = 0;
	
	public static void main(String[] args) throws IOException {
		//우선 입력 받음
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer (br.readLine());
		
		size = 1 << Integer.parseInt(st.nextToken());
		int magicAmount = Integer.parseInt(st.nextToken());
		
		arr = new int[size][size];
		
		//배열 생성
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 마법 하나 씩 실행
		st = new StringTokenizer (br.readLine());
		for (int i = 0; i < magicAmount; i++) {
			int level = Integer.parseInt(st.nextToken());
			turnAll(level);
			reduceIce();
		}
		
		// 가장 큰 덩어리를 구하며 얼음의 합도 구함
		getLargest();
		
		System.out.println(total);
		System.out.println(largest);
		
	}
	
	private static void turnAll(int level) {
		if (level == 0) return;
		turnAll(level-1);
		
		int tSize = (1<<level);
		int tCnt = size/tSize;
		for (int i = 0; i < tCnt; i++) {
			for (int j = 0; j < tCnt; j++) {
				int stY = tSize*i;
				int stX = tSize*j;
				turn(stY,stX, tSize);
			}
		}
	}
	
	private static void turn(int stY, int stX, int tSize) {
		int oSize = tSize/2;
		int[][] temp = new int[oSize][oSize];
		for (int i = 0; i < oSize; i++) {
			for (int j = 0; j < oSize; j++) {
				temp[i][j] = arr[stY+oSize+i][stX+j];
			}
		}
		
		for (int i = 0; i < oSize; i++) {
			for (int j = 0; j < oSize; j++) {
				arr[stY+oSize+i][stX+j] = arr[stY+oSize+i][stX+oSize+j];
				arr[stY+oSize+i][stX+oSize+j] = arr[stY+i][stX+oSize+j];
				arr[stY+i][stX+oSize+j] = arr[stY+i][stX+j];
				arr[stY+i][stX+j] = temp[i][j];
			}
		}
		
	}
	
	private static void reduceIce() {
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] == 0) continue;
				int nearCnt = 0;
				for (int k = 0; k < 4; k++) {
					int y = i + dy[k];
					int x = j + dx[k];
					
					if ( y < 0 || x < 0 || y >= size || x >= size
							|| arr[y][x] == 0) continue;
					
					nearCnt++;
				}
				if (nearCnt >= 3) continue;
				q.add(new Point(j,i));
			}
		}
		
		//감소 대상 일괄 처리
		while(!q.isEmpty()) {
			Point target = q.poll();
			arr[target.y][target.x]--;
		}
	}
	
	private static void getLargest() {
		visited = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (visited[i][j] || arr[i][j] == 0) continue;
				bfs(i,j);
			}
		}
	}
	
	private static void bfs(int y,int x) {
		visited[y][x] = true;
		total += arr[y][x];
		int cnt = 1;
		
		q.add(new Point(x,y));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i <4 ; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if (ny<0 || nx<0 || ny>=size || nx>= size
						|| visited[ny][nx] || arr[ny][nx] == 0) continue;
				cnt++;
				visited[ny][nx] = true;
				total += arr[ny][nx];
				q.add(new Point(nx,ny));
			}
		}
		
		largest = Math.max(largest, cnt);
	}
}
