package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1058 {
	public static void main(String[] args) throws IOException {
		// A�� B�� 2-ģ���� �Ǳ� ���ؼ�
		// ���� ģ���̰ų�
		// A�� ģ���̰� B�� ģ���� C�� �����ؾ��Ѵ�.
		
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
		
		//�÷��̵�-�ͼ�
		//k = ���İ��� �ٸ�
		for(int k = 0;k<N;k++) {
			//i = ��� ���
			for(int i = 0; i<N;i++) {
				//j = ���� ���
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
