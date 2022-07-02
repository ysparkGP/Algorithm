package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp, 비트마스킹
 */

public class Test9527 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long[] dp = new long[55];
		dpSet(dp);
		System.out.println(oneCount(dp, b) - oneCount(dp,a-1));
		
	}
	public static void dpSet(long[] dp) {
		dp[0] = 1;
		for(int i = 1; i<55; i++) {
			dp[i] = dp[i-1]*2 + (1L<<i);
		}
	}
	public static long oneCount(long[] dp, long x) {
		long answer = 0L;
		
		for(int i = 54; i>0; i--) {
			if((x & (1L<<i)) > 0) {
				answer += dp[i-1] + (x-(1L<<i)+1);
				x -= (1L<<i);
			}
		}
		answer += (x & 1);
		
		return answer;
	}

}
