package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 최소 신장 트리 (Prim)... 다익스트라랑 비슷
 * 프림으로 하려 하였으나, N이 최대 100,000 인 관계로 크루스컬로 풀이
 * 프림 : O(N^2), 크루스컬: O(E*logE)
 */

class Planet{
	int x;
	int y;
	int z;
	int num;
	Planet(int x, int y, int z, int num){
		this.x = x;
		this.y = y;
		this.z = z;
		this.num = num;
	}
}

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

public class Test2887 {
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Planet> space = new ArrayList<>();
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			space.add(new Planet(x,y,z,i));
		}
		
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		
		Collections.sort(space, new Comparator<Planet>() {
			@Override
			public int compare(Planet p1, Planet p2) {
				return p1.x - p2.x;
			}
		});
		for(int i = 0; i<space.size()-1; i++) {
			que.add(new Node(space.get(i).num, space.get(i+1).num,
					Math.abs(space.get(i).x - space.get(i+1).x)));
		}
		
		Collections.sort(space, new Comparator<Planet>() {
			@Override
			public int compare(Planet p1, Planet p2) {
				return p1.y - p2.y;
			}
		});
		for(int i = 0; i<space.size()-1; i++) {
			que.add(new Node(space.get(i).num, space.get(i+1).num,
					Math.abs(space.get(i).y - space.get(i+1).y)));
		}
		
		Collections.sort(space, new Comparator<Planet>() {
			@Override
			public int compare(Planet p1, Planet p2) {
				return p1.z - p2.z;
			}
		});
		for(int i = 0; i<space.size()-1; i++) {
			que.add(new Node(space.get(i).num, space.get(i+1).num,
					Math.abs(space.get(i).z - space.get(i+1).z)));
		}
		
		parent = new int[N+1];
		for(int i = 1; i<=N; i++)
			parent[i] = i;
		int cnt = 0;
		int result = 0;
		while(!que.isEmpty()) {
			Node edge = que.poll();
			int startParent = find(edge.start);
			int	targetParent = find(edge.target);
			if(startParent == targetParent) continue;
			
			union(edge.start, edge.target);
			result += edge.cost;
			cnt++;
			if(cnt >= N-1) break;
		}
		System.out.println(result);
		
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a]  = find(parent[a]);
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

