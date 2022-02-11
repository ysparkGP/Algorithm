package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라
 */

class Node{
	int x;
	int cost;
	Node(int x, int cost){
		
		this.x = x;
		this.cost = cost;
	}
}

public class Test1504 {
	static ArrayList<ArrayList<Node>> graph;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); //정점의 개수
		int E = Integer.parseInt(st.nextToken()); //간선의 개수
		
		graph = new ArrayList<>();
		for(int i = 0; i<=N; i++)
			graph.add(new ArrayList<Node>());
		
		for(int i = 0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v,cost));
			graph.get(v).add(new Node(u,cost));
		}
		st = new StringTokenizer(br.readLine()," ");
		int transfer1 = Integer.parseInt(st.nextToken());
		int transfer2 = Integer.parseInt(st.nextToken());
		// 1 -> v1 -> v2 -> N
		long result1 = 0;
		result1 += dijkstra(1,transfer1);
		result1 += dijkstra(transfer1, transfer2);
		result1 += dijkstra(transfer2, N);
		
		
		// 1 -> v2 -> v1 -> N
		long result2 = 0;
		result2 += dijkstra(1,transfer2);
		result2 += dijkstra(transfer2, transfer1);
		result2 += dijkstra(transfer1, N);
		
		if(result1 >= Integer.MAX_VALUE || result2 >= Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Math.min(result1, result2));
		
	}
	static int dijkstra(int v1, int v2) {
		int[] cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		que.add(new Node(v1,0));
		cost[v1] = 0;
		while(!que.isEmpty()) {
			/* 노드가 선택된 것은 우선순위 큐에 의해서 오름차순 비용으로 정렬된 큐에서 뽑은 것임
			 * 즉, 현재 노드에서 가장 최솟값을 뽑은 것이나 마찬가지이므로 visit같은 배열처리가 따로 필요없음
			 */
			Node curNode = que.poll();
			if(curNode.x == v2) { // v1(시작 정점)에서 v2(목표 정점)까지 도달했다면 비용 반환
				return curNode.cost;
				
			}
			if(curNode.cost > cost[curNode.x]) continue; //뽑은 노드 비용이 이미 갱신된 비용보다 더 클 때, (후반 처리)
			//System.out.println("d");
			for(int i = 0; i<graph.get(curNode.x).size(); i++) { // 인접 노드 탐색
				Node adjNode = graph.get(curNode.x).get(i);
				if(cost[adjNode.x] > curNode.cost + adjNode.cost) {
					cost[adjNode.x] = curNode.cost + adjNode.cost;
					que.add(new Node(adjNode.x, cost[adjNode.x]));
				}
			}
		}
		//System.out.println(v1 + ", " + v2);
		return Integer.MAX_VALUE; //도달못했으면 최대치 반환
	}

}
