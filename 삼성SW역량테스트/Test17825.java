package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 삼성 SW 역량 기출문제
 * 순열(백트래킹), 구현, 연결리스트
 */

public class Test17825 {
	
	static boolean[] board;
	static int[] dice;
	static int[] order;
	static int max;
	
	static class Node{
		int val;
		Node next, shortCut;
		boolean end, isEmpty;
		
		public Node(int val){
			this.val = val;
			this.isEmpty = true;
		}
		
		public Node addNext(int val) {
			this.next = new Node(val);
			return next;
		}
		public static Node getNode(Node start, int val) {
			
			Node nowNode = start;
			while(true) {
				if(nowNode == null) return null;
				
				if(nowNode.val == val)
					return nowNode;
				
				nowNode = nowNode.next;
			}
		}
		
	}
	
	static Node[] horses;
	static Node start;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		board = new boolean[100];
		dice = new int[10];
		order = new int[10];
		horses = new Node[4];
		for(int i = 0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		init();
		permutation(0);
		System.out.println(max);
		
	}
	static void permutation(int index) {
		if(index >= 10) {
			max = Math.max(max, game());
			return;
		}
		for(int i = 0; i<4; i++) {
			order[index] = i;
			permutation(index+1);
		}
	}
	static int game() {
		int score = 0;
		Arrays.fill(horses, start);
		
		for(int i = 0; i<order.length; i++) {
			Node cur = horses[order[i]];
			cur.isEmpty = true;
			for(int j = 0; j<dice[i]; j++) {
				// 파란색 칸에서 시작한다면
				if(j ==0 && cur.shortCut != null) {
					cur = cur.shortCut;
				}
				// 빨간 색칸에서 시작한다면
				else {
					cur = cur.next;
				}
			}
			
			horses[order[i]] = cur;
			if(!horses[order[i]].isEmpty && !horses[order[i]].end) {
				// 말이 도착한 칸에 다른 말이 있다면 무효
				score = 0;
				break;
			}
			else {
				cur.isEmpty = false;
				score += cur.val;
			}
		} // 게임 종료
		
		// 다음 게임을 위해 말이 밟았던 노드들 방문 초기화
		for(int i = 0; i<4; i++)
			horses[i].isEmpty = true;
		
		return score;
	}
	static void init() {
		start = new Node(0);
		Node tmp = start;
		for(int i = 2; i<=40; i+=2) {
			tmp = tmp.addNext(i);
		}
		Node end = tmp.addNext(0);
		end.end = true;
		end.next = end;
		
		// shortCut 설정
		// 25 -> 30 -> 35 -> 40
		Node crossNode = new Node(25);
		tmp = crossNode.addNext(30);
		tmp = tmp.addNext(35);
		tmp.next = Node.getNode(start, 40);

		// 10 -> 13 -> 16 -> 19 -> 25
		tmp = Node.getNode(start, 10);
		//System.out.println(tmp.val);
		tmp = tmp.shortCut = new Node(13);
		tmp = tmp.addNext(16);
		tmp = tmp.addNext(19);
		tmp.next = crossNode;
		
		// 20 -> 22 -> 24 -> 25
		tmp = Node.getNode(start, 20);
		tmp = tmp.shortCut = new Node(22);
		tmp = tmp.addNext(24);
		tmp.next = crossNode;
	
		// 30 -> 28 -> 27 -> 26 -> 25
		tmp = Node.getNode(start, 30);
		tmp = tmp.shortCut = new Node(28);
		tmp = tmp.addNext(27);
		tmp = tmp.addNext(26);
		tmp.next = crossNode;
		
	}

}
