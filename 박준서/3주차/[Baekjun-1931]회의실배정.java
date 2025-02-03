import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    
    // 회의 시간을 저장하는 Block 클래스
    static class Block{
        int x,y;  // x: 시작 시간, y: 종료 시간

        public Block(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    
    private static ArrayList<Block> vec = new ArrayList<>();  // 회의 시간을 저장하는 리스트
    private static String[] in;
    private static int n;  // 회의의 수

    public static void main(String args[]) throws Exception {
        in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);
        
        // n개의 회의 시간 입력
        for(int i = 0;i<n;i++) {
            int a,b;
            in = br.readLine().split(" ");
            a = Integer.parseInt(in[0]);  // 시작 시간
            b = Integer.parseInt(in[1]);  // 종료 시간
            vec.add(new Block(a,b));
        }
        
        // 종료 시간을 기준으로 정렬, 종료 시간이 같으면 시작 시간이 빠른 순서로 정렬
        vec.sort((o1, o2) -> {
            if (o1.y == o2.y) return o1.x - o2.x;
            return o1.y - o2.y;
        });
        
        // 그리디 알고리즘으로 최대 회의 개수 계산
        int cnt=1,now=vec.get(0).y;  // cnt: 회의 개수, now: 현재 회의 종료 시간
        for(int i = 1;i<n;i++) {
            // 현재 회의 종료 시간보다 다음 회의 시작 시간이 같거나 크면 회의 가능
            if(vec.get(i).x >= now) {
                now = vec.get(i).y;  // 현재 회의 종료 시간 갱신
                cnt++;  // 회의 개수 증가
            }
        }
        
        sb.append(cnt).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
