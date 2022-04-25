package programmers;

/*
 * 구현
 * 2020 KAKAO BLIND RECRUITMENT 괄호 변환
 */

public class Test_KAKAO_BLIND10 {

	public static void main(String[] args) {
		String p = "()))((()";
		String answer = "";
		
		if(check(p)) {
			answer = p;
		}
		else {
			answer = split(p);
		}
		
		System.out.println(answer);
	}
	
	// 올바른 괄호 문자열 체크
	static boolean check(String str) {
		
		if(str.charAt(0) == ')') return false;
		
		int right = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '(') right++;
			else right--;
			
			// (가 더 많아진 시점붙머 올바른 문자열이 아님
			if(right < 0) return false;
		}
		
		return true;
	}
	static String reverse(String str) {
		StringBuilder rStr = new StringBuilder();
		for(int i = 1; i<str.length()-1; i++) {
			if(str.charAt(i) == '(') rStr.append(")");
			else rStr.append("(");
		}
		return rStr.toString();
	}
	
	static String split(String str) {
		if(str.isBlank()) return "";
		
		StringBuilder u = new StringBuilder();
		StringBuilder v = new StringBuilder();
		
		// 균형잡힌 괄호 문자열 u, v로 분리... u는 분리할 수 없는 균헝잡힌 괄호 문자열, v는 빈 문자열 가능
		int right = 0;
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == '(') right++;
			else right--;
			
			if(right ==0) {
				u.append(str.substring(0,i+1));
				v.append(str.substring(i+1,str.length()));
				if(check(u.toString())) {
					u.append(split(v.toString()));
				}
				else {
					StringBuilder result = new StringBuilder();
					result.append("(");
					result.append(split(v.toString()));
					result.append(")");
					result.append(reverse(u.toString()));
					return result.toString();
				}
				
				break;
			}
		}
		
		return u.toString();
		
	}
}
