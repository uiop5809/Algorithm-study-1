import java.util.*;
import java.io.*;
public class 세수두M {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int []arr;
    static long answer=Long.MIN_VALUE;

    public static void main(String[] args) throws  Exception{
        init();
        execute();
        System.out.println(Math.abs(answer));

    }
    static void init() throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new int[n];
        for(int i=0;i<n;i++)arr[i]=Integer.parseInt(br.readLine());
    }
    static void execute(){
        Arrays.sort(arr);
        for(int i=1;i<n-1;i++){
            long temp= Math.max(Math.abs(arr[n-1]+arr[i-1]-arr[i]* 2L), Math.abs(arr[0]+arr[i+1]-arr[i]* 2L));
            answer=Math.max(answer,temp);

        }

    }
}
