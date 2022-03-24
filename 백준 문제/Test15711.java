package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 골드바흐의 추측 - 정수론
 * 1. A+B 가 짝수인 경우
 * -> 짝수인 수는 2를 제외하고 모두 소수의 합으로 표현이 가능하다고 주장, 10^18 이하의 숫자에 대해서는 해당 주장이 참으로 증명
 * -> 즉, 2를 제외하고는 모든 짝수는 두 소수의 합으로 표현이 가능하다.
 * 
 * 2. A+B 가 홀수인 경우
 * 홀수는 짝수 + 홀수 로만 표현이 가능하고, 이것은 짝수인 소수 + 홀수인 소수가 되어야한다.
 * 짝수인 소수는 2 밖에 없으므로 (A+B)-2가 소수인지 아닌지 판별하면 된다.
 * 
 * 3. A+B 가 최대 4*10^12 이므로 루트를 씌운 2*10^6 이하의 소수만 알고있으면 판별이 가능하다.(에라토스테네스의 체)
 * 
 * 4. A+B가 2*10^6 보다 큰 수가 들어온다면 3에서 구한 소수들로 나누면서 판별하면 되겠다.
 */

public class Test15711 {
	static int MAX = 2*(int)Math.pow(10, 6);
	static boolean prime[];
	static List<Integer> era;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		prime = new boolean[MAX+1];
		prime[0] = prime[1] = true;
		era = new ArrayList<>();
		era();
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long ab = a+b;
			if(ab%2 == 0) { // 짝수
				if(ab != 2) System.out.println("YES");
				else System.out.println("NO");
			}
			else { // 홀수
				ab -= 2;
				if(ab <= MAX) {
					if(!prime[(int)ab]) System.out.println("YES");
					else System.out.println("NO");
				}
				else {
					boolean tf = true;
					for(Integer i : era) {
						if(ab%i == 0) {  // 소수가 아님
							tf = false;
							break;
						}
					}
					if(tf) System.out.println("YES");
					else System.out.println("NO");
					
				}
			}
		}
	}
	
	static void era() {
		for(int i = 2; i<=MAX; i++) {
			if(!prime[i]) { // 소수라면
				era.add(i);
				for(int j = i*2; j<=MAX; j+=i) {
					prime[j] = true;
				}
			}
		}
	}

}
