import java.util.*;
import java.io.*;


public class 은하수 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int input[];
    static int train[];
    static Set<Integer> trainSet;

    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        train = new int[N];
        trainSet = new HashSet<>();
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            command(input[0], input[1], input.length == 3 ? input[2] : 0);
        }
        for (int i = 0; i < N; i++) {
            trainSet.add(train[i]);
        }
        System.out.println(trainSet.size());


    }

    public static void command(int c, int i, int x) {
        switch (c) {
            case 1:
                train[i - 1] = train[i - 1] | (1 << (x - 1));
                break;
            case 2:
                train[i - 1] = train[i - 1] & (~(1 << (x - 1)));
                break;
            case 3:
                train[i - 1] = train[i - 1] << 1;
                train[i - 1] = train[i - 1] % ((int) Math.pow(2, 20));
                break;
            case 4:
                train[i - 1] = train[i - 1] >> 1;
                break;
        }

    }



}
