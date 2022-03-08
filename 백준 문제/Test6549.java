package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 이 문제의 핵심은 어느 구간에서의 넓이는 그 구간에서 가장 작은 높이가 좌우한다.
 * 1. 스택
 * 현 스택에서 top인덱스의 높이보다 더 작은 인덱스가 나올 때 까지 담는다.
 * 높이가 더 작은 인덱스를 만나면 반복문을 통해 스택의 top인덱스의 높이가 더 작을때까지 pop을 하면서 넓이를 갱신한다.
 * 이런 작업에도 불구하고 pop되지 않은 원소를 계산해야한다.
 * 마지막 원소까지 담았다면 후처리를 진행한다.
 * 
 * 2. 세그먼트 트리 + 분할정복
 * 세그먼트 트리에는 구간마다 높이가 더 작은 인덱스를 담는다.(분할)
 * 모든 구간에서 높이가 가장 작은 인덱스부터 분할정복 시작
 * 인덱스가 선정되었다는 것은 어느 특정 구간에서는 가장 작은 높이를 가진 인덱스를 뜻하므로
 * 그 구간의 넓이는 (right-left+1) * height[구간에서 선택된 인덱스]
 * 그 후 왼쪽과 오른쪽을 재귀적으로 탐색(정복) 
 */

public class Test6549 {
	
	static int[] tree;
	static int[] height;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		while((st = new StringTokenizer(br.readLine()," ")).countTokens() > 1) {
			long max = 0;
			int n = Integer.parseInt(st.nextToken());
			tree = new int[4*n];
			
			height = new int[n+1];
			height[0] = Integer.MAX_VALUE;
			for(int i = 1; i<=n; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			init(height,1,1,n);
			System.out.println(getArea(n,1,n));
			
			/*
			int[] height = new int[n];
			for(int i = 0; i<n; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			Stack<Integer> stack = new Stack<>();
			for(int i = 0; i<n; i++) {
				// 현재 스택 top 지점인 높이보다 들어올 높이가 더 작을때 계산
				// 스택에 남아있는 막대기는 나가는 막대기의 높이보다 더 작아지는 경향을 이용해서
				// 현재 인덱스 - 1에서 stack.peek() (남아있는 막대기) 를 빼어준다.
				while(!stack.isEmpty() && height[stack.peek()] > height[i]) {
					int index = stack.pop();
					int width = stack.isEmpty()? i: i-1-stack.peek();
					max = Math.max(max, (long)width*height[index]);
				}
				stack.push(i);
			}
			
			// 남아있는 스택 처리
			// 남아있는 히스토그램은 자신보다 뒤에 막대기의 높이가 더 큰 특징
			while(!stack.isEmpty()) {
				int index = stack.pop();
				int width = stack.isEmpty()? n: n-1-stack.peek();
				max = Math.max(max, (long)width*height[index]);
			}
			System.out.println(max);*/
		}
	}
	
	static int init(int[] arr, int node, int start, int end) {
		
		if(start == end) return tree[node] = start;
		else {
			int leftIndex = init(arr,2*node,start,(start+end)/2);
			int rightIndex = init(arr,2*node+1,(start+end)/2+1, end);
			if(height[leftIndex] <= height[rightIndex]) return tree[node] = leftIndex;
			else return tree[node] = rightIndex;
		}
		
	}
	static int query(int node, int start, int end, int left, int right) { // 구간 내 최소 높이를 가진 인덱스를 찾아주는 쿼리
		if(start > right || end < left) return 0;
		else if(start >= left && end <= right) return tree[node];
		else {
			int leftIndex = query(2*node, start, (start+end)/2, left, right);
			int rightIndex = query(2*node+1, (start+end)/2+1, end, left, right);
			if(height[leftIndex] <= height[rightIndex]) return leftIndex;
			else return rightIndex;
		}
	}
	
	static long getArea(int n, int left, int right) { // 구간 내 넓이 구하는 메서드
		int index = query(1,1,n,left,right);
		long max = (long) height[index]*(right-left+1);
		
		if(index-1 >= left) { // 왼쪽을 더 탐색할 수 있다면
			max = Math.max(max, getArea(n,left,index-1));
		}
		if(index+1 <= right) { // 오른쪽을 더 탐색할 수 있다면
			max = Math.max(max, getArea(n,index+1,right));
		}
		
		return max;
	}

}
