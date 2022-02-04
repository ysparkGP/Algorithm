import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1644 {
	static int[] prime;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		prime = new int[N-1];
		visit = new boolean[N+1];
		int index = 0;
		
		for(int i=2; i<=N; i++) { //에라토스테네스의 체
			if(!visit[i]) {
				prime[index] = i;
				for(int j = i*2; j<=N; j+=i) {
					visit[j] = true;
				}
				index++;
			}
		}
		
		
		int start = 0;
		int end = 0;
		int sum = prime[start];
		int count = 0;
		while(start <= end) {
			
			if(sum > N) {
				sum -= prime[start];
				start++;
			}
			else if(sum < N) {
				end++;
				if(end >= index)
					break;
				sum += prime[end];
			}
			else {
				count++;
				end++;
				if(end >= index)
					break;
				sum += prime[end];
			}
		}
		
		System.out.println(count);
	}
}
