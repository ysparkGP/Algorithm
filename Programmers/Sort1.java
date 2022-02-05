package programmers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Sort1 {
	static int[] numbers = {3,30,34,5,9};
	static String answer = "";
	public static void main(String[] args) throws IOException{
		
		String[] str = new String[numbers.length];
		
		for(int i = 0; i<numbers.length; i++) {
			str[i] = String.valueOf(numbers[i]);
		}
		
		//내림차순 정렬
		Arrays.sort(str, new Comparator<String>(){
			//b+a와 a+b를 비교하다가 서로 같지 않으면 문자 하나 하나 씩 빼어주면서 비교, 양수가 나오면 바뀌고 음수가 나온다면 바뀌지않는다.
			@Override
			public int compare(String a, String b) { 
				return (b+a).compareTo(a+b);
			}
		});
		
		for(int i = 0; i<str.length; i++) {
			answer += str[i];
		}
		
		// 0000같은 0으로만 구성되어있는 경우 0으로
		if(str[0].equals("0"))
			answer = "0";

        System.out.println(answer);
	}
}
