package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * TSP(Traveling Salesman Problem) 외판원 순회 문제
 * DP + BitMasking, 메모이제이션으로 성능 최적화
 *
 */

public class Test2098 {

	static int[][] dp;
	static int[][] map;
	static int maxBit, N;
	static int INF = 256000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxBit = (1 << N) - 1;
		
		map = new int[N][N];
		dp = new int[N][maxBit];
		for(int i = 0; i<N; i++)
			Arrays.fill(dp[i], INF);
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0;  j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0,1));
	}
	
	static int tsp(int now, int check) {
		if(check == maxBit) {
			if(map[now][0] == 0) return INF;
			else return map[now][0];
		}
		
		// Memoization
		if(dp[now][check] != INF) return dp[now][check];
		
		for(int i = 0; i<N; i++) {
			
			int nextCheck = check | (1<<i);
			// 길이 없거나, 방문한 도시라면
			if(nextCheck == check || map[now][i]==0) continue;
			dp[now][check] = Math.min(dp[now][check], map[now][i] + tsp(i,nextCheck));
		}
		
		return dp[now][check];
	}

}
