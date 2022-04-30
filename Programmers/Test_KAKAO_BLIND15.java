package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * 2021 KAKAO BLIND RECRUITMENT
 * 순위 검색
 * 문자열, 구현, 이분탐색
 */

public class Test_KAKAO_BLIND15 {

	static Map<String, ArrayList<Integer>> map;
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

		int[] answer = new int[query.length];
		
		// step 1
		map = new HashMap<String, ArrayList<Integer>>();
		for(int i = 0; i<info.length; i++) {
			mapSetting(info[i].split(" "), 0, "");
		}
		
		// step 2
		mapSort();
		
		// step 3
		for(int i = 0; i<query.length; i++) {
			String str = query[i].replaceAll(" and ", "");
			String[] strArr = str.split(" ");
			int result = binarySearch(strArr);
			answer[i] = result;
		}
		for(int i = 0; i<answer.length; i++)
			System.out.print(answer[i] + " ");
		
	}
	
	// 1. map에 info의 모든 문자열 조합과 점수들을 저장
	static void mapSetting(String[] strArr, int depth, String str) {
		if(depth == 4) {
			if(map.containsKey(str)) {
				map.get(str).add(Integer.parseInt(strArr[depth]));
			}
			else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(strArr[depth]));
				map.put(str, list);
			}
			return;
		}
		mapSetting(strArr, depth+1, str+strArr[depth]);
		mapSetting(strArr, depth+1, str+"-");
	}
	
	// 2. value에 저장된 점수들을 얕은복사로 정렬
	static void mapSort() {
		for(String key: map.keySet()) {
			ArrayList<Integer> list = map.get(key);
			Collections.sort(list);
		}
	}
	// 3. query를 받아 이분탐색
	static int binarySearch(String[] query) {
		if(!map.containsKey(query[0])) return 0;
		ArrayList<Integer> list = map.get(query[0]);
		int targetScore = Integer.parseInt(query[1]);

		int start = 0;
		int end = list.size()-1;
		while(start<=end) {
			int mid = (start+end)/2;
			if(list.get(mid) >= targetScore) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		return list.size() - start;
	}

}
