package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * KMP 알고리즘
 */

public class Test1305 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String pattern = br.readLine();
		
		int[] p = new int[L];
		int j = 0;
		for(int i = 1; i<L; i++) {
			
			while(j>0  && pattern.charAt(j)!=pattern.charAt(i)) {
				j = p[j-1];
			}
			if(pattern.charAt(j) == pattern.charAt(i)) {
				p[i] = ++j;
			}
		}
		
		System.out.println(L-p[L-1]);
	}

}
