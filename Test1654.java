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
			
			// K���� ������ mid��ŭ �߶��µ�, N���� ������ ���
			// mid�� �ٿ����Ѵ�.
			if(count < N) {
				max = mid;
			}
			
			// K���� ������ mid��ŭ �߶��µ�, N���� ũ�ų� ���� ���
			// mid�� Ű�����Ѵ�.
			else if(count >= N) {
				min = mid + 1;
			}
		}
		
		//System.out.println(min + ", " + max + ", " + mid + ", " + count);
		
		
		// ������ ������� ������ min�� max�� ���ų� Ŀ���� ��,
		// �� ��� mid���� ���� ����� N���� �۴ٸ� max�� + 1
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
