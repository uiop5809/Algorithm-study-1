import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author mhg10
 * 전형적인 배낭 문제
 * dp[N+1][K+1]
 * 과목을 class로 만들어두고 사용하자.
 * 
 */
public class Main {
    public static int dp[][];
    public static int N;
    public static  int K;
    public static int input[];
    public static Subject[] subjects;
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	N=input[0];
    	K=input[1];
    	subjects=new Subject[K+1];
    	dp=new int[K+1][];
    	for(int i=0;i<K+1;i++) {
    		dp[i]=new int[N+1];
    	}
    	for(int i=0;i<K;i++) {
        	input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	subjects[i+1]=new Subject(input[0],input[1]);
    	}
    	findMaxImportance();
    	System.out.println(dp[K][N]);
    	
    	
    }
    public static void findMaxImportance() {
    	for(int i=1;i<=K;i++) {
    		for(int curTime=1;curTime<=N;curTime++) {
    			if(subjects[i].T<=curTime) {
    				dp[i][curTime]=Math.max(dp[i-1][curTime-subjects[i].T]+subjects[i].I, 
    						dp[i-1][curTime]);
    				continue;
    			}
    			dp[i][curTime]=dp[i-1][curTime];
    		}
    	}
    }
    
    static class Subject{
    	int I;
    	int T;
    	public Subject(int i,int t) {
    		this.I=i;
    		this.T=t;
    	}
    }
 }



