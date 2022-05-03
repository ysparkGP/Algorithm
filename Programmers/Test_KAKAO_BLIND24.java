package programmers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/*
 * 2022 KAKAO BLIND RECRUITMENT
 * 주차 요금 계산
 * 구현
 * HashMap은 key의 순서를 보장하지 않으므로 TreeMap 을 이용하여 정렬된 키값으로 생성
 */

public class Test_KAKAO_BLIND24 {

	public static void main(String[] args) {
		int[] fees = {1, 461, 1, 10}; // 기본시간, 기본요금, 단위시간, 단위요금
		String[] records = {"00:00 1234 IN"};
		
		Map<String, ArrayList<String>> map = new TreeMap<>();
		for(String str : records) {
			String[] splitStr = str.split(" ");
			if(map.containsKey(splitStr[1])) {
				ArrayList<String> list = map.get(splitStr[1]);
				list.add(splitStr[0]);
				map.put(splitStr[1], list);
			}
			else {
				ArrayList<String> list = new ArrayList<>();
				list.add(splitStr[0]);
				map.put(splitStr[1], list);
			}
		}
		
		int[] answer = new int[map.size()];
		int index = 0;
		for(String carNumber : map.keySet()) {
			int time = parkingTime(map.get(carNumber));
			if(time > fees[0]) {
				time = (int) Math.ceil((double) (time - fees[0])/fees[2]);
				answer[index++] = fees[1] + time*fees[3];
			}
			else {
				answer[index++] = fees[1];
			}
		}
		
	}
	
	static int parkingTime(ArrayList<String> list) {
		int time = 0;
		
		for(int i = 0; i<list.size(); i+=2) {
			if(i+1 >= list.size()) { //나간 기록이 없는 정보
				String[] startTimeSplit = list.get(i).split(":");
				int hour = 23 - Integer.parseInt(startTimeSplit[0]);
				int minute = 59 - Integer.parseInt(startTimeSplit[1]);
				time += (hour*60 + minute);
			}
			else {
				String[] endTimeSplit = list.get(i+1).split(":");
				String[] startTimeSplit = list.get(i).split(":");
				int hour = Integer.parseInt(endTimeSplit[0]) - Integer.parseInt(startTimeSplit[0]);
				int minute = Integer.parseInt(endTimeSplit[1]) - Integer.parseInt(startTimeSplit[1]);
				time += (hour*60 + minute);
			}
			
		}
		
		return time;
	}

}
