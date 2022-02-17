package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test10868 {
	
	static class SegmentTree{
		int[] minTree;
		//int[] maxTree;
		
		SegmentTree(int n){
			double treeHeight = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			long treeCount = (long) Math.pow(2, treeHeight);
			minTree = new int[Math.toIntExact(treeCount)];
			//maxTree = new int[Math.toIntExact(treeCount)];
		}
		
		int initMin(int[] arr, int node, int start, int end) {
			if(start == end) {
				//리프노드
				return minTree[node] = arr[start];
			}
			
			else {
				return minTree[node] = Math.min(initMin(arr, 2*node, start, (start+end)/2), 
						initMin(arr, 2*node+1, (start+end)/2 + 1, end));
			}
		}
		
		int findMin(int node, int start, int end, int left, int right) {
			if(start >  right || end < left) return Integer.MAX_VALUE;
			
			else if(start >= left && end <= right) {
				return minTree[node];
			}
			else return Math.min(findMin(2*node, start, (start+end)/2, left, right),
					findMin(2*node+1, (start+end)/2 + 1, end, left, right));
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree segmentTree = new SegmentTree(N);
		segmentTree.initMin(arr, 1, 1, N);
		/*
		for(int i = 1; i<segmentTree.minTree.length; i++) {
			System.out.print(segmentTree.minTree[i] + "  ");
		}*/
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			System.out.println(segmentTree.findMin(1, 1, N, left, right));
		}
		
	}

}
