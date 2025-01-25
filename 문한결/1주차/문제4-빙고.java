import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;



/**
 * 
 * @author SSAFY
 * 
 * 1. 각 섬을 숫자로 구분 1,2,3,4....->bfs
 * 2. 가로 세로로 돌면서 숫자들과 거리를 저장함
 * 3. 위에서 저장한  2개의 섬과 거리의 중복을 제거한다.
 * 4. 3의 결과로 나온 것을 union-find를 이용해서 MST를 돌리면 됨
 * 
 * 
 * 
 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static StringTokenizer st;
      static Map<Integer,int[]> map=new HashMap();
    
    

    static int maps[][];
 
    
   
    public static void main(String[] args) throws IOException {
        maps=new int[5][5];
        for(int i=0;i<5;i++) {
        	int[]newArr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer:: parseInt).toArray();
        	for(int j=0;j<5;j++) {
        		maps[i][j]=newArr[j];
        		map.put(maps[i][j], new int[] {j,i});//x,y
        	}
        }
        int answer=0;
        int flag=0;
        for(int i=0;i<5;i++) {
        	int[]newArr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer:: parseInt).toArray();
        	for(int j=0;j<5;j++) {
        		answer+=1;
        		int []xy=map.get(newArr[j]);
        		maps[xy[1]][xy[0]]=1;
        		
        	
        		if(isBingo()>=3) {
        			System.out.println(answer);
        		    System.exit(0);
        		}
        	
        	
        	}
        }
	    System.out.println("j");

    	
       
    }
    public static int  isBingo() {
    	int flags=0;
    	int cnt=0;
    	for(int i=0;i<5;i++) {
    		cnt=0;
    		for(int j=0;j<5;j++) {
    			if(maps[i][j]==1)
    				cnt+=1;
    		}
    		if(cnt==5) {
    			flags+=1;
    
    		}
    	}
       	for(int i=0;i<5;i++) {
    		cnt=0;
    		for(int j=0;j<5;j++) {
    			if(maps[j][i]==1)
    				cnt+=1;
    		}
    		if(cnt==5) {
    			flags+=1;

    		}
    	}
		cnt=0;
       	for(int i=0;i<5;i++) {
       		if(maps[i][i]==1)
				cnt+=1;
       		
       	}
       	if(cnt==5) {
			flags+=1;

		}
       	cnt=0;
       	for(int i=0;i<5;i++) {
       		if(maps[i][4-i]==1)
				cnt+=1;
       		
       	}
     	if(cnt==5) {
			flags+=1;

		}
     	
       	return flags;
    }
 

  
} 