package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * tsp
 */

public class Test2098 {

	static int[][] graph;
	static int[][] dp;
	static int N;
	final static int noWay = 17_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0) graph[i][j] = noWay;
			}
		}
		dp = new int[N][1<<N];
		for(int i = 0; i<N; i++)
			Arrays.fill(dp[i], -1);
		
		
		System.out.println(tsp(0,1<<0));
	}
	
	public static int tsp(int cur, int bit) {
		if(dp[cur][bit] != -1) return dp[cur][bit];
		if(bit == (1<<N)-1) return graph[cur][0];
		
		dp[cur][bit] = noWay;
		for(int i = 0; i<N; i++) {
			if((bit & (1<<i)) > 0 || graph[cur][i] == noWay) continue;
			dp[cur][bit] = Math.min(dp[cur][bit], tsp(i, bit|(1<<i)) + graph[cur][i]);
		}
		
		return dp[cur][bit];
	}

}
