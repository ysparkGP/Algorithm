package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Queue, Stack 기초
 */

public class Test_Queue2 {

	public static void main(String[] args){
		int answer = 0;
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10};
		
		Stack<Integer> truckStack = new Stack<>();
		Map<Integer, Integer> bridgeMap = new HashMap<>();
		for(int i = truck_weights.length-1; i>=0; i--)
			truckStack.push(truck_weights[i]);
		
		int sum = 0;
		while(true) {
			answer++;
			
			if(bridgeMap.containsKey(answer))
				bridgeMap.remove(answer);
			
			sum = bridgeMap.values().stream().mapToInt(Integer::intValue).sum();
			
			if(!truckStack.isEmpty()) {
				if(truckStack.peek() + sum <= weight) {
					bridgeMap.put(answer+bridge_length, truckStack.pop());
				}
			}
			else if(truckStack.isEmpty() && bridgeMap.isEmpty()) break;
		}
		System.out.println(answer);
		
		/*Queue<Integer> que = new LinkedList<>();
		int index = 0;
		int nowWeight = 0;
		int cnt = 0;
		while(true) {
			while(que.size() < bridge_length) {
				if(index < truck_weights.length && nowWeight+truck_weights[index] <= weight) {
					que.add(truck_weights[index]);
					nowWeight+=truck_weights[index];
					answer+=1;
					index+=1;
				}
				else {
					que.add(0);
					answer+=1;
				}
			}
			
			int value = que.poll();
			nowWeight -= value;
			if(value > 0) cnt++;
			if(cnt >= truck_weights.length) break;
		}
		System.out.println(answer+1);*/
		
		
		
	}
	

}
