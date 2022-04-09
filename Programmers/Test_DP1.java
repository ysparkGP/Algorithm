package programmers;

import java.util.Arrays;

/*
 * DP
 */

public class Test_DP1 {

	static int[][] dp;
	public static void main(String[] args) {
		int[][] triangle = {
				{7},
				{3,8},
				{8,1,0},
				{2,7,4,4},
				{4,5,2,6,5}
		};
		/*
		int[][] dp = new int[triangle.length][triangle.length];
		dp[0][0] = triangle[0][0];
		int max = 0;
		for(int i = 1; i < triangle.length; i++) {
			for(int j = 0; j < i+1; j++) {
				if(j == 0) {
					dp[i][j] = triangle[i][j] + dp[i-1][j];
				}
				else if(j == i) {
					dp[i][j] = triangle[i][j] + dp[i-1][j-1];
				}
				else {
					dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
		*/
		
		dp = new int[triangle.length][triangle.length];
		for(int i = 0; i<triangle.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dynamic(0,0,triangle));
	}
	
	static int dynamic(int step, int index, int[][] triangle) {
		if(step >= triangle.length-1) return triangle[step][index];
		
		else if(dp[step][index] != -1) return dp[step][index];
		
		// 메모이제이션
		return dp[step][index] = triangle[step][index] + Math.max(dynamic(step+1, index, triangle),dynamic(step+1, index+1, triangle));
	}
	
}
