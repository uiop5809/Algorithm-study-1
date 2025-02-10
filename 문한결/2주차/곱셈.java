import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author mhg10
 * 20억개 이므로, 1억으로 줄이려면 20분에 1을 해야함.
 * 그냥 같은 수니까 2 4 인 경우 -> 4 2로 바꿀 수 있다.
 * 즉       2  40은 ->  2** 10 -> 4가 된다.  
 * 2**10-> 1028 2**20-> 1000000<- 이거면 뭐 ㄱㅊ을듯.
 */
public class Main {

    public static long arr[];
    public static long A,B,C;
    public static long newA,newB,remainB;
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	arr=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        A=arr[0];
        B=arr[1];
        C=arr[2];
   
        System.out.println(simplify(A,B));
    }
    private static long simplify(long currentA,long currentB) {
    	if(currentB==1)
    		return currentA%C;
    	else {
    		long temp=simplify(currentA%C,currentB/2)%C;
    	if(currentB%2==0) 
    		return temp*temp%C;
    	else
    		return (temp*temp%C)*A%C;
    	}
    		
    }



 }



