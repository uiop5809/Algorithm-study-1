import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 기차가_어둠을_헤치고_은하수를 {
	static int[] train;
	
	static int trainCnt;
	static int orderCnt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		trainCnt = Integer.parseInt(st.nextToken());
		orderCnt = Integer.parseInt(st.nextToken());
		
		train = new int[trainCnt];
		
		for (int i = 0; i < orderCnt; i++) process();
		
		Set<Integer> set = new HashSet<>();
		
		for (int i = 0; i < trainCnt; i++) {
			set.add(train[i]);
		}
		
		System.out.println(set.size());
	}
	
	private static  void process() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int orderN = Integer.parseInt(st.nextToken());
		int trainNum = Integer.parseInt(st.nextToken())-1;
		int seatN = -1;
		
		switch (orderN) {
			case 1: 
				seatN = Integer.parseInt(st.nextToken())-1;
				train[trainNum] |= (1<<seatN);
				break;
				
			case 2: 
				seatN = Integer.parseInt(st.nextToken())-1;
				train[trainNum] &= ~(1 << seatN);
				break;
				
			case 3:
				//20을 넘어가서 기차를 타는사람이 있는 것을 방지
				train[trainNum] = (train[trainNum] << 1) & ((1 << 20) - 1);
				break;
			case 4:
				train[trainNum] >>= 1;
				break;
				
		}
	}
}
