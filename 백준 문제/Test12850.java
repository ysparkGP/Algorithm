package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 수학, 인접행렬, 분할정복을 이용한 거듭제곱(이진법)
 */

public class Test12850 {
	
	static final int MOD = 1_000_000_007;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int D = Integer.parseInt(br.readLine());
		
		long[][] originalMat = setMat();
		long[][] resultMat = divAndConq(originalMat, D);
		System.out.println(resultMat[0][0]);
	}
	static long[][] setMat() {
		long[][] mat = {
				{0,1,1,0,0,0,0,0},
				{1,0,1,1,0,0,0,0},
				{1,1,0,1,0,1,0,0},
				{0,1,1,0,1,1,0,0},
				{0,0,0,1,0,1,1,0},
				{0,0,1,1,1,0,0,1},
				{0,0,0,0,1,0,0,1},
				{0,0,0,0,0,1,1,0}
		};
		return mat;
	}
	static long[][] productMat(long[][] leftMat, long[][] rightMat) {
		long[][] newMat = new long[8][8];
		for(int row = 0; row<8; row++) {
			for(int col = 0; col<8; col++) {
				long value = 0;
				for(int i = 0; i<8; i++) {
					value += (long)(leftMat[row][i]*rightMat[i][col]) % MOD ;
				}
				newMat[row][col] = value%MOD;
			}
		}
		return newMat;
	}
	
	static long[][] divAndConq(long[][] nowMat, int n){
		if(n == 1) return nowMat;
		
		long[][] resultMat = new long[8][8];
		if(n % 2 == 0) {
			resultMat = productMat(nowMat, nowMat);
			return divAndConq(resultMat, n/2);
		}
		else {
			resultMat = productMat(nowMat, nowMat);
			return productMat(nowMat, divAndConq(resultMat, n/2));
		}
		
	}
	
	static void printMat(int[][] mat, int n) {
		System.out.println(n);
		for(int i=0;i<8;i++) {
			for(int j = 0; j<8;j++)
				System.out.print(mat[i][j] + " ");
			System.out.println();
		}
	}

}
