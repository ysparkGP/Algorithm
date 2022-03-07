package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/* 
 * 슬라이딩 윈도우
 * 세그먼트 트리 -> 시간초과
 * 우선순위 큐 -> 시간초과
 * 덱 -> O
 */

public class Test11003 {

	static final long MAX_VALUE = 1000000001;
	static long[] tree;
	
	static class Pair{
		int index;
		int value;
		Pair(int value, int index){
			this.index = index;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<Pair> que = new ArrayDeque<>();
		
//		int[] arr = new int[N+1];
//		tree = new long[4*N];
		StringBuilder sb  = new StringBuilder();
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
			
			int value = Integer.parseInt(st.nextToken());
			if(que.size() == 0)
				que.add(new Pair(value, i));
			else {
				while(que.peekLast().value > value) {
					que.pollLast();
					if(que.size() == 0) break;
				}
				que.addLast(new Pair(value,i));
				
				if(que.peek().index < i-L+1)  que.pollFirst();
			}
			
			sb.append(que.peek().value + " ");
		}
		
//		init(arr, 1, 1, N);
//		for(int i = 1; i<=N; i++) {
//			int left = i-L+1;
//			int right = i;
//			if(left < 1)
//				left = 1;
//			sb.append(query(1,1,N,left,right) + " ");
//		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
	}
	
//	static long init(int[] arr, int node, int start, int end) {
//		if(start == end) return tree[node] = arr[start];
//		else return tree[node] = Math.min(init(arr, 2*node, start, (start+end)/2), init(arr, 2*node+1, (start+end)/2+1, end));
//	}
//	
//	static long query(int node, int start, int end ,int left, int right) {
//		if(start > right || end < left) return MAX_VALUE;
//		
//		else if(left <= start && end <= right)  return tree[node];
//		
//		else {
//			return Math.min(query(2*node, start, (start+end)/2, left, right),
//					query(2*node+1, (start+end)/2+1, end, left, right));
//		}
//	}

}
