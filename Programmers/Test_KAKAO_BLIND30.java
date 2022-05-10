package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 2020 카카오 인턴십
 * 보석 쇼핑
 * 구현, 자료구조
 */

public class Test_KAKAO_BLIND30 {

	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList(gems));
		
		Map<String, Integer> map = new HashMap<>();
		Queue<String> que = new LinkedList<>();
		int start = 1;
		int end = 0;
		int minSize = Integer.MAX_VALUE;
		int[] answer = new int[2];
		for(int i = 0; i<gems.length; i++) {
			map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
			que.add(gems[i]);
			end+=1;
			while(map.get(que.peek()) > 1) {
				map.put(que.peek(), map.get(que.peek()) - 1);
				start+=1;
				que.poll();
			}
			if(minSize > end-start && map.size() == set.size()) {
				minSize = end-start;
				answer[0] = start;
				answer[1] = end;
			}
		}
		System.out.println(answer[0] + ", " + answer[1]);
		
	}

}
