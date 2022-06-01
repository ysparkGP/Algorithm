package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * tsp(외판원 순회 문제)
 */

public class Test14590 {

	static boolean[][] graph;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				int win = Integer.parseInt(st.nextToken());
				graph[i][j] = win == 1? true: false;
			}
		}
		dp = new int[N][1<<N];
		for(int i = 0; i<N; i++) Arrays.fill(dp[i], -1);
		tsp(0,(1<<0), N);
		
		System.out.println(dp[0][1<<0]+1);
		System.out.print("1 ");
		tracking(0,(1<<0), N);
	}

	static int tsp(int cur, int visited, int N) {
		if(dp[cur][visited] != -1) return dp[cur][visited];
		
		dp[cur][visited] = 0;
		for(int i = 0; i<N; i++) {
			if((visited & (1<<i)) == 0 && graph[cur][i]) {
				dp[cur][visited] = Math.max(dp[cur][visited], tsp(i, visited|(1<<i), N)+1);
			}
		}
		return dp[cur][visited];
	}
	
	static void tracking(int cur, int visited, int N) {
		for(int i = 0; i<N; i++) {
			if((visited & (1<<i)) == 0 && graph[cur][i]) {
				if(dp[cur][visited] == dp[i][visited | (1<<i)] + 1) {
					System.out.print(i+1+" ");
					tracking(i, visited|(1<<i), N);
					break;
				}
			}
		}
	}
}
