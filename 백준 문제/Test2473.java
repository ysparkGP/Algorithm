package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 투 포인터
 */

public class Test2473 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] liquid = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) liquid[i] = Long.parseLong(st.nextToken());
		Arrays.sort(liquid);
		
		long answer1 = -1;
		long answer2 = -1;
		long answer3 = -1;
		
		long min = Long.MAX_VALUE;
		loop: for(int i = 0; i<N-2; i++) {
			int left = i+1;
			int right = N-1;
			while(left < right) {
				long sum = liquid[i] + liquid[left] + liquid[right];
				if(Math.abs(sum) < Math.abs(min)) {
					answer1 = liquid[i];
					answer2 = liquid[left];
					answer3 = liquid[right];
					min = sum;
				}
				if(sum < 0) {
					left++;
				}
				else if(sum == 0) {
					break loop;
				}
				else {
					right--;
				}
			}
		}
		
		System.out.println(answer1 + " " + answer2 + " " + answer3);
	}

}
