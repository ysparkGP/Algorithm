package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS
 */

public class Test9446 {

	static int N;
	static int[] graph;
	static boolean[] visit;
	static boolean[] finish;
	static int res;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N+1];
			visit = new boolean[N+1];
			finish = new boolean[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i<=N; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			res = 0;
			for(int i = 1; i<=N; i++) {
				dfs(i);
			}
			sb.append(N-res + "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int num) {
		
		if(finish[num]) return;
		
		//System.out.println(num);
		if(!visit[num]) {
			visit[num] = true;
			dfs(graph[num]);
		}
		
		else {
			int next = graph[num];
			res++;
			while(next != num) {
				next = graph[next];
				res++;
			}
		}
		
		finish[num] = true;
		return;
	}

}
