import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test1920 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		long[] arr = new long[N];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		long[] can = new long[M+1];
		for(int i = 0; i<M; i++) {
			can[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i = 0; i<M; i++) {
			
			int min = 0;
			int max = N-1;
			int mid = 0;
			boolean flag = false;
			while(min <= max) {
				mid = (min+max) / 2;
				//System.out.println(can[i] + ", " + min + ", " + max + ", " + mid + ", " + arr[mid]);
				
				if(can[i] < arr[mid]) {
					max = mid - 1;
				}
				else if(can[i] > arr[mid]) {
					min = mid + 1;
				}
				else if(can[i] == arr[mid]) {
					flag = true;
					System.out.println(1);
					break;
				}
				
			}
			
			if(flag == false) {
				System.out.println(0);
			}
			
			
		}
		
	}
}
