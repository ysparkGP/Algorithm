package programmers;

import java.io.IOException;
import java.util.Arrays;

public class Greedy {
	public static void main(String[] args) throws IOException{
		int n = 5;
		int[] lost = {2,3,4};
		int[] reverse = {1,2,3};
		int answer = 0;
		
		/*
		Arrays.sort(lost);
		Arrays.sort(reverse);
		
		boolean[] result = new boolean[n+1];
		Arrays.fill(result, true);
		for(int i = 0; i<lost.length; i++)
			result[lost[i]] = false;
		
		for(int i = 0; i<reverse.length; i++) {
			if(result[reverse[i]]) {
				if(reverse[i] > 1 && result[reverse[i] - 1] == false) {
					result[reverse[i] - 1] = true;
				}
				
				else if(reverse[i] < n && result[reverse[i] + 1] == false) {
					result[reverse[i] + 1] = true;
				}
			}
			
			else {
				result[reverse[i]] = true;
			}
		}
		
		for(int i = 1; i<result.length; i++) {
			if(result[i])
				answer++;
			
			System.out.print(result[i] + " ");
		}
		*/
		
		System.out.println(answer);
	}
}
