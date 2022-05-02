package programmers;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [3차] n진수 게임
 * 구현
 */

public class Test_KAKAO_BLIND22 {

	public static void main(String[] args) {
		int n = 2; // 진법
		int t = 4; // 미리 구할 숫자의 개수
		int m = 2; // 참가인원
		int p = 1; // 튜브의 순서
		
		StringBuilder sb = new StringBuilder();
		int number = 0;
		int turn = 0;
		loop: while(true) {
			int tempNum = number;
			StringBuilder tempSb = new StringBuilder();
			while(true) {
				String value = String.valueOf(tempNum%n);
				int tempValue = Integer.parseInt(value);
				if(tempValue >= 10) {
					switch(tempValue) {
					case 10: value = "A";
					break;
					case 11: value = "B";
					break;
					case 12: value = "C";
					break;
					case 13: value = "D";
					break;
					case 14: value = "E";
					break;
					default: value = "F";
					break;
					}	
				}
				tempSb.append(value);
				tempNum /= n;
				if(tempNum == 0)
					break;
			}
			tempSb = tempSb.reverse();
			for(int i = 0; i<tempSb.length(); i++) {
				if(turn % m == p-1) {
					sb.append(tempSb.charAt(i));
					if(sb.length() >= t) break loop;
				}
				turn+=1;
			}
			
			number+=1;
		}
		// 꿀팁) Integer.toString(int number, int radix); 라는 함수를 사용하면 편리함.
		
		System.out.println(sb.toString());
		

	}

}
