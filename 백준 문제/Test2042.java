package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 세그먼트 트리
 */

public class Test2042 {
	
	static class SegmentTree{
		long[] tree;
		
		SegmentTree(int n){
			//트리 높이 계산
			double treeHeight = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
			//트리 노드 수 계산
			long treeCount = (long) Math.pow(2,treeHeight);
			tree = new long[Math.toIntExact(treeCount)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			// 세그먼트 트리의 리프노드에 도착한 경우
			if(start == end) {
				// 리프노드의 배열에 값을 저장 후 리턴
				return tree[node] = arr[start];
			}
			else {
				// 리프노드가 아닌 경우에는 자식의 노드 값을 더해서 노드의 값 초기화 후 리턴
				return tree[node] = init(arr, 2*node, start, (start + end)/2) +
						init(arr, 2*node+1, (start+end)/2 + 1, end);
			}
		}
		
		long sum(int node, int start, int end, int left, int right) {
			// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하지 않으면 리턴
			if(end < left || start > right) {
				return 0;
			}
			// 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 속하면 리턴
			else if(left <= start && end <= right) {
				return tree[node];
			}
			else {
				//1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간에 일부가 걸칠 때,
				//2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 모두 포함할 때
				return sum(2*node, start, (start+end)/2, left, right) + 
						sum(2*node+1, (start+end)/2+1, end, left, right);
			}
		}
		
		//차이값 갱신
		void update(int node, int start, int end, int index ,long diff) {
			//노드가 가지는 값의 구간이 덥데이트 할 인덱스를 포함하지 않은 경우
			if(index < start || index > end) return;
			
			else {
				//노드가 가지는 값의 구간이 업데이트 할 인덱스를 포함하는 경우
				tree[node] = tree[node] + diff;
				
				//노드가 리프노드가 아닐 경우
				if(start != end) {
					//리프노드까지 자식노드 계속 탐색
					update(2*node, start, (start+end)/2, index, diff);
					update(2*node+1, (start+end)/2 + 1, end, index, diff);
				}
			}
		}
		
		//직접 갱신
		long update2(int node, int start, int end, int index, long changeValue) {
			//노드가 가지는 값의 구간이 업데이트 할 인덱스를 포함하지 않은 경우
			if(index < start || end < index) return tree[node];
			
			else if(start == index && end == index) {
				//노드가 가지는 값의 구간과 배열의 인덱스가 만난 경우
				return tree[node] = changeValue;
			}
			else {
				//노드가 가지는 값의 구간이 배열의 인덱스를 포함하는 경우
				//자식 노드를 탐색 후 값을 더해서 리턴
				return tree[node] = update2(2*node, start, (start+end)/2, index, changeValue) +
						update2(2*node + 1, (start+end)/2 + 1, end, index, changeValue);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 수 변경 횟수
		int K = Integer.parseInt(st.nextToken()); // 수 구간 합 횟수
		
		long[] arr = new long[N+1];
		for(int i = 1; i<=N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		for(int i = 0; i<M+K; i++ ) {
			st = new StringTokenizer(br.readLine()," ");
			int job = Integer.parseInt(st.nextToken());
			int index1 = Integer.parseInt(st.nextToken());
			
			if(job == 1) {
				long index2 = Long.parseLong(st.nextToken());
				tree.update2(1, 1, N, index1, index2);
			}
			else if(job == 2) {
				int index2 = Integer.parseInt(st.nextToken());
				long answer = tree.sum(1, 1, N, index1, index2);
				System.out.println(answer);
			}
			
		}
	}
	
}
