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
 * 이분매칭의 특성을 그리디?하게 활용
 * 최대유량(에드몬드 카프 알고리즘 => 시간초과, 순열을 사용한 방식때문인듯)
 */

public class Test11378 {

	//static ArrayList<Integer>[] graph;
	//static int[][] capacity;
	//static int[][] flow;
	//static int[] path;
	static int N,M,K;
	static int max;
	//static int sink;
	//static int source;
	static int[] match;
	static boolean[] visit;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); // 직원의 수
		M = Integer.parseInt(st.nextToken()); // 일의 수
		K = Integer.parseInt(st.nextToken()); // 벌점의 합
		
		visit = new boolean[M+1];
		match = new int[M+1];
		graph = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			graph[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine()," ");
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j<cnt; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int answer = 0;
		for(int i = 1; i<=N; i++) {
			Arrays.fill(visit, false);
			if(dfs(i)) answer++;
		}
		
		for(int i = 1; i<=N; i++) {
			while(K>0) {
				Arrays.fill(visit, false);
				if(!dfs(i)) break;
				answer++;
				K--;
			}
		}
		System.out.println(answer);
		/*source = 0;
		sink = N+M+1;
		max = 0;
		
		capacity = new int[N+M+2][N+M+2];
		flow = new int[N+M+2][N+M+2];
		path = new int[N+M+2];
		graph = new ArrayList[N+M+2];
		
		for(int i =0; i<=N+M+1; i++) graph[i] = new ArrayList<>();
		
		// source 연결 작업
		for(int i = 1; i<=N; i++) {
			graph[source].add(i);
			graph[i].add(source);
			capacity[source][i] = 1;
		}
		
		// sink 연결 작업
		for(int i = N+1; i<=N+M+1; i++) {
			graph[sink].add(i);
			graph[i].add(sink);
			capacity[i][sink] = 1;
		}
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j<cnt; j++) {
				int job = N + Integer.parseInt(st.nextToken());
				graph[i].add(job);
				graph[job].add(i);
				capacity[i][job] = 1;
			}
		}*/
		
		//int[] arr = new int[N+1];
		//permutation(K, arr);
		
		//System.out.println(max);
	}
	static boolean dfs(int from) {
		
		for(Integer to: graph[from]) {
			if(!visit[to]) {
				visit[to] = true;
				if(match[to] == 0 || dfs(match[to])) {
					match[to] = from;
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	static void permutation(int cnt, int[] arr) {
		if(cnt == 0) {
			for(int i = 1; i<arr.length; i++) {
				capacity[source][i] += arr[i];
			}
			// 플로우값 초기화 해주는 로직 추가해야함
			// 하지만 추가를 안해도 시간초과
			max = Math.max(max,edmondsKarp());
			for(int i = 1; i<arr.length; i++) {
				capacity[source][i] -= arr[i];
			}
			return;
		}
		
		for(int i = 1; i<arr.length; i++) {
			arr[i]+=1;
			permutation(cnt-1, arr);
			arr[i]-=1;
		}
	}
	
	static boolean bfs() {
		Arrays.fill(path, -1);
		Queue<Integer> que = new LinkedList<>();
		que.add(source);
		
		while(!que.isEmpty()) {
			int from = que.poll();
			ArrayList<Integer> list = graph[from];
			
			for(int i = 0; i<list.size(); i++) {
				int to = list.get(i);
				if(path[to] == -1 && capacity[from][to] - flow[from][to] > 0) {
					path[to] = from;
					que.add(to);
					if(to == sink) break;
				}
			}
		}
		if(path[sink] == -1) return false;
		
		return true;
	}
	
	static int edmondsKarp() {
		int total = 0;
		while(bfs()) {
			// 최대 유량 찾기
			int flowNum = Integer.MAX_VALUE;
			for(int i = sink; i != source; i = path[i]) {
				int to = i;
				int from = path[i];
				
				flowNum = Math.min(capacity[from][to] - flow[from][to], flowNum);
			}
			
			// 유량 흘리기
			for(int i = sink; i!= source; i = path[i]) {
				int to = i;
				int from = path[i];
				
				flow[from][to] += flowNum;
				flow[to][from] -= flowNum;
			}
			
			total+=flowNum;
		}
		
		return total;
	}
	*/

}
