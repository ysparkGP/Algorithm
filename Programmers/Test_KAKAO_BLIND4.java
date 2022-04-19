package programmers;

import java.util.HashMap;
import java.util.Map;

/*
 * 구현
 * 2021 카카오 채용연계형 인턴십
 * 숫자 문자열과 영단어
 */

public class Test_KAKAO_BLIND4 {

	public static void main(String[] args) {
		
		String s = "123";
		
		Map<String, String> map = new HashMap<>();
		map.put("zero","0");
		map.put("one","1");
		map.put("two", "2");
		map.put("three","3");
		map.put("four", "4");
		map.put("five", "5");
		map.put("six", "6");
		map.put("seven","7");
		map.put("eight", "8");
		map.put("nine","9");
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)>=48 && s.charAt(i) <= 57) {
				result.append(s.charAt(i));
			}
			else {
				StringBuilder sb = new StringBuilder();
				while(true) {
					sb.append(s.charAt(i));
					if(map.containsKey(sb.toString())) break;
					i++;
				}
				result.append(map.get(sb.toString()));
			}
		}
		int answer = Integer.parseInt(result.toString());
		System.out.println(answer);

	}

}
