package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1058 {
	public static void main(String[] args) throws IOException {
		// A가 B의 2-친구가 되기 위해선
		// 서로 친구이거나
		// A와 친구이고 B와 친구인 C가 존재해야한다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		
		for(int i = 0; i < N;i++) {
			String str = br.readLine();
			for(int j = 0; j<N;j++) {
				char ch = str.charAt(j);
				if(ch == 'Y')
					arr[i][j] = 1;
				else
					arr[i][j] = 999999;
			}
		}
		
		int[][] newArr = new int[N][N];
		
		//플로이드-와샬
		//k = 거쳐가는 다리
		for(int k = 0;k<N;k++) {
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
			result[i] = 0;
			for(int j = 0; j<N;j++) {
				if(arr[i][j] <= 2) {
					result[i] += 1;
				}
			}
			max = max<result[i] ? result[i] : max;
		}
		System.out.print(max);
	}
}
