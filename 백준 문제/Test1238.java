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
	int idx;
	int cost;
	Node(int idx, int cost){
		this.idx = idx;
		this.cost = cost;
	}
}

public class Test1238 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<>();
		for(int i = 0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int startIdx = Integer.parseInt(st.nextToken());
			int endIdx = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(startIdx).add(new Node(endIdx,cost));
		}
		
		int[][] cost = new int[N+1][N+1];
		for(int i = 0; i<=N; i++)
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		
		for(int i = 1; i<=N; i++) {
			int start = i;
			PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					return n1.cost -  n2.cost;
				}
			});
			cost[i][i] = 0;
			que.add(new Node(start,0));
			while(!que.isEmpty()) {
				Node curNode = que.poll();
				
				if(curNode.cost > cost[i][curNode.idx]) continue;
				if(curNode.idx == X && i!= X)
					break;
				
				for(int j = 0; j<graph.get(curNode.idx).size(); j++) {
					Node adNode = graph.get(curNode.idx).get(j);
					if(cost[i][adNode.idx] > curNode.cost + adNode.cost) {
						cost[i][adNode.idx] = curNode.cost + adNode.cost;
						que.add(new Node(adNode.idx, cost[i][adNode.idx]));
					}
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i<=N; i++) {
			max = Math.max(max, cost[i][X] + cost[X][i]);
		}
		System.out.println(max);
		
	}

}
