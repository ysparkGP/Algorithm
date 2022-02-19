package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 세그먼트 트리, 펜윅 트리
 */

public class Test2243 {

	static class SegmentTree{
		long[] tree;
		
		SegmentTree(int n){
			//double treeHeight = Math.ceil(Math.log(n)/Math.log(2)) + 1;
			
			//long treeCount = (long) Math.pow(2, treeHeight);
			
			tree = new long[4*n];
		}
		
		long add(int node, int start, int end, int left, int right) {
			if(start > right || end < left) return 0;
			
			else if(start >= left && end <= right) return tree[node];
			
			else return add(2*node, start, (start+end)/2, left, right) + 
					add(2*node+1, (start+end)/2 + 1, end, left, right);
		}
		
		void update(int node, int start, int end, int diff, int index) {
			
			if(start > index || end < index) return;
			
			else {
				tree[node] += diff;
				//System.out.println("start : " + start + " end : " + end + " index : " + index + " node : " + node);
				if(start != end) { // 리프노드가 아닌 경우 계속 자식노드들 업데이트
					update(2*node, start, (start+end)/2, diff, index);
					update(2*node+1, (start+end)/2 + 1, end, diff, index);
				}
			}
		}
		int find(int node, int start, int end, long rank) {
			
			//System.out.println(start + ", " + end + ", " + rank);
			
			if(start == end) {
				
				update(1, 1, 1000000, -1, start);
				return start;
			}
			
			else{
				if(rank <= tree[2*node])
					return find(2*node, start, (start+end)/2, rank);
				else
					return find(2*node+1, (start+end)/2 + 1, end, rank - tree[2*node]);
			}
			
		}
	}
	
	static long[] fenwickTree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		SegmentTree segmentTree = new SegmentTree(1000000);
		//fenwickTree = new long[1000001];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int operation = Integer.parseInt(st.nextToken());
			if(operation == 1) {
				long find = Long.parseLong(st.nextToken());
				int findRank = segmentTree.find(1, 1, 1000000, find);
				System.out.println(findRank);
			}
			else {
				int index = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				segmentTree.update(1, 1, 1000000, diff, index);
			}
			
			/*for(int j = 1; j<16; j++) {
				System.out.print(segmentTree.tree[j] + " ");
			}
			System.out.println();*/
		}
		
	}
	
	
	static long add(int index) {
		long result = 0;
		
		while(index > 0) {
			result += fenwickTree[index];
			index -= (index & -index);
		}
		
		return result;
	}
	static void update(int index, int diff) {
		while(index < fenwickTree.length) {
			fenwickTree[index] += diff;
			index += (index & -index);
		}
	}

}
