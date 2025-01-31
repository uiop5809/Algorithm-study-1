import java.util.*;
import java.io.*;

/*
 * 1. 비어있는 칸 중 좋아하는 학생이 인접한 칸에 많은 칸으로
 * 2. 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로
 * 3. 행의 번호 작고, 열의 번호 작은 칸
 * */

class Main
{
	static int N;
	static int[] student; // 학생 순서
	static int[][] arr;
	static HashMap<Integer, List<Integer>> preference; // 학생별 선호도

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static class Seat implements Comparable<Seat> {
		private int x, y, studentCnt, emptyCnt;

		public Seat(int x, int y, int studentCnt, int emptyCnt) {
			this.x = x;
			this.y = y;
			this.studentCnt = studentCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Seat seat) {
			// 인접 좋아하는 학생수로 비교
			if (studentCnt != seat.studentCnt) return seat.studentCnt - studentCnt;
			else if (emptyCnt != seat.emptyCnt) return seat.emptyCnt - emptyCnt;
			else if (x != seat.x) return x - seat.x;
			else return y - seat.y;
		}
	}

    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	student = new int[N * N];
    	preference = new HashMap<>();
    	for(int i = 0; i < N * N; i++) {
    		// 순서 기록해야됨
    		st = new StringTokenizer(br.readLine());
    		int idx = Integer.parseInt(st.nextToken());
    		student[i] = idx;
    		preference.put(idx, new ArrayList<>());

    		// 좋아하는 사람 기록
    		for(int j = 0; j < 4; j++) {
    			preference.get(idx).add(Integer.parseInt(st.nextToken()));
    		}
    	}

    	// 해결
    	solution();

    	// 결과
    	// 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000
    	int res = 0;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			int cnt = getStudentCnt(i, j, arr[i][j]);
    			if (cnt != 0) res += Math.pow(10, cnt - 1);
    		}
    	}
    	System.out.println(res);

    }

    static void solution() {
    	// 학생 순서대로 자리 배치하기
    	arr = new int[N][N];
    	for(int k = 0; k < N * N; k++) {
    		int idx = student[k];

    		PriorityQueue<Seat> pq = new PriorityQueue<>();
			for(int i = 0; i < N; i++) {
	    		for(int j = 0; j < N; j++) {
	    			if (arr[i][j] == 0) {
	    				int studentCnt = getStudentCnt(i, j, idx);
	    				int emptyCnt = getEmptyCnt(i, j);
	    				pq.add(new Seat(i, j, studentCnt, emptyCnt));
	    			}
	    		}
	    	}

			Seat seat = pq.poll();
			arr[seat.x][seat.y] = idx;
    	}
    }

    // 주위 선호하는 학생들
    static int getStudentCnt(int x, int y, int idx) {
    	int cnt = 0;
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];

    		if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
			if (preference.get(idx).contains(arr[nx][ny])) cnt++;
    	}
    	return cnt;
    }

    // 주위 빈 곳 
    static int getEmptyCnt(int x, int y) {
    	int cnt = 0;
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];

    		if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) continue;
    		if (arr[nx][ny] == 0) cnt++;
    	}
    	return cnt;
    }

}
