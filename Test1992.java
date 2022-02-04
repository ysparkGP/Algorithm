import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1992 {
	static int[][] quad;
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		quad = new int[N][N];
		for(int i = 0; i<N; i++) {
			String str = br.readLine();	
			for(int j = 0; j<N; j++) {
				quad[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		divCon(N,0,0);
		System.out.println(sb.toString());
		
	}
	
	static void divCon(int n, int y, int x) {
		
		if(validCheck(n,y,x)) {
			sb.append(quad[y][x]);
			return;
		}
			
		sb.append("(");
		divCon(n/2, y, x);
		divCon(n/2, y, x+n/2);
		divCon(n/2, y+n/2, x);
		divCon(n/2, y+n/2, x+n/2);
		sb.append(")");
		
	}
	
	static boolean validCheck(int n, int y, int x) {
		int check = quad[y][x];
		for(int i = y; i<y+n; i++) {
			for(int j = x; j<x+n; j++) {
				if(check != quad[i][j])
					return false;
			}
		}
		
		return true;
	}
}
