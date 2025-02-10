import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



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
    private static int N;
    private static int M;
    static int[] parent;
    
    static Map<Bridge,Integer> bridges=new HashMap();
    

    static int maps[][];
 
    
   
    public static void main(String[] args) throws IOException {
         int[]arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	 N=arr[0];
    	 M=arr[1];
    	 maps=new int[N][M];
    	 for(int i=0;i<N;i++) {
             arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
             for(int j=0;j<arr.length;j++) {
            	 maps[i][j]=arr[j];
             }
    	 }
    	 int flag=2;
    	 for(int i=0;i<N;i++) {
    		 for(int j=0;j<M;j++) {
    			 if(maps[i][j]==1) {
    				 bfs(j,i,flag);
    			 flag+=1;
    			 }
    		 }
    	 }

    	 parent=new int[flag];
    	 for(int i=2;i<flag;i++) {
    		 parent[i]=i;
    	 }
//    	 for(int i=0;i<N;i++) {
//    		 for(int j=0;j<M;j++) {
//    			 System.out.print(maps[i][j]+" ");
//    				 
//    		 }
//			 System.out.println();
//
//    	 }
   
    	 for(int i=0;i<N;i++) {
    		 for(int j=0;j<M;j++) {
    			 if(maps[i][j]>=2) {
    				 insertRightBridge(maps[i][j],j,i);
    				 insertDownBridge(maps[i][j],j,i);
    			 }
    				 
    		 }
    	 }
   
    	 Map<Bridge, Integer> sortedMap = bridges.entrySet().stream()
    			 .sorted(Map.Entry.comparingByValue()) // Value 기준 정렬
    		        .collect(LinkedHashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), Map::putAll);
//    	 for(Bridge b :bridges.keySet()) {
//     		System.out.println(b.v1+" "+b.v2+" "+bridges.get(b));
//     	}
    	 System.out.println(isConnect(sortedMap));

       
    }
    public static void union(int a,int b) {
    	int a1=find(a);
    	int b1=find(b);
    	if(a1!=b1) {
    	if(parent[a1]<parent[b1])
    			parent[b1]=a1;
    	else
    			parent[a1]=b1;
    	}
    }
    public static int find(int v) {
    	if(parent[v]!=v) {
    	return	parent[v]=find(parent[v]);
    	}
    	return parent[v];
    	
    }
    public static int  isConnect(Map<Bridge, Integer>maps ) {
    	int min=0;
    	for(Bridge b :maps.keySet()) {
    		if(find(b.v1)!=find(b.v2)) {
    			union(b.v1,b.v2);
    			min+=maps.get(b);
    		}
    	 	int j=find(2);
        	for(int i=2;i<parent.length;i++) {
        	  	if(find(i)!=j) {
        	  		break;
        	  	}
        	  	if(i==parent.length-1)
        	  		return min;
        	}
    	}
    	return -1;
    }
    public static void insertRightBridge(int v1,int x, int y) {
    	int len=0;
    	for(int i=x+1;i<M;i++) {
    		if(maps[y][i]>=2&& maps[y][i]!=v1) {
    			if(len>=2) {
    			int priorLength=bridges.get(new Bridge(v1,maps[y][i]))==null?10000:bridges.get(new Bridge(v1,maps[y][i]));
    			bridges.put(new Bridge(v1,maps[y][i]), Math.min(priorLength, len));
    			}
    			break;
    		}
    		if(maps[y][i]==v1)
    			break;
    		if(maps[y][i]==0)
    			len++;
    	}
    	
    }
    public static void insertDownBridge(int v1,int x, int y) {
    	int len=0;
    	for(int i=y+1;i<N;i++) {
    		if(maps[i][x]>=2&&maps[i][x]!=v1) {
    			if(len>=2) {
    			int priorLength=bridges.get(new Bridge(v1,maps[i][x]))==null?10000:bridges.get(new Bridge(v1,maps[i][x]));
    			bridges.put(new Bridge(v1,maps[i][x]), Math.min(priorLength, len));
    			
    			}
    			
    			break;
    		}
    		if(maps[i][x]==v1)
    			break;
    		if(maps[i][x]==0)
    			len++;
    	}	
    }
    public static void bfs(int x,int y,int n) {
    	int dx[]= {1,-1,0,0};
    	int dy[]= {0,0,1,-1};
    	Queue<int[]> que=new ArrayDeque();
    	que.add(new int[] {x,y});
    	maps[y][x]=n;
    	while(!que.isEmpty()) {
    		int[] xy=que.remove();
    	    int xx=xy[0];
    	    int yy=xy[1];
    	    for(int i=0;i<4;i++) {
    	    	int nx=xx+dx[i];
    	    	int ny=yy+dy[i];
    	    	if(0<=nx && nx<M && 0<=ny && ny<N && maps[ny][nx]==1) {
    	    		maps[ny][nx]=n;
    	    		que.add(new int[] {nx,ny});
    	    	}
    	    }
    	}
    }
    static class Bridge{
    	int v1;
    	int v2;
    	public Bridge(int v1,int v2) {
    		this.v1=v1;
    		this.v2=v2;
    	}
    	@Override
    	public int hashCode() {
    		return v1*1000000+v2;
    	}
    	 @Override
    	    public boolean equals(Object obj) {
    	        if (this == obj) return true;
    	        if (obj == null || getClass() != obj.getClass()) return false;
    	        Bridge b = (Bridge) obj;
    	        return v1 == b.v1 && Objects.equals(v2, b.v2);
    	    }
    	
    }


  
} 