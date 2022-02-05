package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test2468 {
	
	static int[] cx = {0,0,1,-1};
	static int[] cy = {1,-1,0,0};
	static int N;
	static int max;
	static boolean[][] visit;
	static int[][] city;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		city = new int[N][N];
		visit = new boolean[N][N];
		
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			for(int j = 0; j<N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		for(int range = 0; range<=100; range++) {
			int safe = 0;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(range < city[i][j] && visit[i][j] == false) {
						safe++;
						dfs(i,j,range);
						
					}
				}
			}
			
			for(int i = 0; i<N; i++) {
				Arrays.fill(visit[i], false);
			}
			
			max = safe > max ? safe: max;
		}
		System.out.println(max);
	}
	
	static void dfs(int y, int x, int range) {
		visit[y][x] = true;
		
		for(int k = 0; k<4; k++) {
			int yCheck = cy[k] + y;
			int xCheck = cx[k] + x;
			if(validCheck(yCheck, xCheck)){
				if(city[yCheck][xCheck] > range && visit[yCheck][xCheck] == false) {
					visit[yCheck][xCheck] = true;
					dfs(yCheck, xCheck, range);
				}
			}
		}

	}
	
	
	
	static boolean validCheck(int y, int x) {
		
		if(x >= 0 && x < N && y >= 0 && y < N)
			return true;
		
		return false;
	}
}
