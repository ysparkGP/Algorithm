import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1780 {
	static int[][] paper;
	static int first, second, third;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divCon(N,0,0);
		System.out.println(first);
		System.out.println(second);
		System.out.println(third);
		
	}
	
	static void divCon(int n, int y, int x) {
		
		if(validCheck(n,y,x)) {
			if(paper[y][x] == -1) {
				first++;
			}
			else if(paper[y][x] == 0) {
				second++;
			}
			else if(paper[y][x] == 1) {
				third++;
			}
			
			return;
		}
		
		divCon(n/3,y,x);
		divCon(n/3,y,x+n/3);
		divCon(n/3,y,x+2*n/3);
		divCon(n/3,y+n/3,x);
		divCon(n/3,y+n/3,x+n/3);
		divCon(n/3,y+n/3,x+2*n/3);
		divCon(n/3,y+2*n/3,x);
		divCon(n/3,y+2*n/3,x+n/3);
		divCon(n/3,y+2*n/3,x+2*n/3);
		
	}
	
	static boolean validCheck(int n, int y, int x) {
		int check = paper[y][x];
		
		for(int i = y; i<y+n; i++) {
			for(int j =x; j<x+n; j++) {
				if(check != paper[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
}
