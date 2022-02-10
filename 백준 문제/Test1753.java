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
	int v;
	int cost;
	Node(int v, int cost){
		this.v = v;
		this.cost = cost;
	}
}

public class Test1753 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for(int i = 0; i<=V; i++)
			graph.add(new ArrayList<>());
		
		for(int i = 0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v,cost));
		}
		
		//boolean[] visit = new boolean[V+1];
		int[] cost = new int[V+1];
		Arrays.fill(cost,Integer.MAX_VALUE);
		cost[start] = 0;
		/*
		for(int i = 0; i<V; i++) {
			int nowCost = Integer.MAX_VALUE;
			int nowNodeIndex = 0;
			for(int j = 1; j<=V; j++) { // 현재노드 선택, 그리디 알고리즘
				if(!visit[j] && cost[j] < nowCost) {
					nowCost = cost[j];
					nowNodeIndex = j;
				}
			}
			// 현재노드 방문 체크
			visit[nowNodeIndex] = true;
			for(int j = 0; j<graph.get(nowNodeIndex).size(); j++) { // 현재 노드를 기준으로 인접노드들 거리 수정, DP
				Node adNode = graph.get(nowNodeIndex).get(j);
				if(cost[adNode.v] > cost[nowNodeIndex] + adNode.cost) {
					cost[adNode.v] =  cost[nowNodeIndex] + adNode.cost;
				}
			}
		}*/
		
		
		// 우선순위 큐를 이용한 다익스트라
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		
		que.add(new Node(start,0));
		while(!que.isEmpty()) {
			Node nowNode = que.poll();
			
			if(cost[nowNode.v] < nowNode.cost) {
				continue;
			}
			
			for(int i = 0;  i<graph.get(nowNode.v).size(); i++) {
				Node adNode = graph.get(nowNode.v).get(i);
				if(cost[adNode.v] > nowNode.cost + adNode.cost) { 
					cost[adNode.v] = nowNode.cost + adNode.cost;
					que.add(new Node(adNode.v, cost[adNode.v]));
				}
			}
		}
		
		
		for(int i = 1; i<=V; i++) {
			if(cost[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(cost[i]);
		}
	}

}
