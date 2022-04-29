package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test_SkillCheck_Level2_2 {

	public static void main(String[] args) {
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1,1};
		
		Queue<Integer> progressQue = new LinkedList<>();
		Queue<Integer> speedQue = new LinkedList<>();
		for(int i =0; i<progresses.length; i++) {
			progressQue.add(progresses[i]);
			speedQue.add(speeds[i]);
		}
		
		List<Integer> answers = new ArrayList<>();
		while(!progressQue.isEmpty()) {
			
			int size = progressQue.size();
			for(int i = 0; i<size; i++) {
				int speed = speedQue.poll();
				int newProgress = progressQue.poll() + speed;
				speedQue.add(speed);
				progressQue.add(newProgress);
			}
			
			int cnt = 0;
			while(!progressQue.isEmpty() && progressQue.peek() >= 100) {
				progressQue.poll();
				speedQue.poll();
				cnt++;
			}
			if(cnt > 0) answers.add(cnt);
		}
		
		for(int i = 0; i<answers.size(); i++) {
			System.out.print(answers.get(i)+ " ");
		}
		int[] answer = new int[answers.size()];
		for(int i = 0; i<answer.length; i++) answer[i] = answers.get(i);
	}

}
