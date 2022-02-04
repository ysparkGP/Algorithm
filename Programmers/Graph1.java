package Programmers;

import java.io.IOException;

public class Graph1 {
	static int n = 5;
	static int[][] edge = {
			//{3,6},
			{4,3},
			{3,2},
			{1,3},
			{1,2},
			{2,4},
			{5,2}
	};
	
	public static void main(String[] args) throws IOException{
		int answer = 0;
		
		int[][] Distance = new int[n+1][n+1];
		for(int i = 0; i<edge.length; i++) {
			Distance[edge[i][0]][edge[i][1]] += 1;
			Distance[edge[i][1]][edge[i][0]] += 1;
		}
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=n; j++) {
				if(i != j && Distance[i][j] == 0) {
					Distance[i][j] = Integer.MAX_VALUE/2;
				}
			}
		}
		
		int[] dp = new int[n+1];
		for(int i = 0; i<n+1; i++) {
			dp[i] = Integer.MAX_VALUE/2;
		}
		
		boolean[] visit = new boolean[n+1];
		int startNode = 1;
		dp[startNode] = 0;
		
		for(int i = 0; i<n; i++) {
			
			int checkNode = 0;
			int checkDistance = Integer.MAX_VALUE;
			for(int j = 1; j<=n; j++) {
				if(!visit[j] && checkDistance > dp[j]) {
					checkDistance = dp[j];
					checkNode = j;
				}
			}
			
			visit[checkNode] = true;
			for(int j = 1; j<=n; j++) {
				if(dp[j] > dp[checkNode] + Distance[checkNode][j]) {
					dp[j] = dp[checkNode] + Distance[checkNode][j];
				}
			}
		}
		int value = 0;
		for(int i = 1; i<=n; i++) {
			System.out.print(dp[i] + " ");
			if(value < dp[i]) {
				value = dp[i];
				answer = 1;
			}
			else {
				answer++;
			}
		}
		System.out.println();
		System.out.println(answer);
			
	}
}