import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * @author mhg10
 * 
 *   2       3      1
 * 5    6       4      1
 * 뭔 58점이여
 *
 */
public class Main {
  
	private static int N;
	private static long[] distance;
	private static long[] costs;

 
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    N=Integer.valueOf(br.readLine());
    distance=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    costs=Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    long minC=costs[0];
    long answer=0;
    for(int i=0;i<N-1;i++) {
    	minC=Math.min(minC, costs[i]);
    	answer+=(minC*distance[i]);
    }
    System.out.println(answer);
    }



 }



