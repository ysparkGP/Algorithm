package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * LIS (이분탐색으로 O(n^2) -> O(nlogn)) 
 */

public class Test12015 {
	
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[N];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+1];
		int length = 0;
		for(int i = 0; i<N; i++) {
			int resultIndex = binarySearch(1,length, arr[i]);
			
			if(resultIndex > length) {
				dp[++length] = arr[i];
			}
			else {
				dp[resultIndex] = arr[i];
			}
		}
		/*for(int i = 1; i<=length; i++) {
			System.out.print(dp[i] + " ");
		}*/
		System.out.println(length);
	}
	
	// lower bound
	static int binarySearch(int start, int end, int value) {
		
		
		while(start <= end) {
			int mid = (start+end)/2;
			
			if(dp[mid] >= value) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		return end+1;
	}

}
