import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder sb = new StringBuilder();
	private static final int MAXNUM = 11;
	
	private static int[][] metrix = new int[MAXNUM][MAXNUM];
	private static int[] population = new int[MAXNUM];
	private static Set<Integer> section1 = new HashSet<>();
	private static Set<Integer> section2 = new HashSet<>();
	
	private static String[] in;
	private static int metSize,ans=Integer.MAX_VALUE;
	
	
	public static void main(String args[]) throws Exception {
		in = br.readLine().split(" ");
		metSize = Integer.parseInt(in[0]);

		in = br.readLine().split(" ");
		for(int i = 0;i<metSize;i++)
			population[i+1] = Integer.parseInt(in[i]);
		
		for(int i = 1;i<=metSize;i++) {
			String[] tmpIn = br.readLine().split(" ");
			for(int j = 1;j<tmpIn.length;j++) {
				metrix[i][Integer.parseInt(tmpIn[j])]=1;
			}
		}
		
		dfs(1);
		
		if(ans==Integer.MAX_VALUE) sb.append("-1");
		else sb.append(ans+"");
		
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int now) {
		if(now > metSize) {
			if(section1.isEmpty() || section2.isEmpty()) return;
			ans = Math.min(ans, check());
			return;
		}
		else {
			section1.add(now);
			dfs(now+1);
			section1.remove(now);
			section2.add(now);
			dfs(now+1);
			section2.remove(now);
		}
	}
	
	public static int check() {
		int ret=0, sectionCnt=0;
		ArrayList<Integer> sectionList1 = new ArrayList<>(section1);
		ArrayList<Integer> sectionList2 = new ArrayList<>(section2);
		
		sectionCnt = countSection(sectionList1.get(0),1)+countSection(sectionList2.get(0),2);
		if(sectionCnt != metSize)return Integer.MAX_VALUE;
		
		for(int i = 0;i<sectionList1.size();i++)ret+=population[sectionList1.get(i)];
		for(int i = 0;i<sectionList2.size();i++)ret-=population[sectionList2.get(i)];

		return Math.abs(ret);
	}
	public static int countSection(int start,int secIdx) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.addLast(start);

		int[] visit = new int[metSize+1];
		Arrays.fill(visit,0);
		visit[start]=1;
		
		int ret=0;
		
		while(!dq.isEmpty()) {
			int now = dq.peekFirst();
			dq.pollFirst();
			ret++;

			for(int i =1;i<=metSize;i++) {
				if((secIdx==1&&section1.contains(i))||(secIdx==2&&section2.contains(i)))
				if(metrix[now][i] == 1 && visit[i] == 0) {
					visit[i] = 1;
					dq.addLast(i);
				}
			}
		}
		return ret;
	}
}