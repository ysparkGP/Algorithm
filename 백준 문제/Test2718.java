package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * DP + BitMasking
 */

public class Test2718 {

	static long[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		dp = new long[1001][15];
		dp[0][0] = 1;
		dp[1][0] = 1;
		while(T>0) {
			T--;
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp(N,0));
		}
		
	}
	
	static long dp(int n, int state) {
		
		if(n<0) return 0;
		
		if(dp[n][state] != 0) return dp[n][state];
		
		if(state == 0) {
			return dp[n][state] = dp(n-1,0) + dp(n-2,0) +  dp(n-1,3) + dp(n-1,9) + dp(n-1,12);
		}
		else if(state == 3) {
			return dp[n][state] = dp(n-1,0) + dp(n-1, 12);
		}
		else if(state == 6) {
			return dp[n][state] = dp(n-1, 9);
		}
		else if(state == 9) {
			return dp[n][state] = dp(n-1,0) + dp(n-1,6);
		}
		else {
			return dp[n][state] = dp(n-1,0) + dp(n-1,3);
		}
	}

}
