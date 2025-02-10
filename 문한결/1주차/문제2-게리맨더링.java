import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



/**
 * 
 * @author SSAFY
 * bfs-> 를 돌면서 visited를 체크하는데,
 * que에서 나올 때매다. unvisted한 배열들이 모두 인접해 있는지를 확인해야함.
 * 
 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static StringTokenizer st;
    
   	public static int min=1000000000;
	public static int popular[];
	public static int [][] lists;
	public static int[] visited;
	public static int[] visitedInBFS;



    public static void main(String[] args) throws IOException {
       int n=Integer.valueOf(br.readLine());
       popular=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
       lists=new int[n+1][];
       visited=new int[n+1];
       for(int i=0;i<n;i++) {
    	   int newArr[]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	   int []temp=Arrays.copyOfRange(newArr,1,newArr.length);
    	   lists[i+1]=new int[temp.length];
    	   for(int j=0;j<temp.length;j++)
    		   lists[i+1][j]=temp[j];
       }
       dfs(1,n);
   	   
       System.out.println(min==1000000000?-1 : min );
       
    }
    public static void dfs(int current,int n) {
    	if(n+1==current) {
    		visitedInBFS=new int[n+1];
    		int check=0;
    		int checks[]=new int[2];
    		for(int i=1;i<=n;i++) {
    			if(visitedInBFS[i]==0) {
    				
    				if(check<2)
    					checks[check]=bfs(i,visited[i]);
    		    check+=1;
    			}
    		}
    		if(check==2) {
    			min=Math.min(min, Math.abs(checks[0]-checks[1]));
    		}
    		return ;
    		
    	}
    	
    	visited[current]=1;
    	dfs(current+1,n);
    	
       	visited[current]=2;
    	dfs(current+1,n);
    	
    	
    }
    public static int bfs(int v,int ar) {
    	Queue <Integer>que=new ArrayDeque();
    	int sum=0;
    	que.add(v);
    	visitedInBFS[v]=1;

    	while(!que.isEmpty()) {
    		int q=que.remove();
        	visitedInBFS[q]=1;
        	sum+=popular[q-1];
    		for (int i: lists[q]) {
    			if(visitedInBFS[i]==0 && visited[i]==ar) {
    				que.add(i);
    		    	visitedInBFS[i]=1;

    			}
    		}
    		
    	}
    	return sum;
    }
    
} 