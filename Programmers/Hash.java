package programmers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hash {
	public static void main(String[] args)throws IOException{
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i<participant.length; i++) {
			if(map.containsKey(participant[i])) {
				map.put(participant[i], map.get(participant[i]) + 1);
			}
			else
				map.put(participant[i], 1);
		}
		
		for(int i = 0; i<completion.length; i++) {
			if(map.get(completion[i]) == 1) {
				map.remove(completion[i]);
			}
			else if(map.get(completion[i]) > 1) {
				map.put(completion[i], map.get(completion[i]) - 1);
			}
		}
		
		String answer = "";
		answer += map.keySet().toString();
		answer = answer.substring(1,answer.length()-1);
		
		System.out.println(answer);
	}
}
