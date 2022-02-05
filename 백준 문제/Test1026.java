package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Test1026 {
	
	static boolean[] visit;
	static int[] A;
	static int[] B;
	static int[] temp;
	static int min = 0;
	static int N;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N];
		A = new int[N];
		B = new int[N];
		temp = new int[N];
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		for(int i = 0; i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for(int i = 0; i<N;i++) {
			B[i] = Integer.parseInt(st.nextToken());
			temp[i] = 0;
			visit[i] = false;
		}
		//Arrays.sort(A);
		for(int i = 0; i<N-1;i++) {
			for(int j = i; j<N; j++) {
				if(B[i] > B[j]) {
					int temp = B[i];
					B[i] = B[j];
					B[j] = temp;
				}
				if(A[i] < A[j]) {
					int temp = A[i];
					A[i] = A[j];
					A[j] = temp;
				}
			}
		}
		
		for(int i = 0; i<N;i++) {
			min += A[i]*B[i];
		}
		//search(0);
		System.out.println(min);
		
	}
	
	
	static void search(int cnt) {
		
		if(cnt >= N) {
			int result = 0;
			for(int i = 0; i<N; i++) {
				result += temp[i]*B[i];
			}
			min = min < result ? min : result;
		}
		
		for(int i = 0; i<N; i++) {
			if(visit[i] == false) {
				visit[i] = true;
				temp[cnt] = A[i];
				search(cnt+1);
				visit[i] = false;
			}
		}
	}
}
