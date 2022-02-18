package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 펜윅트리
 */

public class Test2268 {
	
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
		
		void update(int index, int diff) {
			
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
		int M = Integer.parseInt(st.nextToken());
		
		FenwickTree fenwickTree = new FenwickTree(N+1);
		int[] arr = new int[N+1];
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			int operation = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(operation == 0) {
				if(x < y) {
					int temp = x;
					x = y;
					y = temp;
				}
				System.out.println(fenwickTree.add(x) - fenwickTree.add(y-1));
			}
			else {
				int modifyValue = y;
				int diff = modifyValue - arr[x];
				arr[x] = modifyValue;
				fenwickTree.update(x, diff);
			}
			
			
		}
	}

}
