package Programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dev_Matching_2021_1 {
	
	static int[] old_lottos = {45, 4, 35, 20, 3, 9};
	static int[] old_win_nums = {20, 9, 3, 45, 4, 35};
	
	public static void main(String[] args) throws IOException{
		
		Integer[] lottos = Arrays.stream(old_lottos).boxed().toArray(Integer[]::new);
		Integer[] win_nums = Arrays.stream(old_win_nums).boxed().toArray(Integer[]::new);
		Arrays.sort(lottos, Collections.reverseOrder());
		List<Integer> winList = new ArrayList<>(Arrays.asList(win_nums));
		
		
		int[] answer = new int[2];
		
		int maxResult = 0;
		int minResult = 0;
		boolean[] visit = new boolean[46];
		for(int i = 0; i<6; i++) {
			if(lottos[i] == 0) {
				for(int win : winList) {
					if(visit[win])
						continue;
					else {
						lottos[i] = win;
						visit[win] = true;
						break;
					}
				}
				maxResult += 1;
			}
			else {
				if(winList.contains(lottos[i])) {
					minResult+=1;
					maxResult+=1;
				}
				visit[lottos[i]] = true;
			}
		}
		answer[0] = maxResult;
		answer[1] = minResult;
		for(int i = 0; i<2; i++) {
			switch(answer[i]) {
			case 6: answer[i] = 1;
			break;
			
			case 5: answer[i] = 2;
			break;
			
			case 4: answer[i] = 3;
			break;
			
			case 3: answer[i] = 4;
			break;
			
			case 2: answer[i] = 5;
			break;
			
			default: answer[i] = 6;
			break;
			}
		}
		System.out.println(answer[0] + ", " + answer[1]);
	}
}
