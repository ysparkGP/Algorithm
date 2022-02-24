package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * DP(최장 길이 부분 수열...LIS O(nlogn)
 * => 이분 탐색(lower bound) 으로 LIS에 삽입할 곳을 탐색
 * 숫자를 단순히 옮겨 오름차순을 만들어야하므로 N - 최장부분수열
 */


public class Test2631 {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		dp = new int[N];
		
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int lis = 0;
		for(int i = 0; i<N; i++) {
			int index = binarySearch(0,lis,arr[i],lis+1);
			//System.out.println(index);
			// 추가할 요소가 현재 최장길이 수열 중 가장 큰 요소라면
			if(index == -1) {
				dp[lis++] = arr[i];
			}
			else {
				dp[index] = arr[i];
			}
		}
		System.out.println(N-lis);
	}
	
	static int binarySearch(int start, int end, int value, int size) {
	
		// Lower bound
		while(start <= end) {
			int mid = (start+end)/2;
			
			// dp배열에 있는 요소가 추가할 요소보다 크거나 같다면 앞부분 탐색
			if(dp[mid] >= value) {
				end = mid - 1;
			}
			
			else {
				start = mid + 1;
			}
		}
		
		// dp 배열 중에 추가할 요소가 가장 크다면
		if(start == size) {
			return -1;
		}
		else {
			return end+1;
		}
	}

}
