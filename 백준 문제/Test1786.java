package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * KMP 알고리즘
 * https://aiemag.tistory.com/46
 * 좋은 설명
 */

public class Test1786 {

	static int[] p;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		
		p = new int[P.length()];
		getP(P);
		kmp(T,P);
	}
	
	static void getP(String P) {
		int j = 0;
		for(int i = 1; i<P.length(); i++) {
			while(j > 0 && P.charAt(i) != P.charAt(j)) {
				// j-1 번째 까지는 접두사와 접미사가 일치했다는 뜻이므로 j-1번째 인덱스가 가리키는 위치까지 이동
				// => 일치한 접두사부터 다시 시작
				j = p[j-1];
			}
			if(P.charAt(i) == P.charAt(j)) {
				p[i] = ++j;
			}
		}
		
		/*for(int i = 0; i<P.length(); i++) {
			System.out.print(p[i] + " ");
		}*/
	}
	
	static void kmp(String T, String P) {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		int j = 0;
		for(int i = 0; i<T.length(); i++) {
			while(j>0 && T.charAt(i) != P.charAt(j)) {
				j = p[j-1];
			}
			
			if(T.charAt(i) == P.charAt(j)){
				j++;
			}
			if(j >= P.length()) {
				// 비교가 끝났다면 처음 위치부터 시작하는 것이 아니라
				// 맨 마지막 문자의 부분문자열 정보를 이용한다.
				// ex) banana, ana
				cnt++;
				j = p[j-1];
				sb.append(i-P.length()+2 + " ");
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}

}
