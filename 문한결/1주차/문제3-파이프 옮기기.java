import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


//아니 이문제 이상함
// Que= ArrayDeque로 하면 통과되는데
// Que=LinkedList면 통과가 안됨..뭐누

public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
       int n=Integer.valueOf(br.readLine());
       int [][]arr=new int[n][n];
       for(int i=0;i<n;i++) {
           st = new StringTokenizer(br.readLine());
           for(int j=0;j<n;j++)
    	   arr[i][j]=Integer.valueOf(st.nextToken());
       }
       Queue <Pipe>que=new ArrayDeque();
       que.add(new Pipe(0,1,0,0));
       int answer=0;
       while(!que.isEmpty()) {
    	   Pipe p=que.remove();
    	   if(isDepart(p,n))
    		   answer++;
    	   int dir=returnPipeDir(p);
    	   if(dir==1) {
    		   	int x1=p.x1+1;
    		   	int x2=p.x2+1;
    		   	int y1=p.y1;
    		   	int y2=p.y2;
    		   	if(x2<n && arr[y2][x2]==0) {
    		   		que.add(new Pipe(x1,x2,y1,y2));
    		   	}
       		   	x1=p.x1+1;
    		   	x2=p.x2+1;
    		   	y1=p.y1;
    		    y2=p.y2+1;
    		   	if(x2<n && y2<n&& arr[y2][x2]==0 && arr[y2-1][x2]==0&& arr[y2][x2-1]==0) {
    		   		que.add(new Pipe(x1,x2,y1,y2));
    		   	}
    	   }
    	   else if(dir==3) {
    		   	int x1=p.x1;
    		   	int x2=p.x2;
    		   	int y1=p.y1+1;
    		   	int y2=p.y2+1;
    		   	if(y2<n&& arr[y2][x2]==0) {
    		   		que.add(new Pipe(x1,x2,y1,y2));

    		   	}
    		   	x1=p.x1;
    		   	x2=p.x2+1;
    		   	y1=p.y1+1;
    		    y2=p.y2+1;
    		   	if(x2<n && y2<n&& arr[y2][x2]==0 && arr[y2][x2-1]==0&& arr[y2-1][x2]==0) {
    		   		que.add(new Pipe(x1,x2,y1,y2));
    		   	}
    	   }
    	   else if(dir==2) {
   		   	int x1=p.x1+1;
   		   	int x2=p.x2+1;
   		   	int y1=p.y1+1;
   		   	int y2=p.y2;
   		   	if(x2<n && y1<n&& arr[y2][x2]==0) {
   		   		que.add(new Pipe(x1,x2,y1,y2));

   		   	}
   		   	x1=p.x1+1;
   		   	x2=p.x2;
   		   	y1=p.y1+1;
   		    y2=p.y2+1;
   		   	if(y2<n&& arr[y2][x2]==0) {
   		   		que.add(new Pipe(x1,x2,y1,y2));
   		   	}
   		   	x1=p.x1+1;
   		   	x2=p.x2+1;
   		   	y1=p.y1+1;
   		    y2=p.y2+1;
   		   	if(y2<n && x2<n&& arr[y2][x2]==0&& arr[y2-1][x2]==0&& arr[y2][x2-1]==0 ) {
   		   		que.add(new Pipe(x1,x2,y1,y2));
   		   	}
   	   }
       }
       
       System.out.println(answer);
       
       
       

    }
    static class Pipe{
    	int x1;
    	int x2;
    	int y1;
    	int y2;
    	public  Pipe(int x1,int x2,int y1,int y2) {
    		this.x1=x1;
    		this.x2=x2;
    		this.y1=y1;
    		this.y2=y2;
    	}
    }
    //가로가 1 대각선이 2세로가 3
    public static int returnPipeDir(Pipe p) {
    	if(p.y1==p.y2 && p.x1+1==p.x2)
    		return 1;
    	else if(p.y1+1==p.y2 && p.x1+1==p.x2)
    		return 2;
    	else 
    		return 3;

    }
    public static boolean isDepart(Pipe p,int n) {
    	if(p.x2==n-1 &&  p.y2==n-1) {
    		return true;
    	}
    	return false;
    }
}
