package programmers;

import java.util.Arrays;

public class Test_Greedy1 {

	static int n;
	public static void main(String[] args) {
		n = 5;
		boolean[] student = new boolean[n+1];
		Arrays.fill(student, true);
		int[] lost = {1,2,4};
		for(int i = 0; i<lost.length; i++) {
			student[lost[i]] = false;
		}
		int[] reverse = {2,3,4,5};
		boolean[] remain = new boolean[n+1];
		for(int i = 0; i<reverse.length; i++) {
			if(!student[reverse[i]]) student[reverse[i]] = true;
			else remain[reverse[i]] = true;
		}
		
		if(!student[n]) {
			if(remain[n]) {
				student[n] = true;
				remain[n] = false;
			}
		}
		if(!student[n]) {
			if(remain[n-1]) {
				student[n] = true;
				remain[n-1] = false;
			}
		}
		
		for(int i = 1; i<n; i++) {
			if(!student[i]) {
				if(remain[i]) {
					student[i] = true;
					remain[i] = false;
					continue;
				}
				if(validCheck(i-1)) {
					if(remain[i-1]) {
						student[i] = true;
						remain[i-1] = false;
						continue;
					}
				}
				if(validCheck(i+1)) {
					if(remain[i+1]) {
						student[i] = true;
						remain[i+1] = false;
						continue;
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 1; i<=n; i++) {
			System.out.print(student[i] + " ");
			if(student[i]) cnt++;
		}
		System.out.println(cnt);
	}
	
	static boolean validCheck(int x) {
		if(x < 1 || x > n) return false;
		return true;
	}

}
