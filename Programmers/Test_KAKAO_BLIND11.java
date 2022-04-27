package programmers;

import java.io.IOException;
import java.util.HashMap;

/*
 * 2018 KAKAO BLIND RECRUITMENT 1차 뉴스 클러스터링
 * 구현
 */

public class Test_KAKAO_BLIND11 {

	public static void main(String[] args) throws IOException{
		// 'A' - 'Z' = 65 - 90, 'a' - 'z' = 97 - 122
		String str1 = "BAAAA";
		String str2 = "AAA";
		//String match = "[^a-zA-Z]";
		//str1 = str1.replaceAll(match, "");
		//str2 = str2.replaceAll(match, "");
		System.out.println(str1 +", " + str2);
		HashMap<String, Integer> str1Map = new HashMap<>();
		HashMap<String, Integer> str2Map = new HashMap<>();
		
		
		int sum = 0;
		for(int i = 0; i<str1.length()-1; i++) {
			String element = "";
			if(check(str1.charAt(i)) && check(str1.charAt(i+1))) {
				element += str1.charAt(i);
				element += str1.charAt(i+1);
				element = element.toUpperCase();
				
				if(str1Map.containsKey(element)) {
					str1Map.put(element, str1Map.get(element)+1);
				}
				else {
					str1Map.put(element, 1);
				}
				sum+=1;
			}
		}
	
		for(int i = 0; i<str2.length()-1; i++) {
			String element = "";
			if(check(str2.charAt(i)) && check(str2.charAt(i+1))) {
				element += str2.charAt(i);
				element += str2.charAt(i+1);
				element = element.toUpperCase();
				if(str2Map.containsKey(element)) {
					str2Map.put(element, str2Map.get(element)+1);
				}
				else {
					str2Map.put(element, 1);
				}
				sum+=1;
			}
		}
		
		int common = 0;
		for(String key : str1Map.keySet()) {
			if(str2Map.containsKey(key)) {
				common += Math.min(str1Map.get(key), str2Map.get(key));
			}
		}
		sum -= common;
		System.out.println(common +", " + sum);
		
		double commonRate = (double) common/sum * 65536;
		long result = (long) Math.floor(commonRate);
		System.out.println(commonRate + ", " + result);
	}
	
	static boolean check(char c) {
		if((c>=65 && c<=90) || (c>=97 && c<=122)) return true;
		return false;
	}

}
