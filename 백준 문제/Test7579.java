package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp, 배낭 문제
 */

public class Test7579 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[N];
		int[] cost = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N+1][10001];
		int answer = Integer.MAX_VALUE;
		for(int i = 1; i<=N; i++) {
			for(int j = 0; j<=10000; j++) {
				if(cost[i-1] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i-1]] + memory[i-1]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
				if(dp[i][j]>=M) {
					answer = Math.min(answer, j);
				}
			}
		}
		
		System.out.println(answer);

	}

}
