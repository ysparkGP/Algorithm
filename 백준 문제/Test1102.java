package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * tsp 와 비슷한 DP + BitMasking
 */
public class Test1102 {
	static int N,M;
	static int[][] map;
	static int[][] dp;
	static int maxBit;
	static int MAX = 9217;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxBit = (1<<N) - 1;
		dp = new int[N][maxBit];
		for(int i = 0; i<N; i++) {
			Arrays.fill(dp[i], MAX);
		}
		String state = br.readLine();
		M = Integer.parseInt(br.readLine());
		
		int nowBit = 0;
		int cnt = 0;
		for(int i = state.length()-1; i>=0; i--) {
			nowBit = nowBit << 1;
			if(state.charAt(i) == 'Y') {
				nowBit++;
				cnt++;
			}	
		}
		if(cnt >= M) {
			System.out.println(0);
			return;
		}
		else if(cnt <= 0) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(tsp(cnt,nowBit));
		
	}
	static int tsp(int cnt, int check) {
		if(cnt >= M) return 0;
		if(dp[cnt][check] != MAX) return dp[cnt][check]; // 메모이제이션
		
		for(int i = 0; i<N; i++) { // 선택한 발전기
			
			if((check & (1<<i)) != (1<<i)) continue; //선택한 발전기가 꺼져있을 때, skip
			
			for(int j = 0; j<N; j++) { // 선택한 발전기로 킬 발전기
				
				if((check & (1<<j)) == (1<<j) || i==j) continue; // 킬 발전기가 선택한 발전기와 같거나 이미 켜져있을 때, skip
				int nextCheck = check | (1<<j);
				dp[cnt][check] = Math.min(dp[cnt][check], tsp(cnt+1, nextCheck) + map[i][j]);
			}
		}
		
		return dp[cnt][check];
	}

}
