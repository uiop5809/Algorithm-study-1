import java.util.*;
import java.io.*;

public class 컨베이어 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int[] input;
    static int[] arr;
    static int answer = 0;
    static List<Integer> robots;


    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];
        arr = new int[2 * N];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        robots = new ArrayList<>();
        findAnwer();
        System.out.println(answer);

    }

    public static void findAnwer() {
        while (true) {
            answer++;

            rotateAll();

            moveRobot();
            putRobotAtZero();


            if (isFinish())
                break;


        }

    }



    public static void rotateAll() {
        int temp = arr[2 * N - 1];

        for (int i = 2 * N - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = temp;

        List<Integer> newList = new ArrayList<>();
        for (int pos : robots) {
            int movePos = (pos + 1) % (2 * N);
            if (movePos == N - 1) {
                continue;
            }

            newList.add(movePos);
        }
        robots = newList;

    }



    // 0 1 1 2 1 2
    static void moveRobot() {
        List<Integer> newList = new ArrayList<>();
        List<Integer > tempL=new ArrayList();

        for(int i: robots)tempL.add(i);
        for (int pos : tempL) {

            int movePos = pos + 1;

            if (isNotMove(movePos)) {
                newList.add(pos);
                continue;
            }
            arr[movePos] -= 1;
            removeRobot(tempL, pos);
            if (movePos >= N - 1) {
                continue;
            }

            robots.add(movePos);


            newList.add((movePos));


        }
        robots = newList;

    }

    private static boolean isNotMove(int movePos) {
        return robots.contains(movePos) || arr[movePos] <= 0;
    }

    private static void removeRobot(List<Integer> tempL, int pos) {
        for(int j=0;j< robots.size();j++) {
            if(robots.get(j)==pos) {
                robots.remove(j);
                break;
            }
        }
    }
    private static void putRobotAtZero() {
        if (arr[0] >= 1) {
            arr[0] -= 1;
            robots.add(0);
        }
    }

    public static boolean isFinish() {
        int cnt = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (arr[i] == 0)
                cnt++;
        }
        if (cnt >= K)
            return true;
        return false;
    }

}


