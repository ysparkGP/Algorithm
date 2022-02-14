package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 크루스컬 알고리즘 (그리디 알고리즘 + 유니온 파인드)
 */

class Node{
	int start;
	int target;
	int cost;
	Node(int start, int target, int cost){
		this.start = start;
		this.target = target;
		this.cost = cost;
	}
}

public class Test1197 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Node> graph = new ArrayList<>();
		
		for(int i = 0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Node(start, target, cost));
		}
		
		Collections.sort(graph, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		parent = new int[V+1];
		for(int i = 1; i<=V; i++) {
			parent[i] = i;
		}
		
		long answer = 0;
		int cnt = 0;
		// 간선을 기준으로 그리디하게 선택
		for(Node node : graph) {
			int startParent = find(node.start);
			int targetParent = find(node.target);
			if(startParent == targetParent) continue; //부모가 같으면 사이클이 형성되었다는 것이므로 패스
			
			union(node.start, node.target);
			answer+=node.cost;
			cnt++; // 간선들의 개수가 V-1일 때, 종료
			if(cnt >= V-1) break;
		}
		
		
		System.out.println(answer);
		
		
	}
	static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB)
			return;
		else if(parentA > parentB) {
			parent[parentA] = parentB;
			return;
		}
		else {
			parent[parentB] = parentA;
			return;
		}
	}

}
