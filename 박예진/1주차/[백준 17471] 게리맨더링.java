import java.util.*;
import java.io.*;

class Main
{
	static int N;
	static int[] value; // 인구수
	static int[] parent;
	static boolean[] visited;
	static int ans = Integer.MAX_VALUE;
	
	static List<Integer>[] graph;
	
	public static int getParent(int x) {
		if (parent[x] == x) return x;
		return getParent(parent[x]);
	}
	
	public static void unionParent(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		if (x < y) parent[y] = x;
		else parent[x] = y;
	}
	
    public static void main(String args[]) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 입력
    	N = Integer.parseInt(st.nextToken());
    	
    	value = new int[N + 1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= N; i++) {
    		value[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 그래프 입력
    	graph = new LinkedList[N + 1];
    	for(int i = 1; i <= N; i++) {
    		graph[i] = new LinkedList<>();
    		st = new StringTokenizer(br.readLine());
    		int cnt = Integer.parseInt(st.nextToken());
    		for(int j = 0; j < cnt; j++) {
    			int v = Integer.parseInt(st.nextToken());
    			graph[i].add(v);
    		}
    	}
    	
    	// 해결
    	// 조합 nCr n=N r=1, 2,,, N/2까지
    	int T = N / 2 + 1;
    	while(T-- > 1) {
    		visited = new boolean[N + 1];
    		dfs(1, 0, T);
    	}
    	
    	// 정답
    	if (ans == Integer.MAX_VALUE) System.out.println(-1);
    	else System.out.println(ans);
    }
    
    static void dfs(int idx, int depth, int T) {
    	if (depth == T) {
    		// nCt 조합대로 연결 
    		solution();
    		return;
    	}
    	
    	for(int i = idx; i <= N; i++) {
    		if (!visited[i]) {
        		visited[i] = true;
        		dfs(i, depth + 1, T);
        		visited[i] = false;
    		}

    	}
    }
    
    static void solution() {
    	// 부모 노드 초기화
    	parent = new int[N + 1];
    	for(int i = 1; i <= N; i++) {
    		parent[i] = i;
    	}
		
		// visited된 곳과 안된 곳이 다 연결되어 있는지 확인
		List <Integer> L1 = new ArrayList<>(); // 된 곳
		List <Integer> L2 = new ArrayList<>(); // 안된 곳

		for(int i = 1; i <= N; i++) {
			if (visited[i]) L1.add(i);
			else L2.add(i);
		}
		
		// L1 연결하기
        for (int i : L1) {
            for (int j : graph[i]) {
                if (L1.contains(j)) unionParent(i, j);
            }
        }
        // 연결이 다 됐는지
        boolean flag1 = true;
        for (int i = 0; i < L1.size() - 1; i++) {
            if (getParent(L1.get(i)) != getParent(L1.get(i + 1))) {
                flag1 = false;
                break;
            }
        }
		
		// L2 연결하기
        for (int i : L2) {
            for (int j : graph[i]) {
                if (L2.contains(j)) unionParent(i, j);
            }
        }
        // 연결이 다 됐는지
        boolean flag2 = true;
        for (int i = 0; i < L2.size() - 1; i++) {
            if (getParent(L2.get(i)) != getParent(L2.get(i + 1))) {
                flag2 = false;
                break;
            }
        }
	
		// 두 선거구로 나눌 수 있는 경우
		if (flag1 && flag2) {
			int sum1 = 0, sum2 = 0;
	        for (int i : L1) sum1 += value[i];
            for (int i : L2) sum2 += value[i];
			ans = Math.min(ans, Math.abs(sum1 - sum2));
		} 
    }
    

}
