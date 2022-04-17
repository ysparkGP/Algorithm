package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 0-1 bfs
 */

public class Test_Graph1 {
	
	public static void main(String[] args) {
		int n = 4;
		int[][] edge = {
				{1,3},{4,3},{2,3}
		};
		
		int[] distance = bfs(edge,n);
		int minDistance = 0;
		int cnt = 0;
		for(int i = 1; i<=n; i++) {
			if(minDistance < distance[i]) {
				minDistance = distance[i];
				cnt = 1;
			}
			else if(minDistance == distance[i]) {
				cnt++;
			}
		}
		int answer = cnt;
		System.out.println(cnt);
	}
	static int[] bfs(int[][] edge, int n){
		List<Integer>[] check = new ArrayList[n+1];
		for(int i = 0; i<n+1; i++) {
			check[i] = new ArrayList<>();
		}
		for(int i = 0; i<edge.length; i++) {
			check[edge[i][0]].add(edge[i][1]);
			check[edge[i][1]].add(edge[i][0]);
		}
		
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int i = 0; i<check[now].size(); i++) {
				int target = check[now].get(i);
				if(distance[target] > distance[now] + 1) {
					distance[target] = distance[now] + 1;
					que.add(target);
				}
			}
		}
		return distance;
	}

}
