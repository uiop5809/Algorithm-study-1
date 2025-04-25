import java.util.*;
import java.io.*;
public class 할일정하기 {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int dp[][];
    static int arr[][];
    static int [] input;
    static int answer=0;
    public static void main(String[] args) throws Exception{
        init();
        execute();
        System.out.println(answer);

    }
    static void init()throws  Exception{
        n=Integer.parseInt(br.readLine());
        arr=new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=1;j<=n;j++)arr[i][j]=input[j-1];
        }
        dp=new int[n+1][1<<(n+1)];
        for(int []a:dp)Arrays.fill(a,Integer.MAX_VALUE);
        dp[0][0]=0;
    }
    static void execute(){
        for(int i=1;i<=n;i++){
            for(int j=0;j<(1<<(n+1));j++){
                if(dp[i-1][j]==Integer.MAX_VALUE)continue;
                for(int k=0;k<=n;k++){
                    if((j&(1<<k))!=0)continue;
                    dp[i][j|(1<<k)]=Math.min(dp[i][j|(1<<k)],dp[i-1][j]+arr[i][k]);

                }

            }

        }
        answer = dp[n][(1 << n+1) - 2];    }

}
