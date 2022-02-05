package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 거리에 대한 이분탐색
public class Test2110 {
	
	static int[] x;
	static int N;
	static int C;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		x = new int[N];
		for(int i = 0; i<N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(x);
		
		int start = 1;
		int end = x[N-1];
		
		while(start <= end) {
			
			int mid = (start+end) / 2;
			
			if(job(mid) == true) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
			
		}
		//start = upper bound
		System.out.println(start-1);
		
	}
	
	static boolean job(int mDistance) {
		int c = C-1;
		int prevIndex = 0;
		
		for(int i = 1; i<N; i++) {
			if(x[i] - x[prevIndex] >= mDistance) {
				c--;
				prevIndex = i;
			}
			if(c <= 0) {
				return true;
			}
		}
		
		return false;
	}
}
