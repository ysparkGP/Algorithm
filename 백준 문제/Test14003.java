package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * LIS 이분탐색 dp
 */
public class Test14003 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N];
		int[] record = new int[N];
		int increasing = 0;
		for(int i = 0; i<N; i++) {
			int index = binarySearch(record, dp, 0, increasing, arr[i]);
			if(index >= increasing) {
				dp[increasing++] = arr[i];
			}
			else {
				dp[index] = arr[i];
			}
			record[i] = index;
			
		}
		
		System.out.println(increasing);
		recordPrint(record, arr, increasing);
	}
	static void recordPrint(int[] record, int[] arr, int increasing) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] print = new int[increasing];
		int start = increasing-1;
		for(int i = arr.length-1; i>=0; i--){
			if(start == record[i]) {
				print[start--] = arr[i];
			}
		}
		for(int i = 0; i<increasing; i++) bw.write(print[i] + " ");
		bw.flush();
		bw.close();
	}
	
	static int binarySearch(int[] record, int[] dp, int start, int increasing, int value) { // lower bound
		if(increasing == 0) return 0;
		int end = increasing;
		while(start < end) {
			int mid = (start+end)/2;
			
			if(dp[mid] >= value) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		return end;	
	}

}
