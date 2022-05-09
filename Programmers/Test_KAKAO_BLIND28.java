package programmers;

import java.util.Arrays;
import java.util.Stack;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [1차] 셔틀버스
 */

public class Test_KAKAO_BLIND28 {

	public static void main(String[] args) {
		int n = 10;
		int t = 60;
		int m = 45;
		String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
		
		int[] minuteTime = new int[timetable.length];
		for(int i =0; i<timetable.length; i++) {
			String[] time = timetable[i].split(":");
			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			minuteTime[i] = hour*60 + minute;
		}

		Arrays.sort(minuteTime);
		int startTime = 9*60;
		int endTime = startTime + (n-1)*t;
		int index = 0;
		int lastTime = 0;
		Stack<Integer> stack = new Stack<>();
		for(int time = startTime; time <= endTime; time+=t) {
			while(stack.size() < m) {
				if(minuteTime[index] <= time) {
					stack.add(minuteTime[index++]);
				}
				else {
					break;
				}
				if(index >= minuteTime.length) break;
			}
			
			if(time == endTime) {
				if(stack.size() == m) {
					lastTime = stack.peek() - 1;
				}
				else {
					lastTime = time;
				}
			}
			stack.clear();
		}
		System.out.println(timeToString(lastTime));
	}
	public static String timeToString(int time) {
		int hour = time/60;
		int minute = time%60;
		StringBuilder sb = new StringBuilder();
		if(hour<10) {
			sb.append(0);
		}
		sb.append(hour);
		sb.append(":");
		if(minute < 10) {
			sb.append(0);
		}
		sb.append(minute);
		return sb.toString();
	}

}
