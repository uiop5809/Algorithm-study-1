import java.util.*;
import java.io.*;

class Main
{
	static int N, res = 1;
	static List<Conference> clist;
	
	static class Conference{
		private int end, start;
		
		public Conference(int end, int start) {
			this.end = end;
			this.start = start;
		}
	}
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	clist = new ArrayList<>();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		
    		clist.add(new Conference(end, start));
    	}
    	
    	// 해결
    	greedy();
    	
    	// 출력
    	System.out.println(res);
    }
    
    static void greedy() {    	
    	Collections.sort(clist, (c1, c2) -> {
    		if(c1.end != c2.end) return Integer.compare(c1.end, c2.end);
    		return Integer.compare(c1.start, c2.start);
    	});
    	
    	int time = clist.get(0).end;
    	for(int i = 1; i < N; i++) {
    		Conference next = clist.get(i);
    		// 종료 시각 <= 다음 시작 시각
    		if (time <= next.start) {
    			time = next.end;
    			res++;
    		}
    	}
    }
    
}
