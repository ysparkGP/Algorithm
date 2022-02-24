package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP (부분합, 점화식)
 */

public class Test1915 {

	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n+1][m+1];
		for(int i = 1; i<=n; i++) {
			String str = br.readLine();
			for(int j = 1; j<=m; j++) {
				//arr[i][j] = str.charAt(j-1) - '0';
				arr[i][j] = Character.getNumericValue(str.charAt(j-1));
			}
		}
		
		int[][] pSum = new int[n+1][m+1];
		// 부분합 사각형 만들기 pSum[i][j] = pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1] + arr[i][j]
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=m; j++) {
				pSum[i][j] = pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1] + arr[i][j];
			}
		}
		
		int result = 0;
		int[][] dp = new int[n+1][m+1];
		// 부분합 사각형에서 정사각형 도출하기(2792ms), 점화식 DP(408ms)
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=m; j++) {
				/*
				if(arr[i][j] == 1) {
					int size = 1;
					
					
					// 1 2
					// 3 4
					// => 4 - 2 - 3 + 1
					
					while(true) {
						if(i+size > n || j+size > m) break;
						
						int square = pSum[i+size][j+size] - pSum[i-1][j+size] - pSum[i+size][j-1] + pSum[i-1][j-1];
						
						if(square != (size+1)*(size+1)) {
							break;
						}
						size++;
					}
					
					result = Math.max(result, size*size);
				}*/
				
				// 정사각형은 박스 안이 모두 1이어야하므로 하나씩 확인해보면서 좌, 좌상, 상 의 영향을 확인해봐야함
				
				// 시작점은 좌, 좌상, 상의 영향을 받지않음
				if(i==1 && j==1) {
					dp[i][j] = arr[i][j];
					result = dp[i][j]*dp[i][j];
				}
				
				// 박스가 1이라면 좌, 좌상, 상을 확인... 
				// 최솟값에 영향을 받음
				else {
					if(arr[i][j] == 1) {
						dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j])) + 1;
						result = Math.max(result, dp[i][j] * dp[i][j]);
					}
					else {
						dp[i][j] = 0;
					}
				}
			}
		}
		System.out.println(result);
	}

}
