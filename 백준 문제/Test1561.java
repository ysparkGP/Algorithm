package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이분탐색, 파라메트릭 서치
 */

public class Test1561 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[M];
		for(int i = 0; i<M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N <= M) {
			System.out.println(N);
		}
		else {
			int number = binarySearch(arr, N-M);
			System.out.println(number);
		}
		return;
	}
	
	static int binarySearch(int[] arr, long N) {
		long left = 1;
		long right = 60_000_000_000L;
		while(left < right) {
			long mid = (left + right) / 2;
			long value = playEnd(arr, mid);
			
			if(value >= N) {
				right = mid;
			}
			else {
				left = mid + 1;
			}
		}
		
		int result = 0;
		long last = N - playEnd(arr, right-1);
		//System.out.println(right+": " +playEnd(arr, right)+ ", " + (right-1) + ": " + playEnd(arr, right-1));
		for(int i = 0; i<arr.length; i++) {
			if(right % arr[i] == 0) last-=1;
			if(last == 0) {
				result = i+1;
				break;
			}
		}
		return result;
	}
	static long playEnd(int[] arr, long time) {
		long cnt = 0;
		for(int i = 0; i<arr.length; i++) {
			cnt += (time/arr[i]);
		}
		return cnt;
	}

}
