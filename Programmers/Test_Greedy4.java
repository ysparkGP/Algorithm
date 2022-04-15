package programmers;

import java.util.Arrays;

public class Test_Greedy4 {

	public static void main(String[] args) {
		int[] people = {70,50,80,50};
		int limit = 100;
		Arrays.sort(people);
		
		int answer = 0;
		int left = 0;
		int right = people.length-1;
		while(left <= right) {
			int sum = people[left] + people[right];
			
			if(left!=right && sum <= limit) {
				left++;
			}
			right--;
			answer++;
		}
		
		System.out.println(answer);
	}

}
