package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * dp(top-down)
 */

public class Test17040 {

	static int N;
	static int[][] dp;
	static final int maxValue = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] costs = new int[N][3];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<3; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][3];
		int minValue = Integer.MAX_VALUE;
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<N; j++) Arrays.fill(dp[j], -1);
			
			int value = dpRGB(costs, 0, i, i);
			minValue = Math.min(minValue, value);
			
		}
		System.out.println(minValue);
	}
	
	static int dpRGB(int[][] costs, int cnt, int index, int start) {
		if(cnt == N-1) {
			if(start == index) return maxValue;
			return costs[cnt][index];
		}
		
		if(dp[cnt][index] != -1) return dp[cnt][index];
		
		dp[cnt][index] = maxValue;
		for(int i = 0; i<3; i++) {
			if(index == i) continue;
			dp[cnt][index] = Math.min(dp[cnt][index], costs[cnt][index] + dpRGB(costs, cnt+1, i, start));
			
		}
		
		return dp[cnt][index];
	}

}
