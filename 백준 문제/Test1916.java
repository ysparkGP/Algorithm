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

class Nodes{
	
	int idx;
	int cost;
	Nodes(int idx, int cost){
		this.idx = idx;
		this.cost = cost;
	}
}

public class Test1916 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		int M = Integer.parseInt(br.readLine()); 
	
		ArrayList<ArrayList<Nodes>> graph = new ArrayList<>();
		for(int i = 0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Nodes(v,cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0;
		PriorityQueue<Nodes> que = new PriorityQueue<>(new Comparator<Nodes>() {
			
			@Override
			public int compare(Nodes n1, Nodes n2) {
				return n1.cost -  n2.cost;
			}
		});
				
		que.add(new Nodes(start,0));
		while(!que.isEmpty()) {
			Nodes curNode = que.poll();
			
			if(curNode.cost > cost[curNode.idx])
				continue;
			if(curNode.idx == end) {
				System.out.println(curNode.cost);
				return;
			}
			
			for(int i = 0; i<graph.get(curNode.idx).size(); i++) {
				Nodes adNode = graph.get(curNode.idx).get(i);
				if(cost[adNode.idx] > curNode.cost + adNode.cost) {
					cost[adNode.idx] = curNode.cost + adNode.cost;
					que.add(new Nodes(adNode.idx, cost[adNode.idx]));
				}
			}
		}
	}

}
