package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이분 탐색
 */

public class Test9007 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int k = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n*n];
			int[][] student = new int[4][n];
			for(int i = 0; i<4; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j = 0; j<n; j++) {
					student[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i<n; i++) {
				for(int j = 0; j<n; j++) {
					arr[i*n + j] = student[2][i] + student[3][j];
				}
			}
			Arrays.sort(arr);
			
			int min = Integer.MAX_VALUE;
			for(int i = 0; i<n; i++) {
				
				for(int j = 0; j<n; j++) {
					int sum = student[0][i] + student[1][j];
					int value = binarySearch(arr, k-sum);
					sum += value;
					if(Math.abs(sum-k) == Math.abs(min-k)) {
						if(sum < min) {
							min = sum;
						}
					}
					else {
						if(Math.abs(sum-k) < Math.abs(min-k)) {
							min = sum;
						}
					}
				}
			}
			sb.append(min+"\n");
			
		}
		System.out.println(sb.toString());
	}
	static int binarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		while(left < right) {
			int mid = (left+right)/2;
			if(arr[mid] <= target) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		
		if(right == 0) return arr[right];
		else if(right == arr.length) return arr[right-1];
		else {
			if(Math.abs(arr[right-1]-target) <= Math.abs(arr[right]-target)) {
				return arr[right-1];
			}
			else{
				return arr[right];
			}
		}
	}

}
