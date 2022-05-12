package programmers;

/*
 * 구현, 구간합, 슬라이딩 윈도우
 */

public class Test_KAKAO_BLIND33 {

	public static void main(String[] args) {
		String play_time = "50:00:00";
		String adv_time = "50:00:00";
		String[] logs = {
				"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
		};
		
		long[] stackTime = new long[360_000];
		for(int i = 0; i<logs.length; i++) {
			String[] timeStartEnd = logs[i].split("-");
			int startTime = strToTime(timeStartEnd[0]);
			int endTime = strToTime(timeStartEnd[1]);
			stackTime[startTime]+=1;
			stackTime[endTime]-=1;
		}
		// 시청시간 누적합 만들기
		for(int i = 1; i<360_000; i++) {
			stackTime[i] += stackTime[i-1];
		}
		for(int i = 1; i<360_000; i++) {
			stackTime[i] += stackTime[i-1];
		}
		
		int advTime = strToTime(adv_time);
		int playTime = strToTime(play_time);
		long maxStackTime = stackTime[advTime-1];
		int maxStackIndex = 0;
		for(int i = 0; i+advTime<=playTime; i++) {
			long tempStackTime = stackTime[advTime+i] - stackTime[i];
			if(maxStackTime < tempStackTime) {
				maxStackTime = tempStackTime;
				maxStackIndex = i+1;
			}
		}
		String answer = indexTimeToStr(maxStackIndex);
		System.out.println(answer);
	}
	static String indexTimeToStr(int index) {
		String hour = Integer.toString(index/3600);
		index %= 3600;
		String minute = Integer.toString(index/60);
		index %= 60;
		String seconds = Integer.toString(index);
		
		StringBuilder sb = new StringBuilder();
		if(hour.length() == 1) sb.append(0);
		sb.append(hour+":");
		if(minute.length() == 1) sb.append(0);
		sb.append(minute+":");
		if(seconds.length() == 1) sb.append(0);
		sb.append(seconds);
		return sb.toString();
	}
	static int strToTime(String time) {
		String[] splitTime = time.split(":");
		int hour = Integer.parseInt(splitTime[0]) * 60 * 60;
		int minute = Integer.parseInt(splitTime[1]) * 60;
		int second = Integer.parseInt(splitTime[2]);
		
		return hour+minute+second;
	}

}
