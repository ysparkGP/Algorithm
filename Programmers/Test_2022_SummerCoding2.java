package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 프로그래머스 2022 썸머 코딩 2번
 */

public class Test_2022_SummerCoding2 {

	static class Person{
		String name;
		ArrayList<Integer> rooms = new ArrayList<>();
		Person(String name, ArrayList<Integer> rooms){
			this.name = name;
			this.rooms = rooms;
		}
	}
	public static void main(String[] args) {
		String[] rooms = {"[1234]None,Of,People,Here","[5678]Wow"};
		int target = 1234;
		Map<String, ArrayList<Integer>> map = new HashMap<>();
		for(int i = 0; i<rooms.length; i++) {
			String str = rooms[i].replaceAll("\\[", "");
			String[] strArr = str.split("\\]");
			int roomNumber = Integer.parseInt(strArr[0]);
			String[] nameArr = strArr[1].split(",");
			for(String name: nameArr) {
				if(map.containsKey(name)) {
					ArrayList<Integer> list = map.get(name);
					list.add(roomNumber);
					map.put(name, list);
				}
				else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(roomNumber);
					map.put(name, list);
				}
			}
		}
		
		List<Person> resultList = new ArrayList<>();
		// 방에 지정된 자리가 없는 사람, 있는 사람 제외
		for(String key: map.keySet()) {
			if(!map.get(key).contains(target))
				resultList.add(new Person(key, map.get(key)));
			
		}
		
		Collections.sort(resultList, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				if(p1.rooms.size() == p2.rooms.size()) {
					
					int p1Dis = Integer.MAX_VALUE;
					int p2Dis = Integer.MAX_VALUE;
					for(Integer room: p1.rooms) {
						p1Dis = Math.min(p1Dis,Math.abs(target - room));
					}
					for(Integer room: p2.rooms) {
						p2Dis = Math.min(p2Dis,Math.abs(target - room));
					}
					
					if(p1Dis == p2Dis) {
						return p1.name.compareTo(p2.name);
					}
					
					return p1Dis - p2Dis;
				}
				
				return p1.rooms.size() - p2.rooms.size();
			}
		});
		String[] answer = new String[resultList.size()];
		for(int i = 0; i<resultList.size(); i++) {
			answer[i] = resultList.get(i).name;
		}
		
	}

}
