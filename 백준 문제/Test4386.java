package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 크루스컬(MST), union-find
 */

public class Test4386 {

	static int[] parent;
	static class Edge{
		int s;
		int e;
		double distance;
		Edge(int s, int e, double distance){
			this.s = s;
			this.e = e;
			this.distance = distance;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[][] stars = new double[N][2];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			double y = Double.parseDouble(st.nextToken());
			double x = Double.parseDouble(st.nextToken());
			stars[i][0] = y;
			stars[i][1] = x;
		}
		
		List<Edge> graph = new ArrayList<>();
		for(int i = 0; i<N-1; i++) {
			for(int j = i+1; j<N; j++) {
				double distance = distanceCal(stars[i][0], stars[i][1], stars[j][0], stars[j][1]);
				graph.add(new Edge(i,j,distance));
			}
		}
		Collections.sort(graph, new Comparator<>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				return Double.compare(e1.distance, e2.distance);
			}
		});
		parent = new int[N];
		for(int i = 0; i<N; i++) parent[i] = i;
		double answer = 0;
		for(Edge edge: graph) {
			int s = edge.s;
			int e = edge.e;
			int parentS = find(s);
			int parentE = find(e);
			if(parentS == parentE) continue;
			else {
				union(parentS, parentE);
				answer += edge.distance;
			}
		}
		System.out.println(String.format("%.2f", answer));
	}
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return;
		else {
			if(parentA > parentB) {
				parent[parentA] = parentB;
			}
			else {
				parent[parentB] = parentA;
			}
		}
		return;
	}
	static double distanceCal(double y1, double x1, double y2, double x2) {
		return Math.sqrt((y1-y2) * (y1-y2) + (x1-x2) * (x1-x2));
	}

}
