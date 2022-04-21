package programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 * dfs
 * 2021 KAKAO BLIND RECRUITMENT 
 * 메뉴 리뉴얼
 */

public class Test_KAKAO_BLIND9 {

	static int max;
	public static void main(String[] args) throws IOException{
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] courses = {2,3,4};
		
		for(int i = 0; i<orders.length; i++) {
			// orders 배열 안의 문자열들을 오름차순으로 정렬
			char[] charArr = orders[i].toCharArray();
			Arrays.sort(charArr);
			orders[i] = new String(charArr);
		}
		
		List<String> resultList = new ArrayList<>();
		for(int i = 0; i<courses.length; i++) {
			HashMap<String, Integer> map = new HashMap<>();
			max = 0;
			for(String str : orders) {
				dfs(map, courses[i], 0, "", str);
			}
			if(map.isEmpty() || max < 2) {
				continue;
			}
			
			List<Entry<String,Integer>> entryList = new ArrayList<>(map.entrySet());
			Collections.sort(entryList, new Comparator<Entry<String, Integer>>(){
				@Override
				public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
					return Integer.compare(e2.getValue(), e1.getValue());
				}
			});
			
			for(int j = 0; j<entryList.size(); j++) {
				if(max == entryList.get(j).getValue()) resultList.add(entryList.get(j).getKey());
				else break;
			}
		}
		Collections.sort(resultList, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return str1.compareTo(str2);
			}
		});
		String[] answer = new String[resultList.size()];
		answer = resultList.toArray(answer);
	}
	
	static void dfs(HashMap<String,Integer> map, int cnt, int index, String str, String original) {
		
		if(cnt == 0) {
			if(map.containsKey(str)) {
				map.put(str,map.get(str)+1);
				//System.out.println(str+", "+map.get(str) + ", " + max);
			}
			else {
				map.put(str, 1);
			}
			max = Math.max(max, map.get(str));
			return;
		}
		if(index >= original.length()) return;
		dfs(map,cnt-1, index+1, str+original.charAt(index), original);
		dfs(map,cnt, index+1, str, original);
	}

}
