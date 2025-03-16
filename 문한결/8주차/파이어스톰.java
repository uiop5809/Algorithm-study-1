import java.util.*;
import java.io.*;

public class 파이어스톰{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, q;
    static int[] l;
    static int[][] arr;
    static int answer1, answer2;
    static int[][] newArr;
    static int[] input;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static  boolean[][] visted ;

    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        q = input[1];
        arr = new int[1 << n][1 << n];
        for (int i = 0; i < (1 << n); i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < (1 << n); j++)
                arr[i][j] = input[j];
        }
        l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        excute();
        findAnswer1();
        findAnswer2();

        System.out.println(answer1);
        System.out.println(answer2);

    }

    static void excute() {
        for (int i = 0; i < q; i++) {
            newArr = new int[1 << n][1 << n];
            for (int k = 1; k <= l[i]; k++)
                rotateAll(k);

            removeIce();


        }


    }



    static void rotateAll(int step) {
        if (step == 0)
            return;
        for (int i = 0; i < (1 << n); i += (1 << step)) {
            for (int j = 0; j < (1 << n); j += (1 << step)) {
                rotate(j, i, step);
            }
        }
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++)
                arr[i][j] = newArr[i][j];
        }

    }

    static void rotate(int x, int y, int step) {
        for (int i = y; i < y + (1 << step); i++) {
            for (int j = x; j < x + (1 << step); j++) {
                moveIce(j, i, y + (1 << (step - 1)), x + (1 << (step - 1)), step);
            }
        }
    }

    static void moveIce(int x, int y, int comapreY, int comapreX, int curStep) {
        int dir = 0;
        if (x < comapreX && y < comapreY)
            dir = 1;
        else if (x >= comapreX && y < comapreY)
            dir = 2;
        else if (x < comapreX && y >= comapreY)
            dir = 0;
        else if (x >= comapreX && y >= comapreY)
            dir = 3;
        newArr[y + (1 << (curStep - 1)) * dy[dir]][x + (1 << (curStep - 1)) * dx[dir]] = arr[y][x];

    }

    static void removeIce() {
        int[][] removeIce = new int[1 << n][1 << n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++)
                removeIce[i][j] = arr[i][j];
        }

        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++) {
                if (returnAdj(j, i) >= 2) {
                    if (arr[i][j] > 0)
                        removeIce[i][j] -= 1;
                }
            }
        }
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++)
                arr[i][j] = removeIce[i][j];
        }
    }

    static int returnAdj(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= (1 << n) || y + dy[i] < 0 || y + dy[i] >= (1 << n)
                    || arr[y + dy[i]][x + dx[i]] == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    static void findAnswer1() {
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++) {
                answer1 += arr[i][j];
            }
        }
    }

    static void findAnswer2() {
        visted = new boolean[1 << n][1 << n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < (1 << n); j++)
                if (!visted[i][j] && arr[i][j] != 0) answer2 = Math.max(answer2, bfs(i,j));

        }
    }
    static int bfs(int i,int j){
        int cnt = 1;
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[] {j, i});
        visted[i][j] = true;
        while (!que.isEmpty()) {
            int xy[] = que.remove();
            for (int k = 0; k < 4; k++) {
                int nx = dx[k] + xy[0];
                int ny = dy[k] + xy[1];
                if (nx < 0 || nx >= (1 << n) || ny < 0 || ny >= (1 << n)
                        || visted[ny][nx] || arr[ny][nx] == 0)
                    continue;
                que.add(new int[] {nx, ny});
                visted[ny][nx] = true;
                cnt += 1;

            }
        }
        return cnt;
    }

}
