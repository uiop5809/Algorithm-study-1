import java.util.*;
import java.io.*;

/*
 * 거리가 가장 가까운 물고기
 * 가장 위에 있는 물고기
 * 가장 왼쪽에 있는 물고기
 * */

class Main
{
	static int N, time;
	static int[][] arr;	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static Shark shark; // 현재 상어 위치, 사이즈
	
	static class Node {
		private int x, y, dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static class Shark {
		private int x, y, size, cnt;
		
		public Shark(int x, int y, int size, int cnt) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt; // 현재 얼마나 먹었는지
		}
	}
	
	static class Fish implements Comparable<Fish>{
		private int x, y; // 위치
		private int size; // 크기
		private int dist; // 물고기와 상어 거리
		
		public Fish(int x, int y, int size, int dist) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Fish fish) {
			if (this.dist != fish.dist) return this.dist - fish.dist; // 거리 오름차순
			else if (this.x != fish.x) return this.x - fish.x; // 위쪽
			else return this.y - fish.y; // 왼쪽
		}
		
	}
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	arr = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    			// 상어 위치 표시
    			if (arr[i][j] == 9) {
    				shark = new Shark(i, j, 2, 0); 
    			}
    		}
    	}
    	
    	// 해결
    	solution();
    	
    	// 결과
    	System.out.println(time);
    }
    
    static void solution() {
    	while(true) {
        	PriorityQueue <Fish> pq = new PriorityQueue<>();
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			// 상어와 물고기 거리 책정
        			if (arr[i][j] != 0 && arr[i][j] != 9) {
        				int dist = bfs(shark.x, shark.y, i, j);
        				if (dist != -1) pq.add(new Fish(i, j, arr[i][j], dist));
        			}
        		}
        	}
        	
        	// 물고기 없음
        	if (pq.isEmpty()) break;
        	
        	// 거리가 가까운 순이지만, 현재 상어보다 더 작을 때까지 pq remove
        	// 잡아 먹을 수 있는 물고기가 나올 때까지
        	while(!pq.isEmpty()) {
        		Fish fish = pq.peek();
        		if (fish.size < shark.size) {
        			break;
        		} else pq.remove();
        	}
        	
        	// 잡아먹을 수 있는 물고기 없음
        	if (pq.isEmpty()) break;
        	
        	// 잡아먹을 수 있는 물고기
        	Fish fish = pq.peek();
        	
    		// 물고기 잡아먹힘
    		arr[shark.x][shark.y] = 0;
    		arr[fish.x][fish.y] = 9;
    		// 현재 상어 위치 옮겨짐
    		shark.x = fish.x;
    		shark.y = fish.y;
    		shark.cnt++;
    		// 현재 상어 크기 커져야함
    		if (shark.size == shark.cnt) {
    			shark.size++;
    			shark.cnt = 0;
    		}
    		// 시간 dist 만큼 업데이트
    		time += fish.dist;
        	
    	}
    }
    
    // 상어와 물고기 거리 측정
    static int bfs(int sx, int sy, int ex, int ey) {
    	Queue <Node> q = new LinkedList<>();
    	boolean[][] visited = new boolean[N][N];
    	
    	q.add(new Node(sx, sy, 0));
    	visited[sx][sy] = true;
    	
    	while(!q.isEmpty()) {
    		Node cur = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			
    			if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
    			
    			if (nx == ex && ny == ey) {
    				return cur.dist + 1;
    			}
    			// 현재 상어 크기보다 작거나 같으면 지나갈 수 있음
    			if (arr[nx][ny] <= shark.size && !visited[nx][ny]) {
    				q.add(new Node(nx, ny, cur.dist + 1));
    				visited[nx][ny] = true;
    			}
    		}
    	}
    	return -1;
    }

}
