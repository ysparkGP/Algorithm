package programmers;

import java.util.HashMap;
import java.util.Map;

/*
 * 순열 + 구현
 * 2017 카카오코드 본선
 * 단체사진 찍기
 */

public class Test_KAKAO_BLIND7 {

	static int answer;
	static Map<Character, Integer> map;
	public static void main(String[] args) {
		int n = 2;
		String[] data = {"M~C<2", "C~M>1"};
	
		int[] picture = new int[8];
		boolean[] visit = new boolean[9];
		init();
		dfs(0,picture,data, visit);
		
		System.out.println(answer);
	}
	
	static void dfs(int cnt, int[] picture, String[] data, boolean[] visit) {
		if(cnt == 8) {
			if(conditionCheck(data, picture))
				answer++;
			return;
		}
		for(int i = 1; i<=8; i++) {
			if(!visit[i]) {
				visit[i] = true;
				picture[cnt] = i;
				dfs(cnt+1, picture, data, visit);
				visit[i] = false;
			}
		}
	}
	
	static boolean conditionCheck(String[] data, int[] picture) {
		
		for(String str: data) {
			Character subject1 = str.charAt(0);
			Character subject2 = str.charAt(2);
			Character condition = str.charAt(3);
			int conditionNumber = str.charAt(4) - '0';
			
			int subjectNumber1 = map.get(subject1);
			int subjectNumber2 = map.get(subject2);
			int subjectIndex1 = 0;
			int subjectIndex2 = 0;
			
			for(int i = 0; i<picture.length; i++) {
				if(subjectNumber1==picture[i]) subjectIndex1 = i;
				if(subjectNumber2==picture[i]) subjectIndex2 = i;
			}
			if(condition == '>') {
				int diff = Math.abs(subjectIndex1 - subjectIndex2)-1;
				if(diff <= conditionNumber) return false;
			}
			else if(condition == '<') {
				int diff = Math.abs(subjectIndex1 - subjectIndex2)-1;
				if(diff >= conditionNumber) return false;
			}
			else {
				int diff = Math.abs(subjectIndex1 - subjectIndex2)-1;
				if(diff != conditionNumber) return false;
			}
		}
		return true;
	}
	
	static void init() {
		// "A": 어피치, "C": 콘, "F": 프로도, "J": 제이지, "M": 무지, "N": 네오, "R": 라이언, "T": 튜브
		map = new HashMap<>();
		map.put('A',1);
		map.put('C',2 );
		map.put('F',3);
		map.put('J',4);
		map.put('M',5);
		map.put('N',6);
		map.put('R',7);
		map.put('T',8);
	}

}
