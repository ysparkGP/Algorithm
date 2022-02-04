import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [F(i+1)       [F(i) + F(i-1)
 *          = 
 * F(i)]          F(i) + F(0)]
 * ==>
 * [F(i+1)       [1 1 ^i    [F(1)
 *          =            *
 * F(i)]          0 1]       F(0)]
 */

public class Test2749 {
	static long[][] fiboMat;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		fiboMat = new long[2][2];
		for(int i = 0; i<2; i++) {
			for(int j = 0; j<2; j++) {
				if(i==1 && j==1)
					fiboMat[i][j] = 0;
				else
					fiboMat[i][j] = 1;
			}
		}
		
		long[][] result = divCon(N);
		/*for(int i = 0; i<2; i++) {
			for(int j = 0; j<2; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}*/
		System.out.println(result[1][0]);
	}
	
	static long[][] divCon(long n) {
		if(n == 1) {
			return fiboMat;
		}
		
		long[][] mat = divCon(n/2);
		long[][] resultMat = fiboMulti(mat, mat);
		if(n % 2 == 1) {
			resultMat = fiboMulti(resultMat, fiboMat);
		}
		
		return resultMat;
	}
	
	static long[][] fiboMulti(long[][] A , long[][] B) {
		long[][] result = new long[2][2];
		
		for(int i = 0; i<2;i++) {
			
			for(int j = 0; j<2; j++) {
				long sum = 0;
				for(int k = 0; k<2; k++) {
					sum += ((A[i][k] % 1000000) * (B[k][j] % 1000000))%1000000;
				}

				result[i][j] = sum%1000000;
			}
			
		}
		
		return result;
	}
	
}
