import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//인접한 모든 자릿수 1차이가 되어야함
//
//  처음의 수는 1,2,3,4,5,6,7,8,9가 가능함
//  1->0,2->2개, 2->2개...... 9->한개
//  즉 [자릿 수][첫자리 수] 형태의 dp를 만들면 되겠다고 생각함
//
//
//
public class Main {
    public static    long dp[][];
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
           int N=Integer.valueOf(br.readLine());
           dp=new long[N+1][];
           for(int i=0;i<N+1;i++){
               dp[i]=new long[10];
           }
           for(int i=1;i<10;i++){
               dp[1][i]=1;
           }
        long sum=0;
           for(int i=2;i<N+1;i++){
               for(int j=0;j<10;j++){
                   if(j==0) {
                       dp[i][j] += dp[i - 1][j + 1]% 1000000000;

                       continue;
                   } else if (j==9) {
                       dp[i][j]+=dp[i-1][j-1]% 1000000000;

                       continue;
                   }
                   dp[i][j]+=dp[i-1][j-1]% 1000000000;
                   dp[i][j]+=dp[i-1][j+1]% 1000000000;
               }

           }
           for(int i=0;i<10;i++){
               sum=(sum+dp[N][i])% 1000000000;

           }
        System.out.println(sum% 1000000000);

    }

}
