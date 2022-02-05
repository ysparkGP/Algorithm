package baekjoon;

import java.io.IOException;
import java.util.Scanner;


public class Test1051 {
	static int result = 0;
	static int N,M = 0;
	static int[][] square;
			
	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		square = new int[N][M];
		
		String line = "";
		
		for(int i = 0; i<N;i++) {
			line = sc.next();
			for(int j = 0; j<M;j++) {
				square[i][j] = line.charAt(j) - '0';
			}
		}
		sc.close();
		
		/*int maxLength = Math.max(N, M);
		loop:for(int l = maxLength; l>0; l--) {
			for(int i = 0; i<N-l; i++) {
				for(int j = 0; j<M-l;j++) {
					if((square[i][j] == square[i+l][j]) && (square[i][j] == square[i][j+l]) && (square[i][j] == square[i+l][j+l])) {
						result = l;
						break loop;
					}
				}
			}
		}*/
		
		int minLength = Math.min(N, M);
		for(int l = minLength;l>0;l--) {
			for(int i = 0; i<N-l; i++) {
				for(int j = 0; j<M-l;j++) {
					if((square[i][j] == square[i+l][j]) && (square[i][j] == square[i][j+l]) && (square[i][j] == square[i+l][j+l])) {
						if(result < l)
							result = l;
					}
				}
			}
			
		}
		
		
		System.out.println((result+1) * (result+1));
		
	}
	
}