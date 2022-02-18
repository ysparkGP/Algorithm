package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 펜윅 트리 (Binary Indexed Tree)
 * 세그먼트 트리의 메모리를 더욱 효율적으로 줄임
 * 비트 연산으로 구간합 도출
 */

public class Test1275 {

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
			
			// index & -index(2의 보수) ==> 최하위 비트 구하기
			// 0100 & (1011 + 0001) => 0100 & 00100
			while(index < tree.length) {
				tree[index] += diff;
				index += (index & -index);
			}
		}
	}
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new  StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		FenwickTree fenwickTree = new FenwickTree(N);
		long[] arr = new long[N+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			long value = Long.parseLong(st.nextToken());
			fenwickTree.update(i, value);
			arr[i] = value;
		}
			
		for(int i = 0; i<Q; i++) {
			st  = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a  = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			if(x > y) {
				int temp = x;
				x = y;
				y = temp;
			}
			long diff = b - arr[a];
			arr[a] = b;
			System.out.println(fenwickTree.add(y) - fenwickTree.add(x-1));
			fenwickTree.update(a, diff);
			/*for(int j = 1; j<=N;j++) {
				System.out.print(fenwickTree.tree[j] + " ");
				
			}
			System.out.println();*/
		}

	}

}
