import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 거울설치 {
	static String[][] house;
	// 상 부터 시계방향
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][][] visited;
	

	static int size;
	static int min = Integer.MAX_VALUE;
	static Point start;
	static Queue<Light> q = new LinkedList<>();
	
	static class Light {
		int y;
		int x;
		int cnt;
		int dir;
		public Light(int y, int x, int cnt, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.dir = dir;
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		house = new String[size][size];
		start = new Point(-1,-1);
		// 각 위치+방향별 최소 거울 개수
		visited = new int[size][size][4];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Arrays.fill( visited[i][j], Integer.MAX_VALUE);
			}
		}
		
		
		for (int i = 0; i < size; i++) {
			house[i] = br.readLine().split("");
			for (int j = 0; j < size; j++) {
				if ( house[i][j].equals("#")) {
					start = new Point( j,i);
				}
			}
		}
		// 문이 어디에 있는지를 안말해주었기에 4방향 탐색
		for (int i = 0; i < 4; i++) {
			visited[start.y][start.x][i] = 0;
			q.add(new Light(start.y,start.x,0,i));
		}

		bfs();
		
		System.out.println(min);
		
	}
	
	//한칸 이동하고 방향 전환을 한 채로 bfs 시작된다는 가정
	private static void bfs() {
		while(!q.isEmpty()) {
			Light l = q.poll();
			int ny = l.y + dy[l.dir];
			int nx = l.x + dx[l.dir];
			
			int nDir = -1;
			if (check(ny, nx,l.dir, l.cnt)) continue;
			visited[ny][nx][l.dir] = l.cnt;
			
			if (house[ny][nx].equals("*")) {
				continue;
			} else if (house[ny][nx].equals("#")) {
				if (ny != start.y || nx!= start.x ) min = Math.min(l.cnt, min);
				// 제자리로 돌아온 경우 배제
				continue;
				
			} else if (house[ny][nx].equals("!")) {
				q.add(new Light(ny,nx,l.cnt, l.dir));
				
				nDir = turn(l.dir, "/");
				process(ny,nx,nDir,l.cnt);
				
				nDir = turn(l.dir, "\\");
				process(ny,nx,nDir,l.cnt);
				
				
			}  else if (house[ny][nx].equals(".")){
				q.add(new Light(ny,nx,l.cnt, l.dir));
			} 
		}
	}
	private static void process (int y, int x, int dir, int cnt) {
		if (!check(y,x,dir,cnt)) {
			visited[y][x][dir] = cnt;
			q.add(new Light(y,x,cnt+1,dir));
		}
	}
	
	private static int turn(int dir, String mirror) {
		// '/' 만나면  0번 <->1번 , 2<->3;
		// '\' 만나면  0번 <->3번;
		if (mirror.equals("/")){
			return (dir%2==0)?dir+1:dir-1;
		} else {
			return 3-dir;
		}
	}
	
	private static boolean check(int y, int x, int dir, int cnt) {
		if (y < 0 || x < 0 || y >= size || x>= size
				|| house[y][x].equals("*") || visited[y][x][dir] <= cnt) return true;

		return false;
	}
	
}
