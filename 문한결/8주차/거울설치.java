import java.util.*;
import java.io.*;


// 처음 나온 #: start 두번 째 나온# end
// - initialize()
// #,! 가 각 노드들의 수 . 하고 거리는 arr돌면서 체크-> 숫자에 넣어두고 ㄱㄱ
//

public class 거울설치 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String[] input;
    static String[][] maps;

    static int startX, startY;

    static int answer;



    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        maps = new String[N][N];
        int k = 1;
        for (int i = 0; i < N; i++) {
            input = br.readLine().trim().split("");
            for (int j = 0; j < N; j++) {
                maps[i][j] = input[j];
                if (input[j].equals("#")) {
                    if (k == 1) {
                        startX = j;
                        startY = i;
                        k++;
                    }
                }
            }
        }
        bfs();
        System.out.println(answer);


    }

    // 왼: 0 위 :1 오:2 아래 :3
    static void bfs() {
        Queue<int[]> que = new PriorityQueue<>((arr1, arr2) -> arr1[3] - arr2[3]);
        initialize(que);
        while ( !que.isEmpty()) {
            int[] xydirNum = que.remove();
            int x = xydirNum[0], y = xydirNum[1], dir = xydirNum[2], num = xydirNum[3];

            if (dir % 2 == 0) {
                for (int i = y - 1; i >= 0; i--) {
                    if (maps[i][x].equals("*"))
                        break;
                    if (maps[i][x].equals("#")) {
                        answer = num;
                        return;
                    }
                    if (maps[i][x].equals("!")) {

                        for (int j = i - 1; j >= 0; j--) {
                            if (maps[j][x].equals("*"))
                                break;
                            if (maps[j][x].equals("#")) {
                                answer = num;
                                return;
                            }
                            if (maps[j][x].equals("!")) {
                                que.add(new int[] {x, j, 3, num + 1});
                            }
                        }

                        que.add(new int[] {x, i, 3, num + 1});

                        break;
                    }
                }

                for (int i = y + 1; i < N; i++) {
                    if (maps[i][x].equals("*"))
                        break;

                    if (maps[i][x].equals("#")) {
                        answer = num;
                        return;
                    }
                    if (maps[i][x].equals("!")) {
                        for (int j = i + 1; j < N; j++) {
                            if (maps[j][x].equals("*"))
                                break;
                            if (maps[j][x].equals("#")) {
                                answer = num;
                                return;
                            }
                            if (maps[j][x].equals("!")) {
                                que.add(new int[] {x, j, 1, num + 1});
                            }
                        }

                        que.add(new int[] {x, i, 1, num + 1});

                        break;
                    }
                }

            } else {
                for (int i = x - 1; i >= 0; i--) {
                    if (maps[y][i].equals("*"))
                        break;

                    if (maps[y][i].equals("#")) {
                        answer = num;
                        return;
                    }
                    if (maps[y][i].equals("!")) {
                        for (int j = i - 1; j >= 0; j--) {
                            if (maps[y][j].equals("*"))
                                break;
                            if (maps[y][j].equals("#")) {
                                answer = num;
                                return;
                            }
                            if (maps[y][j].equals("!")) {
                                que.add(new int[] {j, y, 0, num + 1});
                            }
                        }
                        que.add(new int[] {i, y, 0, num + 1});

                        break;
                    }
                }

                for (int i = x + 1; i < N; i++) {
                    if (maps[y][i].equals("*"))
                        break;

                    if (maps[y][i].equals("#")) {
                        answer = num;
                        return;
                    }
                    if (maps[y][i].equals("!")) {

                        for (int j = i + 1; j < N; j++) {
                            if (maps[y][j].equals("*"))
                                break;
                            if (maps[y][j].equals("#")) {
                                answer = num;
                                return;
                            }
                            if (maps[y][j].equals("!")) {
                                que.add(new int[] {j, y, 2, num + 1});
                            }
                        }
                        que.add(new int[] {i, y, 2, num + 1});

                        break;
                    }
                }
            }
        }



    }

    static void initialize(Queue<int[]> que) {
        for (int i = startX + 1; i < N; i++) {
            if (maps[startY][i].equals("*")) {
                break;
            }
            if(maps[startY][i].equals("#")) {
                System.out.println(0);
                System.exit(0);
            }
            if (maps[startY][i].equals("!")) {
                que.add(new int[] {i, startY, 2, 1});

            }
        }
        for (int i = startX - 1; i >= 0; i--) {
            if (maps[startY][i].equals("*")) {
                break;
            }
            if(maps[startY][i].equals("#")) {
                System.out.println(0);
                System.exit(0);
            }
            if (maps[startY][i].equals("!")) {
                que.add(new int[] {i, startY, 0, 1});
            }
        }
        for (int i = startY - 1; i >= 0; i--) {
            if (maps[i][startX].equals("*")) {
                break;
            }
            if(maps[i][startX].equals("#")) {
                System.out.println(0);
                System.exit(0);
            }
            if (maps[i][startX].equals("!")) {
                que.add(new int[] {startX, i, 3, 1});


            }
        }
        for (int i = startY + 1; i < N; i++) {
            if (maps[i][startX].equals("*")) {
                break;
            }
            if(maps[i][startX].equals("#")) {
                System.out.println(0);
                System.exit(0);
            }
            if (maps[i][startX].equals("!")) {
                que.add(new int[] {startX, i, 1, 1});
            }
        }
        maps[startY][startX]=".";

    }
}






