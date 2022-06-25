package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가장 긴 증가하는 부분수열(LIS)
 * dp
 */

public class Test2352 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(findMaxConnection(arr));
	}
	
	static int findMaxConnection(int[] arr) {
		
		int[] dp = new int[arr.length];
		int increment = 0;
		for(int i = 0; i<arr.length; i++) {
			if(increment == 0) {
				dp[increment++] = arr[i];
			}
			else {
				int index = binarySearch(dp, arr[i], increment);
				if(index >= increment) {
					dp[increment++] = arr[i];
				}
				else {
					dp[index] = arr[i];
				}
			}
		}
		
		return increment;
	}
	
	static int binarySearch(int[] dp, int value, int increment) { // upper bound
		int start = 0;
		int end = increment;
		while(start < end) {
			int mid = (start+end)/2;
			
			if(dp[mid] <= value) {
				start = mid+1;
			}
			else {
				end = mid;
			}
		}
		return end;
	}

}
