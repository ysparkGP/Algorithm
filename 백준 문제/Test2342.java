package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * dp(top-down, bottom-up)
 */

public class Test2342 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		List<Integer> list = new ArrayList<>();
		while(true) {
			int value = Integer.parseInt(st.nextToken());
			if(value == 0) break;
			list.add(value);
		}
		int[][][] dp = new int[list.size()][5][5]; // i번째일때, 왼발과 오른발의 위치 이동점수 최솟값
		/*
		for(int i = 0; i<list.size(); i++) {
			for(int j = 0; j<5; j++) {
				for(int k = 0; k<5; k++) {
					dp[i][j][k] = 99999;
				}
			}
		}
		// bottom-up
		dp[0][0][0] = 0;
		for(int i = 0; i<list.size()-1; i++) {
			int next = list.get(i);
			for(int j = 0; j<5; j++) { // 왼발
				for(int k = 0; k<5; k++) { // 오른발
					int now = dp[i][j][k];
					
					if(next != k)
						dp[i+1][next][k] = Math.min(dp[i+1][next][k], now + calcScore(j,next)); // 왼발이 움직였을때
					if(next != j)
						dp[i+1][j][next] = Math.min(dp[i+1][j][next], now + calcScore(k,next)); // 오른발이 움직였을때
				}
			}
		}
		
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				min = Math.min(min, dp[list.size()-1][i][j]);
			}
		}
		*/
		System.out.println(DDR(list, dp, 0, 0, 0));
	}
	
	// top-down
	public static int DDR(List<Integer> list, int[][][] dp, int step, int left, int right) {
		if(list.size() == step) return 0;
		if(dp[step][left][right] != 0) return dp[step][left][right];
		
		int leftStep = Integer.MAX_VALUE;
		int rightStep = Integer.MAX_VALUE;
		int next = list.get(step);
		if(next != right)
			leftStep = DDR(list, dp, step+1, next, right) + calcScore(left, next);
		if(next != left)
			rightStep = DDR(list, dp, step+1, left, next) + calcScore(right, next);
		
		dp[step][left][right] = Math.min(leftStep, rightStep);
		
		return dp[step][left][right];
	}
		
		
	public static int calcScore(int foot, int target) {
		if(foot == 0) return 2;
		
		if(foot == target) return 1;
		else if((foot+target) % 2 == 1) return 3;
		else return 4;
	}

}
