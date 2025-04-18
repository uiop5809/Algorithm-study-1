import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 세수의합 {
	static int[] sums,arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		arr = new int[size];
		for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		
		sums = new int[size*size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) sums[i*size+j] = arr[i]+arr[j];
		}
		Arrays.sort(sums);
		
		int ans = -1;
		for (int i = 1; i < size; i++) {
			for (int j = 0; j < i; j++) {
				int diff = arr[i]-arr[j];
				if(binarySearch(diff)) ans = i;
			}
		}
		
		System.out.println(arr[ans]);
		
		
	}

	private static boolean binarySearch(int diff) {
		int start = 0;
		int end = sums.length;
		
		int mid = (start+end)/2;
		
		while(start < end) {
			if (sums[mid] == diff) return true;
			else if (sums[mid] > diff) end = mid;
			else start = mid +1;
			
			mid = (start+end)/2;
		}
		
		return false;
	}
}
