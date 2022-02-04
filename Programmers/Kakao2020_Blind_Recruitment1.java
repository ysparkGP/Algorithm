package Programmers;

import java.util.ArrayList;
import java.util.List;

public class Kakao2020_Blind_Recruitment1 {
	
	public static void main(String[] args) {
		String s = "abcabcabcabcdededededede";
		
		int answer = 1000;
		for(int cut = 1; cut < s.length()+1; cut++) {
			List<String> strList = new ArrayList<>();
			for(int part = 0; part < s.length(); part+=cut) {
				if(part+cut > s.length()) {
					strList.add(s.substring(part, s.length()));
				}
				else {
					strList.add(s.substring(part, part+cut));
				}
			}
			
			
			int[] strIndex = new int[strList.size()+1];
			String prevStr = "";
			for(int i = 0; i<strList.size(); i++) {
				String partStr = strList.get(i);
				if(prevStr.equals(partStr)) {
					strIndex[i] = strIndex[i-1] + 1;
				}
				
				else {
					strIndex[i] = 1;
				}
				prevStr = partStr;
			}
			
			String resultStr = "";
			for(int i = 1; i<strIndex.length; i++) {
				if(strIndex[i] == 1 || strIndex[i] == 0) {
					if(strIndex[i-1] == 1) {
						resultStr += strList.get(i-1);
					}
					else {
						resultStr += Integer.toString(strIndex[i-1]) + strList.get(i-1);
					}
				}
				
			}
			
			answer = Math.min(answer, resultStr.length());
		}
		System.out.println(answer);
	}

}
