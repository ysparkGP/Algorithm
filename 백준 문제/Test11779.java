package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 다익스트라, 역추적
 */

public class Test11779 {

	static class Node{
		int end;
		int cost;
		Node(int end, int cost){
			this.end = end;
			this.cost = cost;
		}
	}
	static int[] dp;
	static int[] trace;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Node>[] graph = new ArrayList[n+1];
		for(int i = 0; i<graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
			
		trace = new int[n+1];
		for(int i = 0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end,cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		sb.append(dijkstra(graph, start, end)+"\n");
		trace(sb, start, end);
		System.out.println(sb.toString());
	}
	
	public static int dijkstra(List<Node>[] graph, int start, int end) {
		
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return Integer.compare(n1.cost, n2.cost);
			}
		});
		
		dp = new int[graph.length];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;
		que.add(new Node(start,0));
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			if(dp[node.end] < node.cost) continue;
			
			for(int i = 0; i<graph[node.end].size(); i++) {
				Node nextNode = graph[node.end].get(i);
				if(dp[nextNode.end] > dp[node.end] + nextNode.cost) {
					que.add(new Node(nextNode.end, dp[node.end] + nextNode.cost));
					dp[nextNode.end] = dp[node.end] + nextNode.cost;
					trace[nextNode.end] = node.end;
				}
			}
		}
		
		return dp[end];
	}
	
	public static void trace(StringBuilder sb, int start, int end) {
		int cnt = 1;
		Stack<Integer> stack = new Stack<>();
		int now = end;
		while(now != start) {
			stack.add(now);
			now = trace[now];
			cnt++;
		}
		sb.append(cnt+"\n");
		sb.append(start +" ");
		while(!stack.isEmpty()) {
			sb.append(stack.pop() +" ");
		}
	}

}
