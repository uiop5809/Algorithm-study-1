import java.util.*;
import java.io.*;

class Main
{
	static int T, num;
	static final int N = 10000;
	static int[] prime, res;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	T = Integer.parseInt(st.nextToken());
    	while (T-- > 0) {
    		// 입력
    		st = new StringTokenizer(br.readLine());
    		num = Integer.parseInt(st.nextToken());
    		
    		// 해결
        	prime = new int[10001];
    		primeNum();
    		solution();
    		
    		// 출력
    		System.out.println(res[0] + " " + res[1]);
    	}
    }
    
    static void solution() {
    	res = new int[2];
		int difference = Integer.MAX_VALUE;

		// 투포인터
		int left = 2;
		int right = num;
		while(left <= right) {
			// 소수가 아니면 건너뜀
			if (prime[left] == 0) {
				left++;
				continue;
			}
			if (prime[right] == 0) {
				right--;
				continue;
			}
			
			int sum = left + right;
			if (sum == num) {
				if (difference > right - left) {
					difference = right - left;
					res[0] = left;
					res[1] = right;
				}
				left++;
				right--;
			} else if (sum < num){
				left++;
			} else if (sum > num) {
				right--;
			}
		}
    
    // 에라토스테네스의 체
    static void primeNum() {
    	for (int i = 2; i <= N; i++) {
    		prime[i] = i;
    	}
    	for(int i = 2; i <= Math.sqrt(N); i++) {
    		if (prime[i] == 0) continue;
    		for(int j = i * i; j <= N; j += i) {
    			prime[j] = 0;
    		}
    	}
    }
}
