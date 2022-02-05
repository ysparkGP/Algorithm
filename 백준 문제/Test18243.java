package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test18243 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		for(int i = 0; i<N;i++) {
			for(int j = 0; j<N;j++) {
				if(i==j) {
					arr[i][j] = 0;
					continue;
				}
				arr[i][j] = 999999;
			}
		}
		
		for(int i = 0; i<M; i++) {
			String str2 = br.readLine();
			StringTokenizer st2= new StringTokenizer(str2, " ");
			int n = Integer.parseInt(st2.nextToken());
			int m = Integer.parseInt(st2.nextToken());
			arr[n-1][m-1] = 1;
			arr[m-1][n-1] = 1;
		}
		//플로이드 - 워샬
		//k = 거치는 노드
		for(int k = 0; k<N;k++) {
			//i = 출발 노드
			for(int i = 0; i<N;i++) {
				//j = 도착 노드
				for(int j = 0; j<N;j++) {
					if(i == j || j == k || k == i) {
						continue;
					}
					if(arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		int[] result = new int[N];
		int max = 0;
		for(int i = 0; i<N;i++) {
			for(int j = 0; j<N;j++) {
				max = max < arr[i][j] ? arr[i][j] : max;
			}
		}
		if(max >= 7) {
			System.out.print("Big World!");
		}
		else{
			System.out.print("Small World!");
		}
	}
	
}
