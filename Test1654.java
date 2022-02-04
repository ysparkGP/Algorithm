import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1654 {
	static long[] lan;
	static int K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		lan = new long[K+1];
		long max = 0;
		long min = 1;
		for(int i = 1; i<=K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			max = Math.max(lan[i], max);
		}
		
		long mid = (max+min)/2;
		int count = cal(mid);
		
		//System.out.println(min + ", " + max + ", " + mid + ", " + count);
		
		if(min == max) {
			System.out.println(1);
			return;
		}
		
		while(min < max) {
			mid = (min + max) / 2;
			count = cal(mid);
			
			//System.out.println(min + ", " + max + ", " + mid + ", " + count);
			
			// K개의 랜선을 mid만큼 잘랐는데, N보다 부족할 경우
			// mid를 줄여야한다.
			if(count < N) {
				max = mid;
			}
			
			// K개의 랜선을 mid만큼 잘랐는데, N보다 크거나 같을 경우
			// mid를 키워야한다.
			else if(count >= N) {
				min = mid + 1;
			}
		}
		
		//System.out.println(min + ", " + max + ", " + mid + ", " + count);
		
		
		// 범위가 좁힐대로 좁혀서 min이 max와 같거나 커졋을 때,
		// 그 결과 mid값의 연산 결과가 N보다 작다면 max에 + 1
		if(cal((max+min) / 2) < N) {
			System.out.println(max - 1);
			return;
		}
		else {
			System.out.println(max);
			return;
		}
		
	}
	
	static int cal(long mid) {
		
		int count = 0;
		
		for(int i = 1; i<=K; i++) {
			long l = lan[i];
			while(l - mid >= 0) {
				
				l = l - mid;
				count++;
			}
		}
		
		return count;
	}
}
