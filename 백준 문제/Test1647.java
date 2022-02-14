package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 최소 신장 트리 (크루스컬 알고리즘)... 그리디 + 유니온파인드
 */

class Node{
	int start;
	int target;
	int cost;
	Node(int start, int target, int cost){
		this.start =  start;
		this.target = target;
		this.cost = cost;
	}
}

public class Test1647 {
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> graph = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Node(start, target, cost));
		}
		parent = new int[N+1];
		for(int i = 1; i<=N; i++)
			parent[i] = i;
		Collections.sort(graph, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		
		//크루스컬 시작
		int answer = 0;
		int cnt = 0;
		for(int i = 0; i<graph.size(); i++) {
			int startParent = find(graph.get(i).start);
			int endParent = find(graph.get(i).target);
			
			if(startParent == endParent) continue;
			union(startParent, endParent);
			answer += graph.get(i).cost;
			cnt++;
			if(cnt >= N-2) break; 
		}
		System.out.println(answer);
		
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		
		return parent[a] = find(parent[a]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA == parentB) return;
		else if(parentA > parentB) parent[parentB] = parentA;
		else parent[parentA] = parentB;
		
		return;
	}

}
