package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [3차] 파일명 정렬
 * 정렬, 구현, 정규표현식
 */

public class Test_KAKAO_BLIND21 {

	public static void main(String[] args) {
		
		String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
		
		// 베스트
		Pattern p = Pattern.compile("([a-z\\s.-]*)([0-9]{1,5})");
		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				Matcher m1 = p.matcher(str1.toLowerCase());
				Matcher m2 = p.matcher(str2.toLowerCase());
				m1.find();
				m2.find();
				/*
				System.out.println(m1.groupCount() + ", " + m2.groupCount());
				for(int i = 0; i<=m1.groupCount(); i++)
					System.out.println(m1.group(i)+ ", " +m2.group(i));
				System.out.println();
				*/
				if(!m1.group(1).equals(m2.group(1))) {
					return m1.group(1).compareTo(m2.group(1));
				}
				else {
					return Integer.compare(Integer.parseInt(m1.group(2)), Integer.parseInt(m2.group(2)));
				}
			}
		});
		for(int i = 0; i<files.length; i++) {
			System.out.println(files[i]);
		}
		System.out.println();
		
		// 내 풀이
		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				// header 정렬
				String lowerStr1 = str1.toLowerCase();
				String lowerStr2 = str2.toLowerCase();
				StringBuilder tempStr1 = new StringBuilder();
				int index = 0;
				while(!(lowerStr1.charAt(index) >= 48 && lowerStr1.charAt(index) <= 57)) {
					tempStr1.append(lowerStr1.charAt(index));
					index++;
				}
				StringBuilder tempStr2 = new StringBuilder();
				index = 0;
				while(!(lowerStr2.charAt(index) >= 48 && lowerStr2.charAt(index) <= 57)) {
					tempStr2.append(lowerStr2.charAt(index));
					index++;
				}
				// number 정렬
				if(tempStr1.toString().compareTo(tempStr2.toString()) == 0) {
					String numTempStr1 = str1.replaceAll("[^0-9]", "@").replaceAll("[@]{1,}", "@");
					String numTempStr2 = str2.replaceAll("[^0-9]", "@").replaceAll("[@]{1,}", "@");
					String[] numTempStrSplit1 = numTempStr1.split("@");
					String[] numTempStrSplit2 = numTempStr2.split("@");
					int number1 = Integer.parseInt(numTempStrSplit1[1]);
					int number2 = Integer.parseInt(numTempStrSplit2[1]);
					return Integer.compare(number1, number2);
				}
				
				return tempStr1.toString().compareTo(tempStr2.toString());
			}
		});
		for(int i = 0; i<files.length; i++) {
			System.out.println(files[i]);
		}
		System.out.println();
	}
}
