import java.util.*;
import java.io.*;

class Main
{
	static int N;
	static long totalPrice;
	static int[] dist;
	static int[] price;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	dist = new int[N];
    	price = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N - 1; i++) {
    		dist[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		price[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 해결
    	greedy();
    	
    	// 정답
    	System.out.println(totalPrice);
    }
    
    static void greedy() {
    	long minPrice = price[0];
    	for(int i = 0; i < N - 1; i++) {
    		totalPrice += minPrice * dist[i];
    		minPrice = Math.min(minPrice, price[i + 1]);
    	}
    }
    
}
