package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * TSP(Traveling Salesman Problem) 외판원 순회 문제ㅔ
 * DP + BitMasking, Memoization
 *
 */

public class Test16991 {

	static int N;
	static int maxBit;
	static int INF = 256000000;
	static double[][] dp;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxBit = (1<<N) - 1;
		
		dp = new double[N][maxBit];
		for(int i = 0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<2; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tps(0,1));
	}

	static double tps(int now, int check) {
		if(check == maxBit) { // 모든 도시를 방문했다면
			return calcDistance(map[now][0], map[now][1], map[0][0], map[0][1]);
		}
		if(dp[now][check] != INF) return dp[now][check];
		
		for(int i = 0; i<N; i++) {
			int nextCheck = check | (1<<i);
			// 방문한 도시라면
			if(nextCheck == check) continue;
			dp[now][check] = Math.min(dp[now][check], tps(i,nextCheck) + calcDistance(
					map[now][0], map[now][1], map[i][0], map[i][1]));
		}
		
		return dp[now][check];
	}
	
	static double calcDistance(int x1, int y1, int x2, int y2) {
		
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
}
