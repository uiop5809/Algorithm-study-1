import java.util.*;
import java.io.*;

class Main
{
	static int A, B, C;
	static long answer;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    
    	// 입력
    	A = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	answer = go(A, B);
    	System.out.println(answer);
    }
    
    static long go(long A, long B) {
    	// 기저 조건
    	if (B == 1) return A % C;
    	
    	long res = go(A, B / 2);
    	res = (res * res) % C;
    	
    	// 만약 홀수라면
    	if (B % 2 == 1) res = (res * A) % C;
    	return res;
    }
    
}
