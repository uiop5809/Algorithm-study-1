import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class  스파이 {
	static int workDay,minPoint,ans = 0;
	static int[][] works;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		workDay = Integer.parseInt(st.nextToken());
		minPoint = Integer.parseInt(st.nextToken());
		works = new int[2][3];
		
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				works[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,-1,0);
		System.out.println(ans);
	}
	
	private static void dfs(int day, int pre,int point) {
		if (day == workDay) {
			if (point >= minPoint) ans++;
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == pre) {
					dfs(day+1,j,point+works[i][j]/2);
				}else {
					dfs(day+1,j,point+works[i][j]);
				}
			}
		}
		
		
	}
}
