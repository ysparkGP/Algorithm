package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [3차] 방금그곡
 */

public class Test_KAKAO_BLIND19 {

	static class Info{
		int index;
		int time;
		Info(int index, int time){
			this.index = index;
			this.time = time;
		}
	}
	public static void main(String[] args) {
		String m = "ABCDEFG";
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String[][] fullMusicInfo = new String[musicinfos.length][2]; // 곡 제목과 재생시간연결음
		
		m = changeStr(m);
		int[] pTime = new int[musicinfos.length];
		for(int i = 0; i<musicinfos.length; i++) {
			pTime[i] = musicPlayTime(musicinfos[i]);
			fullMusicInfo[i] = fullMusicConcat(pTime[i], musicinfos[i]);
		}
		
		String answer = musicIs(m, pTime, fullMusicInfo);
		System.out.println(answer);
	}
	// 음악 재생 시간 구하기
	static int musicPlayTime(String musicinfos) {
		
		String[] musicInfoSplit = musicinfos.split(",");
		String startTime = musicInfoSplit[0];
		String endTime = musicInfoSplit[1];

		int time = playTime(startTime, endTime);
		return time;
	}
	static int playTime(String startTime, String endTime) {
		int time = 0;
		
		String[] startTimeSplit = startTime.split(":");
		String[] endTimeSplit = endTime.split(":");

		int hours = Integer.parseInt(endTimeSplit[0]) - Integer.parseInt(startTimeSplit[0]);
		int startTimeMinute = Integer.parseInt(startTimeSplit[1]);
		int endTimeMinute = Integer.parseInt(endTimeSplit[1]);
		System.out.println(startTimeMinute +", "+ endTimeMinute);
		if(startTimeMinute > endTimeMinute) {
			endTimeMinute+=60;
			hours-=1;
			time = hours*60 + (endTimeMinute - startTimeMinute);
		}
		else {
			time = hours*60 + (endTimeMinute - startTimeMinute);
		}
		
		return time;
	}
	// 음악 재생 시간까지 음 연결하기
	static String[] fullMusicConcat(int time, String musicinfo) {
		
		String[] musicInfo = musicinfo.split(",");
		String[] result = new String[2];
		result[0] = musicInfo[2]; // 곡 제목
		
		// C# -> c... +32
		String newMusic = changeStr(musicInfo[3]);
		
		String str = "";
		int index = 0;
		while(index < time) {
			str+=newMusic.charAt(index % newMusic.length());
			index+=1;
		}
		
		result[1] = str;
		return result;
	}
	static String changeStr(String str) {
		String newStr = "";
		
		for(int i = 0; i<str.length(); i++) {
			if(i != str.length()-1) {
				if(str.charAt(i+1) == '#') {
					char c = Character.toLowerCase(str.charAt(i));
					newStr += c;
					i++;
					continue;
				}
			}
			newStr += str.charAt(i);
		}
		return newStr;
	}
	
	// 주어진 m 의 곡 제목 구하기
	static String musicIs(String searchMusic, int[] time, String[][] musicInfo) {
		List<Info> answerList = new ArrayList<>();
		for(int i = 0; i<musicInfo.length; i++) {
			if(musicInfo[i][1].contains(searchMusic)) {
				answerList.add(new Info(i, time[i]));
			}
		}
		
		if(answerList.isEmpty()) {
			return "(None)";
		}
		else {
			Collections.sort(answerList, new Comparator<Info>() {
				@Override
				public int compare(Info i1, Info i2) {
					if(i1.time == i2.time) return Integer.compare(i1.index, i2.index);
					return Integer.compare(i2.time, i1.time);
				}
			});
			
			return musicInfo[answerList.get(0).index][0];
		}
	}
}
