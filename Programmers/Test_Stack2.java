package programmers;

import java.util.Stack;

public class Test_Stack2 {

	public static void main(String[] args) {
		int[] prices = {1,2,3,2,3};
		int[] answer = new int[prices.length];
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i<prices.length; i++) {
			
			while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
				answer[stack.peek()] = i - stack.pop();
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			answer[stack.peek()] = prices.length - stack.pop() - 1;
		}
		
		/*for(int i : answer)
			System.out.print(i + " ");
		*/
	}

}
