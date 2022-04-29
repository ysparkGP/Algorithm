package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 2022 카카오 인턴십
 * 수식 최대화
 * 구현, 순열, 정규식
 */

public class Test_KAKAO_BLIND13 {

	static long max;

	static String[] totalOperator = {"*","-","+"};
	static LinkedList<Long> numList;
	static LinkedList<String> operatorList;
	public static void main(String[] args) {
		String expression = "100-200*300-500+20";
		String[] operandStr = expression.split("[-+*]");
		String[] operatorStr = expression.split("[0-9]+");
		numList = new LinkedList<>();
		operatorList = new LinkedList<>();
		
		for(String str: operandStr) numList.add(Long.parseLong(str));
		for(int i = 1; i<operatorStr.length;i ++) operatorList.add(operatorStr[i]);
		
		max = 0;
		boolean[] visit = new boolean[totalOperator.length];
		List<String> rank = new ArrayList<>();
		permutation(visit, rank,0);
		System.out.println(max);
	}
	
	static void permutation( boolean[] visit, List<String> rank, int index) {
		
		if(rank.size() == totalOperator.length) {
						
			LinkedList<Long> copyNumList = new LinkedList<Long>(numList);
			LinkedList<String> copyOperatorList = new LinkedList<String>(operatorList);
			
			for(String operator: rank) {
				for(int i = 0; i<copyOperatorList.size(); i++) {
					if(operator.equals(copyOperatorList.get(i))) {
						long operand1 = (long) copyNumList.get(i);
						long operand2 = (long) copyNumList.get(i+1);
						
						long result = calculation(operand1, operand2, operator);
						
						copyNumList.remove(i);
						copyNumList.remove(i);
						copyOperatorList.remove(i);
						
						copyNumList.add(i, result);
						i--;
					}
				}
			}
			max = Math.max(Math.abs(copyNumList.get(0)), max);
			
			return;
		}
		
		for(int i = 0; i<totalOperator.length; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			rank.add(totalOperator[i]);
			permutation(visit, rank, index+1);
			visit[i] = false;
			rank.remove(index);
		}
		
	}
	static long calculation(long operand1, long operand2, String operator) {
		if(operator.equals("*")) {
			return (long) operand1 * operand2;
		}
		else if(operator.equals("+")) {
			return (long) operand1 + operand2;
		}
		else {
			return (long) operand1 - operand2;
		}
	}

}
