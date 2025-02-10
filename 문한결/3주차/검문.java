import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




/**
 * 
 * @author SSAFY
 * 
 * 아니 문제가 검문이랑 완전 관련이 없음 --
 * b형 특강-> 우선 관찰을 해보자 
 * 
 * 3
 * 6= rem+M*&              6-rem=M*&
 * 34=rem+M*>>             34-rem=M*>>
 * 38=rem+M*?              38-rem=M*?
 *                          4=M(?->>)즉 4도 M을 약수로 갖네
 *                          32=(>>-&)이것도 M을 약수로 갖네
 *                          정렬후 차이를 윸
 *                          M은 최대 공약수를 구해서 약수 처리
 * 같은 나머지를 가지는 몫을 구하여라ㅏㅏㅏㅏ
 */
public class Main {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; 
    private static int[] arr;
    private static int[] difArr;
    private static int maxAnswer;
    private static List<Integer> answers;

    
   
    public static void main(String[] args) throws IOException {
    	N=Integer.valueOf(br.readLine());
    	arr=new int[N];
        for(int i=0;i<N;i++) {
        	arr[i]=Integer.valueOf(br.readLine());
        }
        difArr=new int[N-1];
        insertDif();
        maxAnswer=findAnswerMax();
        findAnswer();
        for(int an: answers) {
        	System.out.print(an+" ");
        	
        }
        
    }
    private static void insertDif() {
    	for(int i=0;i<N-1;i++) {
    		difArr[i]=Math.abs(arr[i+1]-arr[i]);
    	}
    }
    private static int uclid(int a,int b) {
    	if(a%b==0)
    		return b;
    	return uclid(b,a%b);
    }
    private static int findAnswerMax() {
        int result = difArr[0];  // 첫 번째 차이로 초기화
        for (int i = 1; i < N - 1; i++) {
            result = uclid(result, difArr[i]);
        }
        return result;
    }
    private static void findAnswer() {
    	answers=new LinkedList();
    	for(int i=2;i<=maxAnswer/2;i++) {
    		if(maxAnswer%i==0)
    			answers.add(i);
    	}
    	answers.add(maxAnswer);
    }
    
    

  
} 