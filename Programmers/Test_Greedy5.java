package programmers;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 크루스컬 알고리즘 (MST: 최소 신장 트리)
 * 그리디하게 간선들을 선택하면서 유니온파인드 알고리즘으로 사이클을 찾는다.
 * 사이클이 나오는 간선들이 선택된다면 선택하지 않고 그리디하게 선택을 진행해나간다.
 */

public class Test_Greedy5 {
	static int[] parent;
	public static void main(String[] args) {
		int[][] costs = {
				{0,1,1},
				{0,2,2},
				{1,2,5},
				{1,3,1},
				{2,3,8}
		};
		int N = 4;
		Arrays.sort(costs, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		parent = new int[N];
		for(int i = 0; i<N; i++) parent[i] = i;
		
		int answer = kruskal(costs);
		System.out.println(answer);
	}
	static int kruskal(int[][] costs) {
		int totalCost = 0;
		
		for(int i = 0; i<costs.length; i++) {
			int parentA = find(costs[i][0]);
			int parentB = find(costs[i][1]);
			if(parentA == parentB) continue;
			union(parentA, parentB);
			totalCost += costs[i][2];
		}
		
		return totalCost;
	}
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return;
		else if(parentA < parentB) parent[b] = parentA;
		else parent[a] = parentB;
		return;
		
	}

}
