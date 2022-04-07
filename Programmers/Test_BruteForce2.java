package programmers;

import java.util.HashSet;
import java.util.Set;

/*
 * 완전탐색(백트래킹)
 */

public class Test_BruteForce2 {

	static int cnt;
	static boolean[] prime;
	static boolean[] visit;
	static Set<Integer> set;
	
	public static boolean isPrime(int number) {
		if(number == 0 || number == 1) return false;
		for(int i = 2; i*i<=number; i++) {
			if(number % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {

		int N = 10000000;
		prime = new boolean[N];
		prime[0] = true;
		prime[1] = true;
		double max = Math.sqrt(N);
		for(int i = 2; i<=max; i++) {
			for(int j = 2*i; j<N; j+=i) {
				prime[j] = true;
			}
		}
		String numbers = "17";
		visit = new boolean[numbers.length()];
		set = new HashSet<>();
		dfs(numbers, "");
		System.out.println(set.size());
	}
	public static void dfs(String origin, String str) {
		if(!str.isBlank() && str.length() > origin.length()) {
			return;
		}
		if(!str.isBlank()) {
			int newNumber = Integer.parseInt(str);
			if(!prime[newNumber]) set.add(newNumber);
		}
		
		for(int i = 0; i<origin.length(); i++) {
			if(!visit[i]) {
				visit[i] = true;
				str += origin.charAt(i);
				dfs(origin, str);
				visit[i] = false;
				str = str.substring(0,str.length()-1);
			}
		}
	}

}
