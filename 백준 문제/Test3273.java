import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//투 포인터
public class Test3273 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		
		if(n == 1) {
			System.out.println(0);
			return;
		}
		
		Arrays.sort(a);
		
		int count = 0;
		int start = 0;
		int end = n-1;
		while(start < end) {
			int sum = a[start] + a[end];
			if(sum == x) {
				count++;
				start++;
				end--;
			}
			else if(sum < x) {
				start++;
			}
			else if(sum > x) {
				end--;
			}
			
		}
		
		System.out.println(count);
	}
}
