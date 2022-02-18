package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 펜윅 트리
 */

public class Test12837 {

	static class FenwickTree{
		
		long[] tree;
		FenwickTree(int n){
			tree = new long[n+1];
		}
		
		long add(int index) {
			
			long result = 0;
			
			while(index > 0) {
				result += tree[index];
				index -= (index & -index);
			}
			
			return result;
		}
		
		void update(int index, long diff) {
			
			while(index < tree.length) {
				tree[index] += diff;
				index += (index & -index);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N+1];
		FenwickTree fenwickTree = new FenwickTree(N);
		
		for(int i = 0; i<Q; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int operation = Integer.parseInt(st.nextToken());
			
			if(operation == 1) { // p일에 x를 추가 update(p, x)
				int p = Integer.parseInt(st.nextToken());
				long x = Long.parseLong(st.nextToken());
				fenwickTree.update(p, x);
				
			}
			else { // p일부터 q일까지 변화한 양 출력... add(q) - add(p-1)
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				
				if(p < q) {
					int temp = p;
					p = q;
					q = temp;
				}
				
				// p > q
				System.out.println(fenwickTree.add(p) - fenwickTree.add(q-1));
			}
		}
	}

}
