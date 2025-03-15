import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어_벨트_위의_로봇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        int[] container = new int[size * 2];  // 벨트의 내구도
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size * 2; i++) {
            container[i] = Integer.parseInt(st.nextToken());
        }

        // 로봇 위치를 나타내는 비트마스크 (0번부터 size-1번 칸)
        boolean[] robots = new boolean[size];

        int broken = 0;  // 내구도가 0인 칸의 개수
        int level = 0;   // 단계(시뮬레이션 회수)

        while (broken < limit) {
            level++;

            
            // 로봇 & 컨테이너 회전
            for (int i = size -2; i > 0; i--) {
            	robots[i] = robots[i-1];
            }
            robots[size-1] = false;
            robots[0] = false;
            
            
            int tempContainer = container[2*size-1];
            for (int i = 2*size -1; i >0;i--) {
            	container[i] =  container[i-1];
            }
            container[0] = tempContainer;
            
            for (int i = size-2; i >=0 ; i--) {
            	//i칸에는 로봇 있고, i+1칸에는 없으며 내구도가 0보다 큰 경우 이동
            	if (robots[i] && !robots[i+1] && container[i+1]>0) {
            		robots[i] =false;
            		
            		// 끝 위치라면 바로 내림
            		if (i != size-2) robots[i+1] = true;
            		
            		// 0달성하면 broken
            		if (--container[i+1] == 0) broken++;
            	}
            }
            
            if (!robots[0] && container[0] > 0) {
            	robots[0] = true;
            	if (--container[0] == 0) broken++;
            }

        }

        System.out.println(level);
    }
}
