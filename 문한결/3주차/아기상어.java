import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



/**
 * 
 * @author SSAFY
 * 
 * 아기 상어 초기 크기는 2 상하좌우로 인접한 한칸을 이동할 수 있음.
 * 자기보다 작은 놈만 먹을 수 있음. 
 * 자신의 크기와 같은 수의  물고기를 먹으면 크기가 커짐 
 * 메서드 1-> 더이상 먹을 물고기가 공간에 없다면 끝.
 * 메서드 2-> 먹을 수 있는 물고기가 1마리라면, 이걸 먹으러감
 * 메서드 3-> 1마리 보다 많다면 거리가 가장 가까운 물고기를 먹으러감-> 거리는 최소 거리기준임.
 * 메서드 4-> 거리가 가까운 물고기가 많다면 가장 위에 있는 물고기 이것도 같다면 가장 왼쪽.
 * 메서드 5-> 물고기를 먹을 때마다 경험지가 오른다.경험치가 크기와 같다면 레벨업.
 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; 
    private static int[][] arr;
    private static int answer=0;
    private static  Shark shark;
    private static List<int[]> eatenList;

    
   
    public static void main(String[] args) throws IOException {
    	N=Integer.valueOf(br.readLine());
    	arr=new int[N][];
    	for(int i=0;i<N;i++) {
    		arr[i]=new int[N];
    	}
    	
    	for(int i=0;i<N;i++) {
    		int []in= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    		for(int j=0;j<in.length;j++) {
    			arr[i][j]=in[j];
    	        if(arr[i][j]==9) {
    	        	shark=new Shark(j,i,2,0);
    	        	arr[i][j]=0;
    	        }
    		}
    	}
    	while(true) {
    		int enableNum=returnEnabledFish();

    		if(enableNum==0)break;
    		if(enableNum==1) {eatFishInOne();
    		}else {
    			eatFishInMany();
    		}
    		
    		
    	}
     System.out.println(answer);
        
    }
    static class Shark{
    	int x,y;
    	int value;
    	int eaten;
    	public Shark(int x,int y, int value,int eaten) {
    		this.x=x;
    		this.y=y;
    		this.value=value;
    		this.eaten=eaten;
    	}
    	public void update(int x,int y) {
    		this.x=x;
    		this.y=y;
    		eaten+=1;
    		if(eaten==value) {
    			value+=1;
    		    eaten=0;
    		}
    
    	}
    }
    public static int  returnEnabledFish() {
    	int fish=0;
    	int dx[]= {1,-1,0,0};
    	int dy[]= {0,0,1,-1};

        Queue<int[]> que=new ArrayDeque();
        int [][]visited=new int[N][];
       	for(int i=0;i<N;i++) {
       		visited[i]=new int[N];
    	}
       	int minTime=1000000;
        que.add(new int[] {shark.x,shark.y,0});
        while(!que.isEmpty()) {
        	int xy[]=que.remove();
        	int xx=xy[0];
        	int yy=xy[1];
        	int time=xy[2];
        	
    		if(time==minTime)
    			break;

        	for(int i=0;i<4;i++) {
        		int nx=xx+dx[i];
        		int ny=yy+dy[i];
        			
        		if(nx>=0 && nx<N && ny>=0 && ny<N && visited[ny][nx]==0 && arr[ny][nx]<=shark.value) {
        			if(arr[ny][nx]!=0 &&arr[ny][nx]<shark.value) {
        				fish+=1;
            			visited[ny][nx]=1;
            			minTime=Math.min(minTime, time+1);
        			}else {
        			que.add(new int[] {nx,ny,time+1});
        			visited[ny][nx]=1;
        			}
        		}
        		
        	}
        }
        return fish;
    }
    public static void  eatFishInOne() {
    	int dx[]= {1,-1,0,0};
    	int dy[]= {0,0,1,-1};

        Queue<int[]> que=new ArrayDeque();
        int [][]visited=new int[N][];
       	for(int i=0;i<N;i++) {
       		visited[i]=new int[N];
    	}
       	
        que.add(new int[] {shark.x,shark.y,0});
        while(!que.isEmpty()) {
        	int xy[]=que.remove();
        	int xx=xy[0];
        	int yy=xy[1];
        	int time=xy[2];
        	for(int i=0;i<4;i++) {
        		int nx=xx+dx[i];
        		int ny=yy+dy[i];
        		if(nx>=0 && nx<N && ny>=0 && ny<N && visited[ny][nx]==0 && arr[ny][nx]<=shark.value) {
        			if(arr[ny][nx]!=0 &&arr[ny][nx]<shark.value) {
        				shark.update(nx, ny);
        				arr[ny][nx]=0;
        				time+=1;
        				answer+=time;
        			    return;
        			}
        			que.add(new int[] {nx,ny,time+1});
        			visited[ny][nx]=1;
        		}
        		
        	}
        }
    }
    public static void  eatFishInMany() {
    	eatenList=new <int[]>LinkedList();
    	int dx[]= {1,-1,0,0};
    	int dy[]= {0,0,1,-1};

        Queue<int[]> que=new ArrayDeque();
        int [][]visited=new int[N][];
       	for(int i=0;i<N;i++) {
       		visited[i]=new int[N];
    	}
       	int minTime=1000000;
        que.add(new int[] {shark.x,shark.y,0});
        while(!que.isEmpty()) {
        	int xy[]=que.remove();
        	int xx=xy[0];
        	int yy=xy[1];
        	int time=xy[2];
        	
    		if(time==minTime)
    			break;

        	for(int i=0;i<4;i++) {
        		int nx=xx+dx[i];
        		int ny=yy+dy[i];
        			
        		if(nx>=0 && nx<N && ny>=0 && ny<N && visited[ny][nx]==0 && arr[ny][nx]<=shark.value) {
        			if(arr[ny][nx]!=0 &&arr[ny][nx]<shark.value) {
        				eatenList.add(new int[] {nx,ny});
            			visited[ny][nx]=1;
            			minTime=Math.min(minTime, time+1);
        			}else {
        			que.add(new int[] {nx,ny,time+1});
        			visited[ny][nx]=1;
        			}
        		}
        		
        	}
        }
        eatenList.sort((arr1,arr2)->{
        	if(arr2[1]==arr1[1])
        		return arr1[0]-arr2[0];
        	return arr1[1]-arr2[1];
        });
		shark.update(eatenList.get(0)[0], eatenList.get(0)[1]);

        answer+=minTime;
        arr[shark.y][shark.x]=0;

    }


    
    

  
} 