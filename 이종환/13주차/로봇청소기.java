import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int height,width,totalCleanCnt;
	static final int DIRTY = 0;
	static final int WALL = 1;
	static final int CLEAN = -1;
	static int[][] arr;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static Robot r;
	
	static class Robot{
		int y,x,dir;

		public Robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		arr = new int[height][width];
		totalCleanCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		r = new Robot(y,x,dir);
		
		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	
	private static void process() {
		while(true) {
			if (arr[r.y][r.x] == DIRTY) {
				totalCleanCnt++;
				arr[r.y][r.x] = CLEAN;
				continue;
			}
			
			if (isAllClean()) {
				if (!tryGoingBack()) break;
			}
			else goForward();
			
		}
	}

	private static boolean isAllClean() {
		for (int i = 0; i < 4; i++) {
			r.dir +=3;
			r.dir %=4;
			int ny = r.y + dy[r.dir];
			int nx = r.x + dx[r.dir];
				
			if (isInValid(ny,nx) || arr[ny][nx]==CLEAN) continue;
			return false;
		}
		return true;
	}
	
	private static boolean tryGoingBack() {
		int ny = r.y - dy[r.dir];
		int nx = r.x - dx[r.dir];
		if (isInValid(ny,nx)) return false;
		
		r.y = ny;
		r.x = nx;
		return true;
	}

	private static boolean isInValid(int y,int x) {
		if( y < 0 || x <0 || y >= height || x >= width || arr[y][x] == WALL)return true;
		return false;
	}
	
	private static void goForward() {
		r.y += dy[r.dir];
		r.x += dx[r.dir];
	}

	private static void print() {System.out.println(totalCleanCnt);}

}
