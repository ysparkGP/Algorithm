package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 비트 마스킹, dfs
 */

public class Test_KAKAO_BLIND35 {

	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {
				{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}
		};
		List<Integer>[] graph = new ArrayList[info.length];
		for(int i = 0; i<info.length; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i<edges.length; i++) {
			int s = edges[i][0];
			int e = edges[i][1];
			graph[s].add(e);
			graph[e].add(s);
		}
		
		int[][] dp = new int[info.length][1<<info.length];
		for(int i = 0; i<info.length; i++) Arrays.fill(dp[i], -1);
		
		int max = dfs(info, graph, dp, 0, 1<<0);
		
		System.out.println(max);
	}
	static int dfs(int[] info, List<Integer>[] graph, int[][] dp, int cur, int visit) {
		if(dp[cur][visit] != -1) return dp[cur][visit];
		
		int wolf = 0;
		int sheep = 0;
		for(int i = 0; i<info.length; i++) {
			if((visit&(1<<i)) > 0) {
				if(info[i] == 0) sheep++;
				else if(info[i] == 1) wolf++;
			}
		}
		if(sheep - wolf <= 0) return dp[cur][visit] = 0;
		dp[cur][visit] = sheep;
		for(Integer next: graph[cur]) {
			dp[cur][visit]= Math.max(dp[cur][visit], dfs(info, graph, dp, next, visit|(1<<next)));
		}
		
		return dp[cur][visit];
	}
}

