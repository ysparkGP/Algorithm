package programmers;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [3차] 압축
 * 구현
 */
public class Test_KAKAO_BLIND20 {

	public static void main(String[] args) {
		String msg = "KAKAO";
		
		// 1. 사전에 있는 단어인지 체크
		// 2. 사전에 없는 단어까지 인덱스가 왔다면 인덱스-1까지 사전의 Value를 출력하고 인덱스까지의 단어를 저장
		HashMap<String, Integer> map = new HashMap<>();
		init(map);
		
		int lastIndex = 27;
		StringBuilder str = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i<msg.length(); i++) {
			str.append(msg.charAt(i));
			if(map.containsKey(str.toString())) {
				continue;
			}
			else {
				list.add(map.get(str.toString().substring(0,str.toString().length()-1)));
				map.put(str.toString(), lastIndex++);
				str = new StringBuilder();
				i--;
			}
		}
		list.add(map.get(str.toString()));
	}
	
	static void init(HashMap<String, Integer> map) {
		for(int i = 1; i<=26; i++) {
			String str = Character.toString(64+i);
			map.put(str, i);
		}
	}

}
