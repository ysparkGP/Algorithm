package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * SegmentTree와 Fenwick Tree를 이용한 Inversion Count
 */

public class Test7578 {
	
	/*
	static class SegmentTree{
		
		long[] tree;
		SegmentTree(int n){
			double treeHeight = Math.ceil(Math.log(n)/Math.log(2)) + 1;
			
			long treeCount = (long) Math.pow(2, treeHeight);
			
			tree = new long[Math.toIntExact(treeCount)];
		}
		
		long add(int node, int start, int end, int left, int right) {
			if(start > right || end < left) return 0;
			
			else if(left <= start && right >= end) return tree[node];
			
			else return add(2*node, start, (start+end)/2, left, right) + 
					add(2*node+1, (start+end)/2 + 1, end, left, right);
		}
		
		long update(int node, int start, int end, int valueChange, int index) {
			if(start > index || end < index) return tree[node];
			
			else if(start == index && end == index) return tree[node] = valueChange;
			
			else return tree[node] = update(2*node, start, (start+end)/2, valueChange, index) +
					update(2*node+1, (start+end)/2 + 1, end, valueChange, index);
		}
		
		void update2(int node, int start, int end, int diff, int index) {
			if(start > index || end < index) return;
			
			else if(start <= index && end >= index) {
				tree[node] += diff;
				
				if(start != end) {
					update2(2*node, start, (start+end)/2, diff, index);
					update2(2*node+1, (start+end)/2 + 1, end, diff, index);
				}
			}
		}
		
	}*/

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			int val = Integer.parseInt(st.nextToken());
			map.put(val, i); // A기준 식별번호 인덱스
		}
		int[] valB = new int[N+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			valB[i] = Integer.parseInt(st.nextToken());
		}
		
		//SegmentTree segmentTree = new SegmentTree(N);
		/*long answer = 0;
		for(int i = 1; i<=N; i++) {
			
			int valueB = valB[i];
			int index = map.get(valueB);
			answer += segmentTree.add(1, 1, N, index+1, N);
			segmentTree.update2(1, 1, N, 1, index);
			
		}*/
		
		//펜윅트리
		long[] tree = new long[N+1];
		long answer = 0;
		for(int i = 1; i<=N; i++) {
			int valueB = valB[i];
			int index = map.get(valueB);
			answer += (add(tree, N) - add(tree,index));
			update(tree, index, 1);
		}
		
		System.out.println(answer);
		
	}
	
	static long add(long[] tree, int index) {
		long answer = 0;
		while(index > 0) {
			answer += tree[index];
			index -= (index & -index);
		}
		return answer;
	}
	static void update(long[] tree, int index, int diff) {
		while(index < tree.length) {
			tree[index] += diff;
			index += (index & -index);
		}
	}

}
