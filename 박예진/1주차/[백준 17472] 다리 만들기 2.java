package algorithm;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
    // 오름차순 우선순위큐
    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

class Main
{
    static int N, M, idx = 1;
    static int[][] arr;
    static int[][] newArr;
    static int[] parent;
    static boolean[][] visited;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1}; 

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());;
            }
        }

        // 각 군집들 index 표시하기
        visited = new boolean[N][M];
        newArr = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    idx++;
                }
            }
        }

        // 군집 idx test
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                System.out.print(newArr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        // 0 아닌 것들 탐색해 idx -> idx 간선 우선순위큐에 넣기
        pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (newArr[i][j] != 0) {
                    addEdge(i, j, newArr[i][j]); // 시작 노드
                }
            }
        }

        // pq 출력 test
//        while(!pq.isEmpty()) {
//            Edge cur = pq.poll();
//            System.out.println(cur.v1 + " " + cur.v2 + " " + cur.cost);
//        }

        // 유니온 파인드 하면서 모든 정점 부모 같아질 때까지
        // 부모 노드 초기화
        parent = new int[idx + 1];
        for(int i = 1; i <= idx; i++) {
            parent[i] = i;
        }

        int costSum = 0, edgesUsed = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            // 부모 노드가 다를 때만 (사이클x)
            if (findParent(cur.v1) != findParent(cur.v2)) {
                unionParent(cur.v1, cur.v2);
                costSum += cur.cost;
                edgesUsed++;
            }
        }
        if (costSum == 0 || edgesUsed != idx - 2) System.out.println(-1);
        else System.out.println(costSum);
    }


    public static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static void unionParent(int x, int y) {
        int p1 = findParent(x);
        int p2 = findParent(y);

        if (p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    public static void addEdge(int x, int y, int start) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int cnt = 0;

            if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) continue;
            // 같은 군집이기에 무시
            if (newArr[nx][ny] == start) continue;
            // 0이면 같은 방향으로 직진
            else if (newArr[nx][ny] == 0){
                while(true) {
                    nx += dx[i];
                    ny += dy[i];
                    cnt++;
                    if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) break;
                    // 다른 군집일 경우
                    if (newArr[nx][ny] != 0 && newArr[nx][ny] != start) {
                        if (cnt > 1) {
                            pq.add(new Edge(start, newArr[nx][ny], cnt));  // start, end, cost
                        }
                        break;
                    }
                }
            }
        }
    }

   public static void bfs(int x, int y) {
        Queue <Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            newArr[cur.x][cur.y] = idx;

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) continue;
                if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }
    

}
