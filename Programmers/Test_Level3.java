package programmers;

/*
 * dp
 */

public class Test_Level3 {

	public static void main(String[] args) {
		int n = 4;
		long[] dp = new long[n+1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i<=n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
		}
		System.out.println(dp[n]);

	}

}
