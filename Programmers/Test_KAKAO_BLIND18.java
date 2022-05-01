package programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [1차] 캐시
 * 큐, 구현
 */

public class Test_KAKAO_BLIND18 {

	public static void main(String[] args) {
		int cacheSize = 5;
		String[] cities = {"A","A","A"};
		for(int i = 0; i<cities.length; i++) {
			cities[i] = cities[i].toLowerCase();
		}
		
		int answer = 0;
		Queue<String> que = new LinkedList<>();
		for(int i = 0; i<cities.length; i++) {
			if(cacheSize == 0) {
				answer+=5;
				continue;
			}
			if(que.contains(cities[i])) {
				Queue<String> tempQue = new LinkedList<>();
				while(!que.isEmpty()) {
					tempQue.add(que.poll());
				}
				while(!tempQue.isEmpty()) {
					String str = tempQue.poll();
					if(str.equals(cities[i])) continue;
					que.add(str);
				}
				que.add(cities[i]);
				answer+=1;
			}
			else {
				if(que.size() >= cacheSize) {
					que.poll();
					que.add(cities[i]);
				}
				else {
					que.add(cities[i]);
				}
				answer+=5;
			}
		}
		
		System.out.println(answer);
	}

}
