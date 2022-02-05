package programmers;
import java.io.IOException;
import java.util.Arrays;

public class Binary_Search {
	public static void main(String[] args) throws IOException {
		
		long answer = 0;
		
		long n = 1000000000;
		int[] times = {7,1000000000};
		
		Arrays.sort(times);
		long start = 1;
		long end = times[times.length-1] * n;
		System.out.println(end);
		while(start <= end) { //lower bound
			long mid = (start + end) / 2;
			long result = cal(mid, times);
			
			if(n > result) { // midŸ������ ó���� �� �ִ� ��� ���� n ���� ���� ��,
				start = mid + 1;
			}
			else if(n < result) { // midŸ������ ó���� �� �ִ� ��� ���� n ���� ���� ��,
				end = mid - 1;
			}
			else { //midŸ������ ó���� �� �ִ� ��� ���� n�� ���� ��,
				end = mid - 1;
			}
		}
		
		System.out.println(start);
	}
	
	static long cal(long mid, int[] times) {
		long process = 0;
		
		for(int i = 0; i<times.length; i++) {
			process += (mid/times[i]);
		}
		
		return process;
	}
}
