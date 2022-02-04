import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test2447 {
	static char[][] square;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		square = new char[N][N];
		recursive(N, 0, 0, false);
		
		for(int i = 0; i<N; i++) {
			bw.write(square[i]);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	
	static void recursive(int n, int i, int j, boolean blank) {

		int size = n;
		
		
		if(blank == true) {
			for(int ii = i; ii<i+size; ii++) {
				for(int jj = j; jj<j+size; jj++) {
					square[ii][jj] = ' ';
				}
			}
			return;
		}
		
		if(n == 1) {
			square[i][j] = '*';
			return;
		}
		
		int cnt = 0;
		for(int ii = i; ii<size+i; ii+=size/3) {
			for(int jj = j; jj<size+j; jj+=size/3) {
				cnt++;
				if(cnt != 5)
					recursive(size/3, ii, jj, false);
				else
					recursive(size/3, ii, jj, true);
				
			}
		}
		
			
	}
		
}
