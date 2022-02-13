package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플루이드 와샬
 */

public class Test10159 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] graph = new boolean[N+1][N+1];
		boolean[][] reverseGraph = new boolean[N+1][N+1];
		for(int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start][end] = true;
			reverseGraph[end][start] = true;
		}
		
		for(int k = 1; k<=N; k++) { // 경유 노드
			for(int i = 1; i<=N; i++) { // 출발 노드
				if(i == k) continue;
				for(int j = 1; j<=N; j++) { // 도착 노드
					if(i == j || j == k) continue;
					
					if(graph[i][k] && graph[k][j])
						graph[i][j] = true;
					if(reverseGraph[i][k] && reverseGraph[k][j])
						reverseGraph[i][j] = true;
				}
			}
		}
		
		int[]count = new int[N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(graph[i][j] || reverseGraph[i][j]) count[i]++;
			}
		}
		
		for(int i = 1; i<=N; i++) {
			for(int j =1 ; j<=N; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---");
		for(int i = 1; i<=N; i++) {
			for(int j =1 ; j<=N; j++) {
				System.out.print(reverseGraph[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 1; i<=N; i++)
			System.out.println(N-count[i]-1);
	}

}
