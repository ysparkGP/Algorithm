package programmers;

/*
 * 프로그래머스 2022 썸머 코딩 1번
 */

public class Test_2022_SummerCoding1 {

	public static void main(String[] args) {
		int[][] atoms = {{80, 35}, {70, 38}, {100, 41}, {75,30}, {160,80}, {77, 29}, {181, 68}, {151, 76}};
		// 1열: 미세먼지, 2열 초미세먼지
		
		int mask = 0;
		int answer = 0;
		for(int i = 0; i<atoms.length; i++) {
			if(atoms[i][0] >= 151 && atoms[i][1] >= 76) {
				if(mask >= 1) {
					mask = 0;
				}
				else {
					answer++;
				}
			}
			else if(atoms[i][0] >= 81 || atoms[i][1] >= 36) {
				if(mask >= 1) {
					mask--;
				}
				else {
					mask = 2;
					answer++;
				}
			}
			else
				mask--;
		}
		//System.out.println(answer);
	
	}

}
