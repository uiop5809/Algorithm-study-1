import java.io.*;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static String[] in;
	
	private static long A, B, C, ans;
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");

		A = Integer.parseInt(in[0]);
		B = Integer.parseInt(in[1]);
		C = Integer.parseInt(in[2]);
		
		ans = power(B);
		
		sb.append(ans).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

    // 지수 B를 넣어서 분할정복
	public static long power(long num){
	    if(num == 1) return A % C;
	    long k = power(num / 2) % C;

        if(num % 2 == 0) return (k * k) % C;
        else return ((k * k) % C * A) % C;
	}
}