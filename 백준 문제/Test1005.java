package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상 정렬 + DP
 */

public class Test1005 {	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t = 0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken()); //건물의 개수
			int K = Integer.parseInt(st.nextToken()); //건물순서 규칙 개수
			
			st = new StringTokenizer(br.readLine()," ");
			int[] buildTime = new int[N+1];
			for(int i = 1; i<=N; i++) {
				int time = Integer.parseInt(st.nextToken());
				buildTime[i] = time;
			}
			ArrayList<ArrayList<Integer>> buildGraph = new ArrayList<>();
			for(int i = 0; i<=N; i++)
				buildGraph.add(new ArrayList<>());
			int[] inDegree = new int[N+1];
			for(int k = 0; k<K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				buildGraph.get(start).add(end);
				inDegree[end]++;
			}
			
			int target = Integer.parseInt(br.readLine());
			int[] dpTime = new int[N+1];
			Queue<Integer> que = new LinkedList<>();
			//Queue<Integer> result = new LinkedList<>();
			for(int i = 1; i<=N; i++) {
				if(inDegree[i] == 0) {
					que.add(i);
					dpTime[i] = buildTime[i];
					
				}
				
			}
			
			while(!que.isEmpty()) {
				int node = que.poll();
				for(Integer i : buildGraph.get(node)) {
					inDegree[i]--;
					if(inDegree[i] == 0) {
						que.add(i);
					}
					dpTime[i] = Math.max(dpTime[i], dpTime[node] + buildTime[i]);
				}
			}
			System.out.println(dpTime[target]);
		}
		
		
	}

}
