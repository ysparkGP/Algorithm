package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 세그먼트 트리
 */

public class Test11505 {
	
	static int mod = 1000000007;
	
	static class SegmentTree{
		long[] tree;
		
		SegmentTree(int n){
			//세그먼트 트리 높이
			double treeHeight = Math.ceil(Math.log(n)/Math.log(2)) + 1;
			
			//세그먼트 트리 개수
			long treeCount = (long) Math.pow(2, treeHeight);
			
			tree = new long[Math.toIntExact(treeCount)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			// 세그먼트 트리의 리프노드에 도착한 경우
			if(start == end)
				return tree[node] = (arr[start] % mod); //모듈러 연산
			// 세그먼트 트리의 부모노드에 도착한 경우
			else
				return tree[node] = (init(arr, 2*node, start, (start+end)/2)  *
						init(arr, 2*node+1, (start+end)/2 + 1, end)) % mod; // 모듈러 연산
		}
		
		long product(int node, int start, int end, int left, int right) {
			// 세그먼트 트리가 가지는 구간이 구해야할 구간합을 벗어날 때,
			if(start > right || end < left) return 1;
			// 세그먼트 트리가 가지는 구간이 구해야할 구간합에 속해 있을 때,
			else if(start >= left && end <= right)
				return tree[node] % mod;
			else {
				//세그먼트 트리가 가지는 구간이 구해야할 구간합을 포함하고 있을 때,
				//세그먼트 트리가 가지는 구간이 구해야할 구간합을 걸칠 때,
				return (product(2*node, start, (start+end)/2, left, right) * 
						product(2*node + 1, (start+end)/2 + 1, end, left, right)) % mod;
			}
		}
		
		long update(int node, int start, int end, int index, int changeValue) {
			// 세그먼트 트라가 가지는 구간이 인덱스를 벗어날 때, 그래도 인덱스가 포함되는 부모 노드를 업데이트 쳐줘야하므로 노드 리턴
			if(start > index || end < index) return tree[node];
			
			// 세그먼트 트리가 가지는 구간이 리프노드인데, 그 인덱스일 때,
			else if(start == index && end == index) {
				return tree[node] = changeValue % mod;
			}
			// 구간이 리프노드가 아닐 때,
			// 리프노드부터 부모노드를 업데이트 치면서 리턴
			else return tree[node] = (update(2*node, start, (start+end)/2, index, changeValue) *
					update(2*node+1, (start+end)/2 + 1, end, index, changeValue)) % mod;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		for(int i = 0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) { //update
				tree.update(1,1,N,b,c);
			}
			else { // product
				System.out.println(tree.product(1, 1, N, b, c));
			}
			
		}
	}

}
