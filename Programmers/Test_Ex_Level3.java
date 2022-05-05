package programmers;

/*
 * 2xn 타일링 
 * dp
 */

public class Test_Ex_Level3 {

	public static void main(String[] args) {
		int n = 5;
		int N = n+1;
		int[] dp = new int[N];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i<N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000_007;
		}
		int answer = dp[n];
		System.out.println(answer);

	}

}
