package programmers;

import java.util.HashSet;
import java.util.Set;

/*
 * Union-Find
 */

public class Test_DfsBfs2 {

	static int[] parent;
	public static void main(String[] args) {
		int[][] computers = {{1,0,0,0,0}, {0,1,0,0,1}, {0,0,1,0,1},{0,0,0,1,1},{1,0,0,0,1}};
		int n = 5;
		parent = new int[n];
		for(int i = 0; i<n; i++) parent[i] = i;
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n; j++) {
				if(i == j) continue;
				
				// 주의: 유니온을 루트끼리 수행하지 않으면 다른 노드들이 한 부모를 가리키지 못하면서 붕 뜨게 된다. (0,0,2,2) -> (0,0,2,0)
				if(computers[i][j] == 1) union(parent[i], parent[j]);
			}
		}
		Set<Integer> networks = new HashSet<>();
		for(int i = 0; i<n; i++) {
			networks.add(find(i));
		}
		System.out.println(networks.size());
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	static void union(int A, int B) {
		int parentA = find(A);
		int parentB = find(B);
		
		if(parentA == parentB) return;
		else {
			if(parentA < parentB) {
				parent[B] = parentA;
			}
			else {
				parent[A] = parentB;
			}
		}
	}

}
