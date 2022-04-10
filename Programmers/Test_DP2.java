package programmers;

/*
 * DP
 */

public class Test_DP2 {

	public static void main(String[] args) {
		int m = 4; // 가로크기
		int n = 3; // 세로크기
		int[][] puddles = {{2,2}};
		int[][] dp = new int[n+1][m+1];
		dp[1][1] = 1;
		
		for(int i = 0; i<puddles.length; i++) {
			dp[puddles[i][1]][puddles[i][0]] = -1;
		}
		
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=m; j++) {
				if(i==1 && j==1) continue;
				/*
				boolean tf = false;
				for(int k = 0; k<puddles.length; k++) {
					if(puddles[k][1] == i && puddles[k][0] == j) {
						tf = true;
						break;
					}
				}
				if(tf) dp[i][j] = 0;
				
				*/
				if(dp[i][j] == -1) dp[i][j] = 0;
				else dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000007;
			}
		}
		System.out.println(dp[n][m]);
	}

}
