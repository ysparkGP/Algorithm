package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백트래킹
 */

public class Test2661 {

	static int N;
	static int l;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		backTracking("");
	}
	static void backTracking(String str) {
		System.out.println(l++);
		if(str.length() == N) {
			System.out.println(str);
			System.exit(0);
		}
		
		for(int i = 1; i<=3; i++) {
			if(goodSeq(str+i)) {
				backTracking(str+i);
			}
		}
		
	}
	static boolean goodSeq(String str) {
		//System.out.println(l++);
		int x = str.length();
		for(int i = 1; i<=str.length()/2; i++) {
			x -= 2;
			if(str.substring(x,x+i).equals(str.substring(x+i))) {
				return false;
			}
		}
		
		return true;
	}

}
