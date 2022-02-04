import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*µ¿Àû°èÈ¹¹ý
Top-Down: Àç±Í
Bottom-Up: ¹Ýº¹¹®
*/
public class Test1149 {
	
	static int N;
	static int[][] rgb;
	static int[][] dp;
	static int Red =  0;
	static int Blue = 1;
	static int Green = 2;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		rgb = new int[N][3];
		dp = new int[N][3];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = rgb[i][j];
			}
		}
		
		/*dp[0][Red] = rgb[0][Red];
		dp[0][Green] = rgb[0][Green];
		dp[0][Blue] = rgb[0][Blue];*/
		
		for(int i = 1; i<N; i++) {
			for(int j = 0; j<3; j++) {
				dp[i][Red] = rgb[i][Red] + Math.min(dp[i-1][Blue], dp[i-1][Green]);
				dp[i][Green] = rgb[i][Green] + Math.min(dp[i-1][Red], dp[i-1][Blue]);
				dp[i][Blue] = rgb[i][Blue] + Math.min(dp[i-1][Red], dp[i-1][Green]);
			}
		}
		
		System.out.println(Math.min(dp[N-1][Red], Math.min(dp[N-1][Green], dp[N-1][Blue])));
		//int cost = Math.min(paint(N-1, Red), Math.min(paint(N-1, Green), paint(N-1, Blue)));
		//System.out.println(cost);
	}
	
	static int paint(int n, int color) {
		
		if(n > 0) {
			if(color == Red) {
				dp[n][color] = Math.min(paint(n-1,Green), paint(n-1,Blue)) + rgb[n][color];
			}
			
			else if(color == Green) {
				dp[n][color] = Math.min(paint(n-1, Red), paint(n-1,Blue)) + rgb[n][color];
			}
			
			else if(color == Blue) {
				dp[n][color] = Math.min(paint(n-1, Red), paint(n-1, Green)) + rgb[n][color];
			}
		}
		
		return dp[n][color];
	}
}
