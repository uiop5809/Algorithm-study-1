import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    
    private static final int[] dx = {0,0,1,-1};
    private static final int[] dy = {-1,1,0,0};
    private static final int MAXNUM = 21;

    private static String[] in;
    private static int n,ans,nowx,nowy,nowSize=2,ate;  // n:맵크기, ans:정답, (nowx,nowy):현재위치, nowSize:현재크기, ate:먹은물고기수
    private static int[][] matrix = new int[MAXNUM][MAXNUM];  // 맵 정보 저장

    // 위치와 거리 정보를 저장하는 Block 클래스
    static class Block{
        int x,y,d;  // x,y:좌표, d:거리

        public Block(int x, int y, int d) {
            super();
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    
    public static void main(String args[]) throws Exception {
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);

        // 맵 정보 입력 및 상어 초기 위치 저장
        for(int i = 0;i<n;i++) {
            in = br.readLine().split(" ");
            for(int j = 0;j<n;j++) {
                matrix[i][j] = Integer.parseInt(in[j]);
                if(matrix[i][j] == 9) {  // 상어 위치 저장
                    nowy=i;nowx=j;
                    matrix[i][j] = 0;
                }
            }
        }

        ArrayList<Block> food;
        // 먹을 수 있는 물고기가 없을 때까지 반복
        do {
            food = findFeed(nowy,nowx);  // 먹을 수 있는 물고기 찾기
            // 거리가 가까운 순, 위쪽, 왼쪽 순으로 정렬
            Collections.sort(food,(o1,o2)->{
                if(o1.d==o2.d) {
                    if(o1.y==o2.y) 
                        return o1.x-o2.x;
                    return o1.y-o2.y;
                }
                return o1.d-o2.d;
            });

            if(food.isEmpty())break;  // 먹을 수 있는 물고기가 없으면 종료
            
            // 물고기를 먹고 상어 위치 이동
            matrix[food.get(0).y][food.get(0).x] = 0;
            nowy = food.get(0).y;
            nowx = food.get(0).x;
            ate++;
            // 자신의 크기만큼 물고기를 먹으면 크기 증가
            if(ate == nowSize) {
                nowSize++;
                ate = 0;
            }
            ans+=food.get(0).d;  // 이동 거리 누적
        }while(!food.isEmpty());
        
        // 결과 출력
        sb.append(ans).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    // BFS를 이용하여 먹을 수 있는 물고기를 찾는 함수
    public static ArrayList<Block> findFeed(int y, int x) {
        Queue<Block> q = new LinkedList<>();
        ArrayList<Block> ret = new ArrayList<>();  // 먹을 수 있는 물고기 리스트
        int[][] visit = new int[n][n];  // 방문 체크 배열
        q.add(new Block(x,y,0));
        visit[y][x]=1;
        
        // BFS 탐색
        while(!q.isEmpty()) {
            int nowx = q.peek().x;
            int nowy = q.peek().y;
            int nowd = q.peek().d;
            q.poll();
            
            for(int i = 0;i<4;i++) {
                int nx = nowx+dx[i];
                int ny = nowy+dy[i];
                
                // 맵을 벗어나거나, 이미 방문했거나, 자신보다 큰 물고기가 있는 칸은 지나갈 수 없음
                if(nx<0 || ny<0 || nx>=n || ny>=n || visit[ny][nx] != 0 || matrix[ny][nx]>nowSize)continue;
                visit[ny][nx] = 1;
                Block tmp = new Block(nx,ny,nowd+1);
                // 자신보다 작은 물고기가 있는 칸이면 먹을 수 있는 물고기 리스트에 추가
                if(matrix[ny][nx]<nowSize && matrix[ny][nx] != 0) ret.add(tmp);
                q.add(tmp);
            }
        }
        
        return ret;
    }
}
