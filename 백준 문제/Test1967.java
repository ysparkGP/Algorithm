package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test1967 {

	static class Node{
		int target;
		int cost;
		Node(int target, int cost){
			this.target = target;
			this.cost = cost;
		}
	}
	
	static boolean[] visit;
	static ArrayList<ArrayList<Node>> li;
	static int maxCost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		li = new ArrayList<>();
		for(int i = 0; i<=n; i++) {
			li.add(new ArrayList<>());
		}
		for(int i = 0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			li.get(start).add(new Node(end, cost));
			li.get(end).add(new Node(start, cost));
		}
		
		for(int i = 0; i<=n; i++) {
			visit = new boolean[n+1];
			visit[i] = true;
			dfs(i, 0);
		}
		
		System.out.println(maxCost);
	}
	
	static void dfs(int start, int nowCost) {
		
		maxCost = Math.max(maxCost, nowCost);
		
		//System.out.println(start +" , " + nowCost);
		if(li.size() >= 1) {
			for(Node targetNode: li.get(start)) {
				if(visit[targetNode.target]) continue;
				visit[targetNode.target] = true;
				dfs(targetNode.target, nowCost+targetNode.cost);
				visit[targetNode.target] = false;
			}
		}
		
	}

}
