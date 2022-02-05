package baekjoon;

import java.io.BufferedReader;

//그리디 알고리즘, 너비 우선 탐색

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test16953 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		//Queue<Integer> que = new LinkedList<>();
		int cnt = 0;
		while(B > A) {
			//System.out.println(B);
			if(B%2 == 0) {
				B = B/2;
				cnt++;
			}
			else if(B%2 == 1) {
				if(B%10 == 1) {
					B = B/10;
					cnt++;
				}
				else {
					System.out.println(-1);
					return;
				}
			}
		}
		
		if(B==A) {
			System.out.println(cnt+1);
			
		}
		else {
			System.out.println(-1);
		}
		
	}
}
