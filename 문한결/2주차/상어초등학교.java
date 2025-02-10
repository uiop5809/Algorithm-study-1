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
 * 구현 문제의 느낌이 난다.
 * 1. 자리를 정하는 프로세스-> assignProecss
 *    - 비어 있는 칸 중 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 이동->assignP1
 *      - 비어있는 칸을 순회하면서 인접한 칸을 본다.     ->searchAdj()
 *      - 인접한 칸이 가장 많은 칸들의 좌표를 저장해서 이를 반환하자.	
 *    - 1이 많다면,현재의 칸에서 인접한 칸을 구하고, 칸의 갯수가 큰 것으로 배정-> assignP2
 *    - 위에도 많다면, 행이 가장 작은 순으로, 행이 같다면 열이 같은 곳으로 자리를 배정한다. 
 * 2. 답을 구하는 과정
 * 
 */
public class Main {
    public static int seats[][];
    public static int N;
    public static int input[];
    public static Map<Integer,int[]> students;
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	N=Integer.valueOf(br.readLine());
    	seats=new int[N][];
    	//참고: LinkedHashMap을 사용해야 순서가 보장된다.
    	students=new LinkedHashMap();
    	for(int i=0;i<N;i++) {
    		seats[i]=new int[N];
    	}
    	for(int i=0;i<N*N;i++) {
    		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();    	
    	    students.put(input[0], Arrays.copyOfRange(input, 1, input.length));
    	}
      for(int curStudent:students.keySet()) {
    	  int xy[]=assignProcess(curStudent);		  
    	  seats[xy[1]][xy[0]]=curStudent;
      }
      int answer=0;
      for(int i=0;i<N;i++) {
    	  for(int j=0;j<N;j++) {
    		  answer+=findAnswer(j,i,seats[i][j]);
    	  }
      }
      System.out.println(answer);
    	
    	    		
    	
    }
    private static int[] assignProcess(int curStudent) {
    	List <int []>p1Result=assignP1(curStudent);
    	if(p1Result.size()==1) {
    		return new int[] {p1Result.get(0)[0],p1Result.get(0)[1]};
    	}
    	List <int []>p2Result=assignP2(p1Result,curStudent);
    	if(p2Result.size()==1) {
    		return new int[] {p2Result.get(0)[0],p2Result.get(0)[1]};
    	}
    	assignP3(p2Result);
		return new int[] {p2Result.get(0)[0],p2Result.get(0)[1]};
    	
  	
    }
    private static List<int[]> assignP1(int curStudent) {
    	List<int []> p1Result=new ArrayList();
    	int max=-1;
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<N;j++) {
    			if(seats[i][j]!=0)
    				continue;
    			int favNum=searchAdj(j,i,curStudent,1);
    			if(max<favNum) {
    				p1Result=new ArrayList();
    				p1Result.add(new int[] {j,i});
    				max=favNum;
    			}else if(max==favNum){
    				p1Result.add(new int[] {j,i});
    			}
    		}
    	}
    	return p1Result;
    	
    }
   private static List<int[]> assignP2(List<int []>point,int curStudent) {
	   List<int []> p2Result=new ArrayList();
   	    int max=-1; 
        for (int xy[]: point) {
        	int emptyN=searchAdj(xy[0],xy[1],curStudent,2);
    		if(seats[xy[1]][xy[0]]!=0)
				continue;
			if(max<emptyN) {
				p2Result=new ArrayList();
				p2Result.add(new int[] {xy[0],xy[1]});
				max=emptyN;
			}else if(max==emptyN){
				p2Result.add(new int[] {xy[0],xy[1]});
			}
        	
        }
        return p2Result;
    	
    }
    //인접한 곳을 찾는 프로세스가 1,2단계에서 모두 쓰이므로, 
   // process라는 변수를 통해서 작업을 구분.
    private static int searchAdj(int curX,int curY,int value,int process) {
    	int dx[]=new int[]{1,-1,0,0};
    	int dy[]=new int[]{0,0,1,-1};
    	int favN=0;
    	if(process==1) {
    	for(int i=0;i<4;i++) {
    		int nx=curX+dx[i];
    		int ny=curY+dy[i];
    		if(nx>=0 && nx<N && ny>=0 && ny<N) {
    			int favorites[]=students.get(value);
    			for(int f:favorites ) {
    				if(f==seats[ny][nx])
    					favN++;
    			}
    		}
    	}
    	return favN;
    	}
    	int emptyN=0;
    	for(int i=0;i<4;i++) {
    		int nx=curX+dx[i];
    		int ny=curY+dy[i];
    		if(nx>=0 && nx<N && ny>=0 && ny<N) {
    			if(seats[ny][nx]==0)
    				emptyN+=1;
    		}
    	}
    	return emptyN;  	
    }
	private static void assignP3(List<int[]> p2Result) {
		p2Result.sort((arr1,arr2)->{
    		if(arr1[1]==arr2[1]){
    			return arr1[0]-arr2[0];
    		}
    		return arr1[1]-arr2[1];
    	});
	}
    private static int findAnswer(int curX,int curY, int curStudent) {
    	int dx[]=new int[]{1,-1,0,0};
    	int dy[]=new int[]{0,0,1,-1};
    	int favN=0;
    	for(int i=0;i<4;i++) {
    		int nx=curX+dx[i];
    		int ny=curY+dy[i];
    		if(nx>=0 && nx<N && ny>=0 && ny<N) {
    			int favorites[]=students.get(curStudent);
    			for(int f:favorites ){
    				if(f==seats[ny][nx])
    					favN++;
    			}
    		}
    	}
    	switch(favN) {
    	case 0:
    		return 0;
    	case 1:
    		return 1;
    	case 2: 
    		return 10;
    	case 3: 
    		return 100;
    	case 4:
    		return 1000;
    	}
    	return 0;
    }
 }



