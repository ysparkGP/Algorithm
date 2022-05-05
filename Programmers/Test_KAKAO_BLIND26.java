package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 2021 KAKAO BLIND RECRUITMENT
 * 다익스트라
 * 합승 택시 요금
 */

public class Test_KAKAO_BLIND26 {

	static class Node{
		int target;
		int cost;
		public Node(int target, int cost) {
			this.target = target;
			this.cost = cost;
		}
	}
	public static void main(String[] args) {
		int n = 6;
		int s = 4;
		int a = 5;
		int b = 6;
		int[][] fares = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};
		List<Node>[] graph = new ArrayList[n+1];
		for(int i = 1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i<fares.length; i++) {
			int start = fares[i][0];
			int target = fares[i][1];
			int cost = fares[i][2];
			graph[start].add(new Node(target,cost));
			graph[target].add(new Node(start,cost));
		}
		
		int[] sDist = dijkstra(s,n,graph);
		int[] aDist = dijkstra(a,n,graph);
		int[] bDist = dijkstra(b,n,graph);
		
		// s->x + x->a + x->b 의 최솟값 구하기
		int answer = Integer.MAX_VALUE;
		for(int x = 1; x<=n; x++) {
			int temp = sDist[x] + aDist[x] + bDist[x];
			answer = Math.min(temp,answer);
		}
		System.out.println(answer);
	}
	
	
	static int[] dijkstra(int start, int n, List<Node>[] graph) {
		
		int[] dis = new int[n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return Integer.compare(n1.cost, n2.cost);
			}
		});
		que.add(new Node(start, 0));
		while(!que.isEmpty()) {
			Node now = que.poll();
			if(now.cost > dis[now.target]) continue;
			
			for(int i = 0; i<graph[now.target].size(); i++) {
				Node next = graph[now.target].get(i);
				if(dis[next.target] > dis[now.target] + next.cost) {
					dis[next.target] = dis[now.target] + next.cost;
					que.add(new Node(next.target, dis[now.target] + next.cost));
				}
			}
		}
		
		return dis;
	}

}
