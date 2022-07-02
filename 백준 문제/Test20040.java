package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 유니온 파인드, 분리 집합
 */

public class Test20040 {
	
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i = 0; i<n; i++) parent[i] = i;
		
		for(int i = 1; i<=m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int parentA = find(a);
			int parentB = find(b);
			if(parentA == parentB) {
				System.out.println(i);
				return;
			}
			union(a,b);
			
		}
		System.out.println(0);
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return;
		else {
			if(parentA < parentB) {
				parent[parentB] = parentA;
			}
			else {
				parent[parentA] = parentB;
			}
		}
	}

}
