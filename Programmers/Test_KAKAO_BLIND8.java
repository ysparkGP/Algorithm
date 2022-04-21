package programmers;

import java.util.Stack;

/*
 * 스택
 * 2019 카카오 개발자 겨울 인턴십
 * 크레인 인형뽑기 게임
 */

public class Test_KAKAO_BLIND8 {

	public static void main(String[] args) {
		int[][] board = {
				{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}
		};
		int[] moves = {1,5,3,5,1,2,1,4};
		Stack<Integer>[] dollStacks = new Stack[board.length+1];
		for(int i = 1; i<=board.length; i++) {
			dollStacks[i] = new Stack<>();
		}
		
		for(int i = 0; i<board.length; i++) {
			for(int j = board[i].length-1; j>=0; j--) {
				if(board[j][i] == 0) continue;
				dollStacks[i+1].add(board[j][i]);
			}
		}
		
		int cnt = 0;
		Stack<Integer> basket = new Stack<>();
		for(int move : moves) {
			if(dollStacks[move].isEmpty()) continue;
			int doll = dollStacks[move].pop();
			if(!basket.isEmpty() && doll == basket.peek()) {
				basket.pop();
				cnt+=2;
			}
			else {
				basket.add(doll);
			}
		}

	}

}
