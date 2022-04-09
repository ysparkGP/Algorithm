package programmers;

public class Test_BruteForce3 {

	static int[] answer;
	public static void main(String[] args) {
		
		int brown = 10;
		int yellow = 2;
		int totalBox =brown+yellow;
		answer = new int[2];
		
		for(int row = 1; row <=Math.sqrt(totalBox); row++) { // 세로 길이가 1부터 시작
			
			if(totalBox % row == 0) {
				int col = totalBox/row;
				int yellowRow = row - 2;
				int yellowCol = col - 2;
				if(yellowRow < 0 || yellowCol < 0) continue;
				if((yellowRow * yellowCol == yellow)) {
					answer[0] = col;
					answer[1] = row;
					break;
				}
			}
		}
		
		System.out.println(answer[0]+","+answer[1]);
	}
}
