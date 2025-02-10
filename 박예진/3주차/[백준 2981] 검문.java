import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] num;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		num = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		// 해결
		Arrays.sort(num);
		int GCD = num[1] - num[0];
    // 양옆의 차이로 최대공약수
		for(int i = 1; i < N - 1; i++) {			
			GCD = gcd(GCD, num[i + 1] - num[i]);
		}
		
		// 출력
    // 최대 공약수의 약수
		for(int i = 2; i <= GCD; i++) {
			if (GCD % i == 0) {
				System.out.print(i + " ");
			}
		}
	}
	
	// 유클리드 호제법
	static int gcd(int a, int b) {
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
