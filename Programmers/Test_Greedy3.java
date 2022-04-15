package programmers;

import java.util.Stack;

public class Test_Greedy3 {

	static StringBuffer max = new StringBuffer("-1");
	public static void main(String[] args) {
		String number = "1231234";
		int n = 3;
		
		/* 일부 시간 초과
		for(int i = 0; i<n; i++) {
			//System.out.println(number);
			StringBuffer sb = new StringBuffer(number.substring(1,number.length()));
			for(int j = 1; j<number.length(); j++) {
				StringBuffer tempSb = new StringBuffer(number.substring(0,j) + number.substring(j+1,number.length()));
				if(sb.compareTo(tempSb) < 0) sb = tempSb;
				System.out.print(sb.toString() + " 와 " + tempSb.toString() + " 비교 ");
				System.out.println(" : " +sb.compareTo(tempSb));
			}
			number = sb.toString();
		}
		*/
		
		//boolean[] visit = new boolean[n.length()];		
		//permutation(n, 0, k, visit);
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		int digit = n+1;
		for(int i = 0; i<number.length()-n; i++) {
			int max = 0;
			for(int j = index; j<digit; j++) {
				if(number.charAt(j) - 48 > max) {
					max = number.charAt(j) - 48;
					index = j+1;
				}
			}
			digit++;
			sb.append(max);
		}
		
		char[] result = new char[number.length() - n];
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i<number.length(); i++) {
			char c = number.charAt(i);
			while(!stack.isEmpty() && stack.peek() < c && n-- > 0) {
				stack.pop();
			}
			stack.push(c);
		}
		for(int i = 0; i<result.length; i++) {
			result[i] = stack.get(i);
		}
		String answer = new String(result);
		
		//System.out.println(sb.toString());
	}
	/* 시간초과
	static void permutation(String number, int cnt, int k, boolean[] visit) {
		if(cnt == k) {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i<number.length(); i++) {
				if(visit[i]) continue;
				sb.append(number.charAt(i));
			}
			if(max.compareTo(sb) < 0) max = sb;
		}
		for(int i = 0; i<number.length(); i++) {
			if(!visit[i]) {
				visit[i] = true;
				permutation(number, cnt+1, k,visit);
				visit[i] = false;
			}
		}
		
	}
	*/

}
