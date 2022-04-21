package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 최대 유량, 네트워크 플로우
 */

public class Test17412 {
	
	static int[][] capacity;
	static int[][] flow;
	static int[] path;
//	static boolean[][] visit;
	static ArrayList<Integer> graph[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		capacity = new int[N+1][N+1];
		
		for(int i = 1; i<=N; i++) graph[i] = new ArrayList<>();
		for(int i = 0; i<P; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
			capacity[start][end] = 1;
		}
		flow = new int[N+1][N+1];
		path = new int[N+1];
		
		System.out.println(edmondsKarp());
	}
	
	static boolean bfs() {
		Arrays.fill(path, -1);
		Queue<Integer> que = new LinkedList<>();
		que.add(1); // Source
		
		while(!que.isEmpty()) {
			int from = que.poll();
			ArrayList<Integer> nexts = graph[from];
			
			for(int next: nexts) {
				if(path[next] == -1 && capacity[from][next] - flow[from][next] > 0) {
					path[next] = from;
					que.add(next);
					
					if(next == 2) break;
				}
			}
		}
		
		if(path[2] == -1) return false;
		
		return true;
	}
	
	static int edmondsKarp() {
		int total = 0;
		
		while(bfs()) {
			// flow 값 찾기
			/*int flowNum = Integer.MAX_VALUE;
			for(int i = 2; i!=1; i = path[i]) { //sink 에서부터 source로 (뒤->앞)
				int from = path[i];
				int to = i;
				flowNum = Math.min(flowNum, capacity[from][to] - flow[from][to]);
			}
			*/
			// flow 흘려 보내기, 역방향도 포함해서
			for(int i = 2; i!=1; i = path[i]) {
				int from = path[i];
				int to = i;
				
				flow[from][to] += 1; // 정방향 플로우
				flow[to][from] -= 1; // 역방향 플로우
			}
			
			total += 1;
		}
		
		return total;
	}

}
