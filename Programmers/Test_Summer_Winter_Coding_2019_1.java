package programmers;

/*
 * 대각선이 지나는 단위정사각형
 * 유클리드 호제법(최대 공약수)
 */

public class Test_Summer_Winter_Coding_2019_1 {

	public static void main(String[] args) {
		
		int w = 8;
		int h = 12;
		
		if(w<h) {
			int temp = w;
			w=h;
			h=temp;
		}
		
		long answer = (long) w*h - (w+h-uclid(w,h));
		System.out.println(answer);

	}
	static int uclid(int a, int b) {
		
		int c = a%b;
		if(c == 0) return b;
		
		return uclid(b,c);
	}

}
