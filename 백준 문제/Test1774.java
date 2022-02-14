package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
	int start;
	int end;
	double cost;
	Node(int start, int end, double cost){
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

public class Test1774 {

	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // union처리

		parent = new int[N+1];
		int[][] god = new int[N+1][2];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			god[i][0] = x;
			god[i][1] = y;
			parent[i] = i;
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int god1 = Integer.parseInt(st.nextToken());
			int god2 = Integer.parseInt(st.nextToken());
			
			union(god1,god2);
		}
		
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				if(n1.cost > n2.cost)
					return 1;
				return -1;
			}
		});
		for(int i = 1; i<N; i++) {
			for(int j = i+1; j<=N; j++) {
				double distance = 
						Math.sqrt(Math.pow(god[i][0] - god[j][0],2) + 
								Math.pow(god[i][1] - god[j][1], 2));
				que.add(new Node(i,j,distance));
			}
		}
		double result = 0;
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			int startParent = find(node.start);
			int endParent = find(node.end);
			if(startParent == endParent) continue;
			
			union(node.start, node.end);
			result += node.cost;
			
		}
		System.out.println(String.format("%.2f", result));
	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]); //경로압축최적화
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA > parentB)
			parent[parentA] = parentB;
		else
			parent[parentB] = parentA;
	}

}
