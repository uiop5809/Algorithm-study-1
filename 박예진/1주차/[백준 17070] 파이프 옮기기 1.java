import java.util.*;
import java.io.*;

class Main
{
    static int N, res;
    static int[][] arr;
	
    public static void main(String args[]) throws IOException 
    {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	
	// 입력
	N = Integer.parseInt(br.readLine());
	arr = new int[N][N];
	for(int i = 0; i < N; i++) {
		st = new StringTokenizer(br.readLine());
		for(int j = 0; j < N; j++) {
			arr[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	// 해결
	dfs(0, 1, 0); // flag 0 가로, 1세로, 2대각선
	
	// 출력
	System.out.println(res);
    }
    
    // 끝점을 기준으로
    static void dfs(int x, int y, int flag) {
	if (x == N - 1 && y == N - 1) {
	    res++;
	    return;
	}
	
	// 가로
	if (y + 1 < N && arr[x][y + 1] == 0 && (flag == 0 || flag == 2)) dfs(x, y + 1, 0);
	// 세로
	if (x + 1 < N && arr[x + 1][y] == 0 && (flag == 1 || flag == 2)) dfs(x + 1, y, 1); 
	// 대각선
	if (x + 1 < N && y + 1 < N && arr[x + 1][y] == 0 && arr[x][y + 1] == 0 && arr[x + 1][y + 1] == 0) dfs(x + 1, y + 1, 2); 
    }

}
