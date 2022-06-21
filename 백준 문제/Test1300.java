package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 이분탐색, 파라메트릭 서치
 */

public class Test1300 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long K = Long.parseLong(br.readLine());
		
		long left = 1;
		long right = K;
		while(left < right) {
			long mid = (left+right)/2;
			long count = cnt(mid, N);
			if(count >= K) {
				right = mid;
			}
			else{
				left = mid + 1;
			}
		}
		System.out.println(right);
	}
	public static long cnt(long mid, long N) {
		long count = 0;
		for(int i = 1; i<=N; i++) {
			count += Math.min(mid/i, N);
		}
		return count;
	}

}
