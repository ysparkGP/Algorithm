package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1932 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] tri = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j<=i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		if(N == 1) {
			System.out.println(tri[0][0]);
			return;
		}
		
		int max = -1;
		for(int i = 1; i<N; i++) {
		
			for(int j = 0; j<=i; j++) {
				
				if(j == 0) {
					tri[i][j] = tri[i-1][j] + tri[i][j];
				}
				
				else if(j == i) {
					tri[i][j] = tri[i-1][j-1] + tri[i][j];
				}
				else {
					tri[i][j] = Math.max(tri[i-1][j-1] + tri[i][j], tri[i-1][j] + tri[i][j]);
				}
				max = max < tri[i][j] ? tri[i][j] : max;
			}
		}
		
		System.out.println(max);
	}
}
