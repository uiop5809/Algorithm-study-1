import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author mhg10
 *
 */
public class Main {

    public static int arr[];
    public static City cities[];
    public static int  N,M,K,X;
    public static  List<Integer>answers;
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N=arr[0];
        M=arr[1];
        K=arr[2];
        X=arr[3];
        cities=new City[N+1];
        for(int i=0;i<N+1;i++) {
        	cities[i]=new City();
        }
    
        for(int i=0;i<M;i++) {
        	arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	cities[arr[0]].add(Arrays.copyOfRange(arr, 1, arr.length));
        }
        bfs();
        if(answers.size()==0) {
        	System.out.println(-1);
        	System.exit(0);

        }
        	
        for (int i :answers) {
        	System.out.println(i);
        }
        
   
    }
    public static void bfs() {
    	int visited[]=new int[N+1];
    	Queue<int[]> que=new ArrayDeque();
    	que.add(new int[] {X,0});
		visited[X]=1;
        answers=new LinkedList();
    	while(!que.isEmpty()) {
    		int[] vc=que.remove();
    		int v=vc[0];
    		int cost=vc[1];
    		if(cost==K)
    			answers.add(v);
    		if(cities[v]==null)
    			continue;
    		for(int i: cities[v].adj) {
    			if(visited[i]==0) {
    				que.add(new int[] {i,cost+1});
    				visited[i]=1;
    			}
    		}
    		
    	}
    	answers.sort((a,b)->a-b);
    }
    static class City{
    	List<Integer> adj;
    	public City() {
    		adj=new LinkedList();
    	}
    
    	public void add(int []adjs) {
    		for(int i : adjs) {
    			this.adj.add(i);
    		}
    	}
    	
    }
    


 }



