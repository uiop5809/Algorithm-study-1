import java.util.*;
import java.io.*;

/* 빙고가 되기 위한 횟수 최소 12 */

class Main
{
	static int[][] arr; // 빙고판
	static boolean[][] visited; // 사회자 
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	// 입력
    	arr = new int[5][5];
    	for(int i = 0; i < 5; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 5; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 해결
    	int idx = 0;
    	visited = new boolean[5][5];
    	outer : for(int i = 0; i < 5; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 5; j++) {
    			int num = Integer.parseInt(st.nextToken());
    			solution(num); // 방문 체크
    			idx++;
    			
    			if (idx >= 12 && bingoTest()) {
    				break outer;
    			} 
    		}
    	}
    	
    	// 출력
    	System.out.println(idx);
    }
    
    static void solution(int num) {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			if (arr[i][j] == num) {
    				visited[i][j] = true;
    				return;
    			}
    		}
    	}
    }
    
    static boolean bingoTest() {
    	int bingo = 0;
    	    	
       	int cnt3 = 0, cnt4 = 0;
    	for(int i = 0; i < 5; i++) {
    		// 행열 검사
        	int cnt1 = 0, cnt2 = 0;
    		for(int j = 0; j < 5; j++) {
    			if (visited[i][j]) cnt1++;
    			if (visited[j][i]) cnt2++;
    		}
    		if (cnt1 == 5) bingo++;
    		if (cnt2 == 5) bingo++;
    		
    		// 대각선 검사
    		if (visited[i][i]) cnt3++;
    		if (visited[i][4 - i]) cnt4++;
    	}
		if (cnt3 == 5) bingo++;
		if (cnt4 == 5) bingo++;
		
		return bingo >= 3; 
    }

}
