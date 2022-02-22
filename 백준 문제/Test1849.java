package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 세그먼트 트리 응용 문제 (내 앞에 큰수들이 몇개 있는지 찾는 쿼리)
 */

public class Test1849 {

	static long[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = 1;
		}
		
		tree = new long[4*N];
		init(arr, 1, 1, N);
		
		int[] A = new int[N+1];
		for(int i = 1; i<=N; i++) {
			
			long find = Long.parseLong(br.readLine()); //1~A[i]-1의 구간합
			int findIndex = query(1,1,N,find);
			A[findIndex] = i;
			update(1,1,N,-1,findIndex);
			
			//시간초과
			/*int start = 1;
			int end =  N;
			while(start <= end) {
				int mid = (start+end)/2;
				long result = add(1,1,N,1,mid);
				if(result == find+1 && A[mid] == 0) {
					//System.out.println(mid);
					
					A[mid] = i;
					update(1,1,N,-1,mid);
					break;
				}
				else if(result > find+1 || (result == find+1 && A[mid] != 0)) {
					end = mid-1;
				}
				else {
					start = mid+1;
				}
			}*/
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i<=N; i++) {
			sb.append(A[i] + "\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
	static long init(int[] arr, int node, int start, int end) {
		
		if(start == end) return  tree[node] = arr[start];
		
		else return tree[node] = init(arr, 2*node, start, (start+end)/2) +
				init(arr,2*node+1, (start+end)/2 + 1, end);
	}
	
	/*static long add(int node, int start, int end,  int left, int right) {
		if(start >  right || end < left) return  0;
		
		else if(left <= start && right >= end) return tree[node];
		
		else return add(2*node, start, (start+end)/2, left, right) +
				add(2*node+1, (start+end)/2+1, end, left, right);
		
	}*/
	static int query(int node, int start, int end, long find) {
		if(start == end) return start;
		if(tree[2*node] > find)
			return query(2*node, start, (start+end)/2, find);
		else
			return query(2*node+1, (start+end)/2+1, end, find-tree[2*node]);
			
	}
	
	static void update(int node, int start, int end, int diff, long index) {
		if(start > index || end < index)  return;
		
		else if(start <= index && index <= end) {
			tree[node] += diff;
			if(start != end) {
				update(2*node, start, (start+end)/2, diff, index);
				update(2*node+1, (start+end)/2+1, end, diff, index);
			}
		}
	}

}
