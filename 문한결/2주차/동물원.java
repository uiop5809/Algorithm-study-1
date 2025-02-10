import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author mhg10
 * 동물원
 * 가로세로가 붙어있으면 안됨
 * * 
 *  * 처럼 대각선 혹은
 * * 
 *    처럼 아예 없는 경우도 존재함.
 * 즉 왼쪽, 오른쪽, 아예 없는 경우에 따라서 아래 칸의 사자의 분표 여부가 달라짐.
 * [세로][왼,오,아예 없는]으로 dp를 잡고
 *  [i][j] j==0-> [i+1][1]= [i][j]+1, [i+1][2]=  [i][j]+1-> j==0-> 왼쪽이므로
 *  다음 칸의 오른쪽에 사자를 두거나 아예 사자를 안 둘 수 있음.
 *  [i][j] j==1-> [i+1][0]= [i][j]+1, [i+1][2]=  [i][j]+1-> j==1-> 오른쪽이므로
 *  다음 칸의 왼쪽에 사자를 두거나 아예 사자를 안 둘 수 있음.
 *  [i][j] j==2-> [i+1][0]= [i][j]+1, [i+1][1]=  [i][j]+1-> j==2-> 사자가 없으므로
 *  다음 칸의 오른쪽에 사자를 두거나 왼쪽에  둘 수 있음.
 *
 */
public class Main {

    public static long dp[][];
    public static int N;
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	N=Integer.valueOf(br.readLine());
        dp=new long [N][];
        for(int i=0;i<N;i++) {
        	dp[i]=new long[3];
        }
        for(int i=0;i<3;i++) {
        	dp[0][i]=1;
        }
        assignLion();
        int answer=0;
        for(int i=0;i<3;i++) {
        	answer+=dp[N-1][i];
        }
        System.out.println(answer%9901);
     }
	private static void assignLion() {
		for(int i=0;i<N-1;i++) {
        	for(int j=0;j<3;j++) {
        		switch(j) {
        		case 0:
        			dp[i+1][1]+=dp[i][j]%9901;
        			dp[i+1][2]+=dp[i][j]%9901;
        			break;
        		case 1: 
        			dp[i+1][0]+=dp[i][j]%9901;
        			dp[i+1][2]+=dp[i][j]%9901;
        			break;
        		case 2: 
        			dp[i+1][0]+=dp[i][j]%9901;
        			dp[i+1][1]+=dp[i][j]%9901;
        			dp[i+1][2]+=dp[i][j]%9901;
        			break;  			
        		}
        	}
        }
	}

 }




