import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;



/**
 * 
 * @author SSAFY
 * N개의 회의실 어쩌고..
 * 회의의 최대 갯수 구해라 
 * 어떻게 해야최대가 될라나?
 * 
 * 4
 *   1 5
 *   5 7
 *   4 7 
 *   7 7
 *   이러한 케이스 때문에 arr[0]을 오름차순 정렬해야함
 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; 
    private static int[][] arr;
    private static int answer=1;

    
    public static void main(String[] args) throws IOException {
    	N=Integer.valueOf(br.readLine());
    	arr=new int[N][];
    	for(int i=0;i<N;i++) {
    		arr[i]=new int[2];
    	}
        for(int i=0;i<N;i++) {
        	int [] in=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	arr[i][0]=in[0];
        	arr[i][1]=in[1];
        }
        Arrays.sort(arr, (arr1,arr2)->{
        	if(arr1[1]-arr2[1]==0)
        		return arr1[0]-arr2[0];
        	return arr1[1]-arr2[1];
        		
        });

        int first=arr[0][0];
        int second=arr[0][1];
        for(int i=1;i<N;i++) {
 
        	if(second<=arr[i][0]) {

        		first=arr[i][0];
        		second=arr[i][1];
        		answer+=1;
        	}
        		
        }
        System.out.println(answer);
        
    }
  
} 