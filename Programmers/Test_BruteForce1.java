package programmers;

import java.util.ArrayList;
import java.util.List;

/*
 * 완전탐색
 */
public class Test_BruteForce1 {

	public static void main(String[] args) {
		String[] anti = new String[3];
		anti[0] = "";
		anti[1] = "";
		anti[2] = "";
		for(int i = 0; i<2000; i++) {
			anti[0] += "12345";
			anti[1] += "21232425";
			anti[2] += "3311224455";
		}
		int[] result = new int[3];
		int[] answers = {1,3,2,4,2};
		int max = 0;
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<answers.length; j++) {
				if(answers[j] == Character.getNumericValue(anti[i].charAt(j)))
					result[i]++;
			}
			max = Math.max(max, result[i]);
		}
		List<Integer> answerList = new ArrayList<>();
		for(int i = 0; i<3; i++) {
			if(max <= result[i]) answerList.add(i+1);
		}
		int[] answer = answerList.stream().mapToInt(i->i).toArray();
	}

}
