import java.util.*;
import java.io.*;

class Main
{
	static int N, M, K, X;  // 노드 수, 간선 수, 거리, 출발 노드
	static int[] dist;
	static List<Integer>[] graph;
	static List<Integer> answer;
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	X = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int A = Integer.parseInt(st.nextToken());
    		int B = Integer.parseInt(st.nextToken());
    		graph[A].add(B); 
    	}
    	
    	// 해결
    	answer = new ArrayList<>();
    	dist = new int[N + 1];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	bfs();
    	
    	// 결과
    	if (answer.isEmpty()) System.out.println(-1);
    	else {
        	Collections.sort(answer);
        	for(int ans : answer) {
        		System.out.println(ans);
        	}
    	}
    }
    
    static void bfs() {
    	dist[X] = 0;
    	Queue<Integer> q = new LinkedList<>();
    	q.add(X);
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		if (dist[cur] > K) break;
    		if (dist[cur] == K) answer.add(cur);
    		
    		for(int next : graph[cur]) {
    			// 이미 방문한 노드라면
    			if (dist[next] != Integer.MAX_VALUE) continue;
    			dist[next] = dist[cur] + 1;
    			q.add(next);
    		}
    	}
    }
    

}
