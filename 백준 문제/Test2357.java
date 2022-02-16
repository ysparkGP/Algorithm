package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test2357 {
	static long maxValue;
	static long minValue;
	
	static class SegmentTree{
		long[] mintree;
		long[] maxtree;
		
		SegmentTree(int n){
			double treeHeight = Math.ceil(Math.log(n)/Math.log(2)) + 1;
			long treeCount = (long)Math.pow(2, treeHeight);
			mintree = new long[Math.toIntExact(treeCount)];
			maxtree = new long[Math.toIntExact(treeCount)];
		}
		
		long minInit(long[] arr, int node, int start, int end) {
			
			if(start == end) { // 리프노드
				return mintree[node] = arr[start];
			}
			else {
				return mintree[node] = Math.min(minInit(arr, 2*node, start, (start+end)/2) ,
						minInit(arr, 2*node + 1, (start+end)/2 + 1, end));
			}
		}
		long maxInit(long[] arr, int node, int start, int end) {
			
			if(start == end) { // 리프노드
				return maxtree[node] = arr[start];
			}
			else {
				return maxtree[node] = Math.max(maxInit(arr, 2*node, start, (start+end)/2) ,
						maxInit(arr, 2*node + 1, (start+end)/2 + 1, end));
			}
		}
		
		long findMin(int node, int start, int end, int left, int right) {
			// 찾고자하는 값의 범위가 세그먼트 트리가 갖고있는 범위를 넘을 때,
			if(start > right || end < left) return Long.MAX_VALUE;
			// 찾고자하는 범위를 찾을 때,
			else if(start >= left && end <= right) {
				
				return mintree[node];
			}
			// 찾고자하는 값의 범위가 세그먼트 트리가 갖고있는 범위를 갹갹 포함할 때,
			else{
				return Math.min(findMin(2*node, start, (start+end)/2, left, right),
				findMin(2*node+1, (start+end)/2 + 1, end, left, right));
			}
		}
		
		long findMax(int node, int start, int end, int left, int right) {
			// 찾고자하는 값의 범위가 세그먼트 트리가 갖고있는 범위를 넘을 때,
			if(start > right || end < left) return 0;
			// 찾고자하는 범위를 찾을 때,
			else if(start >= left && end <= right) {
				
				return maxtree[node];
			}
				
			// 찾고자하는 값의 범위가 세그먼트 트리가 갖고있는 범위를 포함할 때,
			else {
				return Math.max(findMax(2*node, start, (start+end)/2, left, right),
				findMax(2*node+1, (start+end)/2 + 1, end, left, right));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		SegmentTree segmentTree = new SegmentTree(N);
		segmentTree.minInit(arr, 1, 1, N);
		segmentTree.maxInit(arr, 1, 1, N);
		
		for(int i = 1; i<segmentTree.mintree.length; i++) {
			System.out.print(segmentTree.mintree[i] + " ");
			
		}
		System.out.println();
		
		for(int i = 1; i<segmentTree.mintree.length; i++) {
			System.out.print(segmentTree.maxtree[i] + " ");
			
		}
		System.out.println();
		
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			minValue = segmentTree.findMin(1, 1, N, left, right);
			maxValue = segmentTree.findMax(1, 1, N, left, right);
			sb.append(minValue + " " + maxValue);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
