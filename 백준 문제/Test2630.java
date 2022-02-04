import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test2630 {
	static int[][] color;
	static int white;
	static int blue;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		color = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divCon(N,0,0);
		System.out.println(white);
		System.out.println(blue);

	}
	
	static void divCon(int n, int y, int x) {
		
		if(validCheck(n,y,x)) {
			
			if(color[y][x] == 0) {
				white++;
			}
			else if(color[y][x] == 1) {
				blue++;
			}
			
			return;
		}
		
		divCon(n/2, y, x);
		divCon(n/2, y, x+n/2);
		divCon(n/2, y+n/2, x);
		divCon(n/2, y+n/2, x+n/2);
	}
	
	static boolean validCheck(int n, int y, int x) {
		int check = color[y][x];
		for(int i = y; i<y+n; i++) {
			for(int j = x; j<x+n; j++) {
				if(check != color[i][j])
					return false;
			}
		}
		
		return true;
	}
}
