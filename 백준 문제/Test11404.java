package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 플루이드-와샬
 */

public class Test11404 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] graph = new int[n+1][n+1];
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=n; j++){
				if(i==j) graph[i][j] = 0;
				else graph[i][j] = Integer.MAX_VALUE;
			}
		}
			
		for(int i = 0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start][end] = Math.min(cost, graph[start][end]);
		}
		
		for(int k = 1; k<=n; k++) { //경유 지점
			for(int i = 1; i<=n; i++) { //출발 지점
				if(i == k) continue;
				for(int j = 1; j<=n; j++) { //도착 지점
					if(i == j || k == j) continue;
					if(graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		for(int i = 1; i<=n; i++) {
			for(int j = 1; j<=n; j++) {
				if(graph[i][j] == Integer.MAX_VALUE)
					graph[i][j] = 0;
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
