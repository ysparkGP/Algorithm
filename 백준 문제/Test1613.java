package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀루이드 와샬
 */

public class Test1613 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[][] graph = new boolean[n+1][n+1];
		boolean[][] reverseGraph = new boolean[n+1][n+1];
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start][end] = true;
			reverseGraph[end][start] = true;
		}
		
		for(int k = 1; k<=n; k++) {
			for(int i = 1; i<=n; i++) {
				if(i == k) continue;
				for(int j = 1; j<=n; j++) {
					if(i == j || k == j) continue;
					
					if(graph[i][k] && graph[k][j]) 
						graph[i][j] = true;
					
					if(reverseGraph[i][k] && reverseGraph[k][j])
						reverseGraph[i][j] = true;
				}
			}
		}
		
		int s = Integer.parseInt(br.readLine());
		for(int i = 0; i<s; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(graph[start][end])
				System.out.println(-1);
			else if(reverseGraph[start][end])
				System.out.println(1);
			else
				System.out.println(0);
		}
		
		
	}

}
