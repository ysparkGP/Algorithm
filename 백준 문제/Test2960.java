import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test2960 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N-1];
		boolean[] visit = new boolean[N+1];
		for(int i = 0; i<N-1; i++)
			arr[i] = i+2;
		int cnt = 0;
		for(int i = 2; i<=N; i++) {
			for(int j = i; j<=N; j+=i) {
				if(!visit[j]) {
					cnt++;
					visit[j] = true;
					if(cnt == K) {
						System.out.println(j);
						return;
					}
				}
			}
		}
		
	}
}
