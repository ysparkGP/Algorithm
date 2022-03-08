package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * stack 기초
 */

public class Test_Stack1 {
	
	public static void main(String[] args) {
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		List<Integer> result = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		for(int i = progresses.length-1; i>=0; i--) {
			stack.push(progresses[i]);
		}
		int index = 0;
		int days = 1;
		while(true) {
			int cnt = 0;
			while(!stack.isEmpty() && stack.peek()+speeds[index]*days >= 100) {
				stack.pop();
				index++;
				cnt++;
			}
			
			if(cnt > 0) result.add(cnt);
			if(index >= progresses.length) break;
			days++;
		}
		
		Integer[] temp = result.toArray(new Integer[result.size()]);
		int[] answer = Arrays.stream(temp).mapToInt(Integer::intValue).toArray();
	}

}
