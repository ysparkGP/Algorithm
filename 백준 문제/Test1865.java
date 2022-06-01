package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 벨만 포드
 * 최단 거리가 아닌 음의 사이클을 형성하는지 구하는 문제
 * 그러므로 단절된 경로 죽, dist[from] != Integer.maxValue 일 때만 거리를 최적화하는 것이 아닌
 * 적절한 값으로 초기화를 해준 후 모든 경로를 확인한다.
 * ex)
 * 3 2 1
 * 2 3 -2
 */

public class Test1865 {

	static class Edge{
		int from;
		int to;
		int time;
		Edge(int from, int to, int time){
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}
	static final int INF = 9_876_543_21;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			List<Edge> graph = new ArrayList<>();
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				graph.add(new Edge(from, to, time));
				graph.add(new Edge(to, from, time));
			}
			for(int i = 0; i<W; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				graph.add(new Edge(from, to, -time));
			}
			boolean answer = false;
			if(bellmanFord(1,graph,N)) answer = true;
			sb.append(answer? "YES\n": "NO\n");
		}
		
		System.out.println(sb.toString());
	}
	static boolean bellmanFord(int start, List<Edge> graph, int N) {
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		for(int i = 1; i<=N; i++) {
			for(int j = 0; j<graph.size(); j++) {
				Edge edge = graph.get(j);
				int from = edge.from;
				int to = edge.to;
				int time = edge.time;
				
				if(dist[to] > dist[from] + time) {
					if(i == N) return true;
					dist[to] = dist[from] + time;
				}
			}
		}
		
		return false;
	}

}
