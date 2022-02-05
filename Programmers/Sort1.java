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
		
		//�������� ����
		Arrays.sort(str, new Comparator<String>(){
			//b+a�� a+b�� ���ϴٰ� ���� ���� ������ ���� �ϳ� �ϳ� �� �����ָ鼭 ��, ����� ������ �ٲ�� ������ ���´ٸ� �ٲ����ʴ´�.
			@Override
			public int compare(String a, String b) { 
				return (b+a).compareTo(a+b);
			}
		});
		
		for(int i = 0; i<str.length; i++) {
			answer += str[i];
		}
		
		// 0000���� 0���θ� �����Ǿ��ִ� ��� 0����
		if(str[0].equals("0"))
			answer = "0";

        System.out.println(answer);
	}
}
