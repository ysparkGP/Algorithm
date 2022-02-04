import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1629 {
	static long A;
	static int B,C;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		A = Long.parseLong(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(div(A,B));
	}
	
	/*모듈러 합동 공식
	(A * B) % C = ((A % C)*(B % C)) % C
	(A + B) % C = ((A % C) + (B % C)) % C
	*/
	
	static long div(long value, int exp) {
		
		if(exp == 1)
			return value % C;
		
		long result = div(value, exp/2); //11 -> 5 -> 2 -> 1
		
		result *= result;
		result %= C;
		
		if(exp%2 == 1) //exp가 홀 수
		{
			result *= A%C;
			result %= C;
		}
		//System.out.println(result);
		return result;
	}
}
