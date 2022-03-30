package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 네트워크 유량의 특징
 * 1. 유량의 제한 = f(a,b) <= c(a,b)
 * 2. 유량의 보존 = a-b-c -> f(a,b) == f(b,c)
 * 3. 유량의 대칭 = f(a,b) == -f(b,a)  ==> 네트워크 유량의 핵심 아이디어 역간선
 * 최대 유량, 포드 풀커슨 알고리즘
 */

public class Test6086 {

	static final int start = 'A'-65;
	static final int end = 'Z' - 65;
	static final int V = 52;
	static boolean[] visit;
	static int[][] flow;
	static int[][] capacity;
	static int[] path;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new ArrayList[V];
		for(int i = 0; i<V; i++) {
			graph[i] = new ArrayList<>();
		}
		capacity = new int[V][V];
		flow = new int[V][V];
		path = new int[V];
		visit = new boolean[V];
		Arrays.fill(path, -1);
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int s = atoi(st.nextToken().charAt(0));
			int e = atoi(st.nextToken().charAt(0));
			int c = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);
			capacity[s][e] += c;
			capacity[e][s] += c;
		}
		
		System.out.println(fordFulkerson());
	}
	
	static int fordFulkerson() {
		int result = 0;
		
		while(dfs(start)) {
			
			int max = Integer.MAX_VALUE;
			// 최대 유량 찾기
			for(int i = end; i != start; i = path[i]) {
				int to = i;
				int from = path[i];
				max = Math.min(max, capacity[from][to] - flow[from][to]);
			}
			
			// 최대 유량 적용
			for(int i = end; i != start; i = path[i]) {
				int to = i;
				int from = path[i];
				flow[from][to] += max;
				flow[to][from] -= max;
			}
			
			Arrays.fill(visit, false);
			Arrays.fill(path, -1);
			
			result += max;
			//System.out.println("RESULT : " + result);
		}
		
		return result;
	}
	
	static boolean dfs(int x) {
		//System.out.println(x);
		if(x == end) return true;
		
		visit[x] = true;
		for(int next: graph[x]) {
			if(!visit[next] && capacity[x][next] - flow[x][next] > 0) {
				path[next] = x;
				if(dfs(next))
					return true;
			
			}
		}
		
		return false;
	}
	
	static int atoi(char c) {
		if('A' <= c && c <= 'Z') {
			return c-'A';
		}else if('a' <= c && c <= 'z'){
			return c-'A'-6;
		}
		return -1;
	}

}
