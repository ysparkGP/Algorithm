package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 투 포인터
 */

public class Test2467 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] liquid = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = N-1;
		int min = Integer.MAX_VALUE;
		int leftAnswer = liquid[start];
		int rightAnswer = liquid[end];
		while(start < end) {
			int sum = liquid[start] + liquid[end];
			if(Math.abs(sum) < min) {
				leftAnswer = liquid[start];
				rightAnswer = liquid[end];
				min = Math.abs(sum);
			}
			if(sum < 0) {
				start++;
			}
			else if(sum == 0) {
				break;
			}
			else {
				end--;
			}
		}
		System.out.println(leftAnswer +" " + rightAnswer);
	}

}
