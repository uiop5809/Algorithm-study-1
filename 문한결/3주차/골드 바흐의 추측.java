import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;



/**
 * 
 * @author SSAFY

 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T; 
    private static int in; 
    private static int[] answer;
    
   
    public static void main(String[] args) throws IOException {
    	T=Integer.valueOf(br.readLine());
    	for(int i=0;i<T;i++) {
        	in=Integer.valueOf(br.readLine());
        	answer=findAnswer(in);
        	System.out.println(answer[0]+" "+answer[1]);

    	}
     
 
    }
    public static int[] findAnswer(int n) {
    	int pivot=n/2;
    	for(int i=pivot;i>=1;i--) {
    		if(isPrime(i)&& isPrime(n-i))
    			return new int[] {i,n-i};
    	}
    	return new int[] {0,0};
    }
    public static boolean isPrime(int n) {
    	for(int i=2;i<=Math.sqrt(n);i++) {
    		if(n%i==0)
    			return false;
    	}
    	return true;
    }
  
} 