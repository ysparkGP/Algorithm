package programmers;

/*
 * 2022 KAKAO BLIND RECRUITMENT
 * k진수에서 소수 개수 구하기
 * 문자열, 소수판별법
 */

public class Test_KAKAO_BLIND23 {

	public static void main(String[] args) {
		int n = 1100011;
		int k = 10;
		
		/*StringBuilder sb = new StringBuilder();
		while(n!=0) {
			sb.append(n%k);
			n/=k;
		}
		
		String str = sb.reverse().toString();
		String[] splitStr = str.replaceAll("[0]+", "-").split("-");
		*/
		
		String str = Integer.toString(n, k);
		String[] splitStr = str.split("[0]+");
		int answer = 0;
		for(String st: splitStr) {
			if(isPrime(Long.parseLong(st))) answer++;
		}
		System.out.println(answer);
	}
	static boolean isPrime(long N) {
		if(N<2) return false;
		
		for(int i = 2; i<=Math.sqrt(N); i++) {
			if(N%i == 0) return false;
		}
		
		return true;
	}

}
