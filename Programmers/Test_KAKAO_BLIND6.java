package programmers;

import java.util.HashMap;
import java.util.Map;

/*
 * 구현
 * 2020 카카오 인턴십
 * 키패드 누르기
 */

public class Test_KAKAO_BLIND6 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		String hand = "right";
		
		// * => 10, 0 => 11, # => 12
		int num = 1;
		Map<Integer,Pair> map = new HashMap<>();
		for(int y = 0; y<4; y++) {
			for(int x = 0; x<3; x++) {
				map.put(num++, new Pair(y,x));
			}
		}
		
		int left = 10;
		int right = 12;
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i<numbers.length; i++) {
			if(numbers[i] == 0)
				numbers[i] = 11;
				
			if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				left = numbers[i];
				answer.append("L");
			}
			else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				right = numbers[i];
				answer.append("R");
			}
			else {
				Pair p = map.get(numbers[i]);
				Pair lp = map.get(left);
				Pair rp = map.get(right);
				
				if(Math.abs(p.y - lp.y) + Math.abs(p.x - lp.x) > Math.abs(p.y - rp.y) + Math.abs(p.x - rp.x)){ // 오른손 위치가 더 가까울때
					right = numbers[i];
					answer.append("R");
				}
				else if(Math.abs(p.y - lp.y) + Math.abs(p.x - lp.x) < Math.abs(p.y - rp.y) + Math.abs(p.x - rp.x)){ // 왼손 위치가 더 가까울때
					left = numbers[i];
					answer.append("L");
				}
				else {
					if(hand.equals("right")) {
						right = numbers[i];
						answer.append("R");
					}
					else {
						left = numbers[i];
						answer.append("L");
					}
				}
			}
		}
		
		System.out.println(answer.toString());
	}

}
