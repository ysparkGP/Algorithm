package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 구현
 * 2018 KAKAO BLIND RECRUITMENT
 * 추석 트래픽
 */

public class Test_KAKAO_BLIND2 {

	public static void main(String[] args) {
		String[] lines = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"};
		
		List<Integer> startEndMsList = new ArrayList<>();
		
		for(int i = 0; i<lines.length; i++) {
			String[] timeAndProcess = new String[2];
			StringTokenizer st = new StringTokenizer(lines[i], " ");
			st.nextToken();// 날짜(쓰레기 값 버림)
			timeAndProcess[0] = st.nextToken(); // hh:MM:ss.SSS
			timeAndProcess[1] = st.nextToken(); // ~s
			int[] time = timeToMs(timeAndProcess);
			startEndMsList.add(time[0]);
			startEndMsList.add(time[1]);
		}
		int answer = 0;
		for(Integer check : startEndMsList) {
			int startPoint = check;
			int endPoint = check + 999;
			
			int cnt = 0;
			for(int i = 0; i<startEndMsList.size(); i+=2) {
				int startMs = startEndMsList.get(i);
				int endMs = startEndMsList.get(i+1);
				if(startPoint > endMs || endPoint < startMs) continue;
				cnt++;
			}
			answer = Math.max(cnt, answer);
		}
		System.out.println(answer);
	}
	static int[] timeToMs(String[] timeAndProcess) {
//		System.out.println(timeAndProcess[0] + ", "+ timeAndProcess[1]);
		// 반환값은 시작 시간과 응답 시간
		int[] startAndEnd = new int[2];
		StringTokenizer st = new StringTokenizer(timeAndProcess[0], ":");
		int hourMs = Integer.parseInt(st.nextToken()) * 60 * 60 * 1000;
		int minuteMs = Integer.parseInt(st.nextToken()) * 60 * 1000;
		int secondsMs = (int) (Double.parseDouble(st.nextToken()) * 1000);
		//System.out.println(hourMs + ", "  + minuteMs + ", " + secondsMs);
		
		int endTimeMs = hourMs + minuteMs + secondsMs;
		String process = timeAndProcess[1];
		process = process.replace("s","");
		int processMs = (int) (Double.parseDouble(process) * 1000);
		int startTimeMs = endTimeMs - processMs + 1;
		
		startAndEnd[0] = startTimeMs;
		startAndEnd[1] = endTimeMs;
		
		return startAndEnd;
	}

}
