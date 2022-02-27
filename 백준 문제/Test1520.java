package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS + DP
 * DFS 만 사용하여 풀 경우 N과M이 최대 500이므로, 최악의 경우의 수는 4^(500*500)으로 풀릴 수 없다.
 * DP를 이용한다.
 * DFS로 탐색하는데, 경로를 DP에 저장해둔다.
 * 만약 (3,3)지점에서 (5,5)지점까지 갈 경우의 수를 찾았다면, 이 길은 더이상 탐색해도 될 필요가 없지만
 * 경로의 개수를 저장해두지 않는다면, 또 저 구간을 탐색할 것이다.
 * 따라서 한 번 탐색한 지역은 경로를 저장하고, 다른 지역에서 탐색이 되었을 때는 저장한 경로를 더해주는 방식으로 풀어야한다.
 */

public class Test1520 {

	static int M,N;
	static int[] cx = {0,0,-1,1};
	static int[] cy = {-1,1,0,0};
	static int[][] dp;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken()); // 세로
		N = Integer.parseInt(st.nextToken()); // 가로
		
		map = new int[M][N];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[M][N];
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<N; j++) {
				dp[i][j] = -1;
			}
		}
		dfs(0,0);
		
		System.out.println(dp[0][0]);
	}

	static int dfs(int y, int x) { // DP + DFS
		
		if(y == M-1 && x == N-1) {
			return 1;
		}
		else if(dp[y][x] != -1) {
			return dp[y][x];
		}
		
		dp[y][x] = 0;
		for(int i = 0; i<4; i++) {
			int ny = y+cy[i];
			int nx = x+cx[i];
			
			if(!boundCheck(ny,nx)) continue;
			if(map[y][x] <= map[ny][nx]) continue;
			
			dp[y][x] += dfs(ny,nx);
		}
		
		return dp[y][x];
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || x < 0 || y >= M || x >= N) return false;
		return true;
	}
}
