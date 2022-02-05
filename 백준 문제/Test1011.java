package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1011 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());
			
			int sum = 1;
			int val = 1;
			int cnt = 0;
			while(y - x != 0) {
				cnt++;
				x += val;
				if(y - x > sum) {
					sum += ++val;
				}
				else if(y - x < sum) {
					sum -= val--;
				}
				System.out.println(x);
			}
			System.out.println(cnt);
		}
	}
}
