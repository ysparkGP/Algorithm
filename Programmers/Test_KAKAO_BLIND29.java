package programmers;

import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 2021 카카오 채용연계형 인턴십
 * 표 편집
 * 연결리스트, 스택, 구현
 */

public class Test_KAKAO_BLIND29 {
	static class Node{
		Node prev, next;
		int idx;
		Node(int idx){
			this.idx = idx;
		}
		static Node initNode(int n) {
			Node start = new Node(-1);
			Node prev = start;
			Node cur = null;
			for(int i = 0; i<n; i++) {
				cur = new Node(i);
				prev.next = cur;
				cur.prev = prev;
				prev = cur;
			}
			cur.next = new Node(-1);
			return start.next;
		}
		boolean hasNext() {
			return next.idx != -1;
		}
		void rollback() {
			prev.next = this;
			next.prev = this;
		}
		void remove() {
			prev.next = next;
			next.prev = prev;
		}
	}

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		
		// 연결리스트
		Node cur = Node.initNode(n);
		for(int i = 0; i<k; i++) {
			cur = cur.next;
		}
		Stack<Node> stack = new Stack<>();
		for(String order : cmd) {
			if(order.length() == 1) { // C(삭제), Z(복구)
				if(order.equals("C")) {
					stack.add(cur);
					cur.remove();
					cur = cur.hasNext()? cur.next: cur.prev;
				}
				else {
					stack.pop().rollback();
				}
			}
			else {
				StringTokenizer st = new StringTokenizer(order, " ");
				String direction = st.nextToken();
				int count = Integer.parseInt(st.nextToken());
				if(direction.equals("U")) { // 위로
					for(int i = 0; i<count; i++)
						cur = cur.prev;
				}
				else { // 아래로
					for(int i = 0; i<count; i++)
						cur = cur.next;
				}
			}
		}
		StringBuilder sb = new StringBuilder("O".repeat(n));
		while(!stack.isEmpty()) {
			sb.setCharAt(stack.pop().idx, 'X');
		}
		
		System.out.println(sb.toString());
		/* 단순 정답 도출을 위한 스택 활용
		Stack<Integer> stack = new Stack<>();
		int size = n;
		for(int i = 0; i<cmd.length; i++) {
			String order = cmd[i];
			if(order.length() == 1) { // C(삭제), Z(복구)
				if(order.equals("C")) {
					stack.push(k);
					size--;
					if(k >= size) k--;
				}
				else {
					int rollBackIndex = stack.pop();
					size++;
					if(rollBackIndex <= k) k++;
				}
			}
			else {
				StringTokenizer st = new StringTokenizer(order, " ");
				String direction = st.nextToken();
				int count = Integer.parseInt(st.nextToken());
				if(direction.equals("U")) { // 위로
					k-=count;
				}
				else { // 아래로
					k+=count;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<size; i++) {
			sb.append("O");
		}
		while(!stack.isEmpty()) {
			sb.insert(stack.pop(), "X");
		}
		//System.out.println(sb.toString());
		 */
		
	}

}
