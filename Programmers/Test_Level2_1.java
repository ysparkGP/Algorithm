package programmers;

public class Test_Level2_1 {

	public static void main(String[] args) {

		int[] arr = {1,2,3};
		int result = arr[0];
		int answer = 0;
		for(int i = 1; i<arr.length; i++) {
			if(result > arr[i]) {
				int temp = result;
				result = gcd(result, arr[i]);
				result = temp*arr[i] / result;
			}
			else {
				int temp = result;
				result = gcd(arr[i], result);
				result = temp*arr[i] / result;
			}
			//System.out.println(result);
			answer = result;
		}
		System.out.println(answer);
	}

	static int gcd(int a, int b) {
		System.out.println(a+", "+b);
		while(b != 0) {
			int r = a%b;
			a=b;
			b=r;
		}
		return a;
	}
}
