package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 그리디 알고리즘
 */

public class Test_Greedy6 {
	
	public static void main(String[] args) {
		int[][] routes = {
				{15,20},
				{5, 14},
				{13,18},
				{3,5},
		};
		
		int answer = 0;
		
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1],o2[1]);
			}
		});
		List<Integer> cameras = new ArrayList<>();
		for(int i = 0; i<routes.length; i++) {
			if(cameras.size() == 0) {
				cameras.add(routes[i][1]);
				continue;
			}
			boolean possible = false;
			for(int j = 0; j<cameras.size(); j++) {
				if(routes[i][0] <= cameras.get(j)) {
					possible = true;
					break;
				}
			}
			if(!possible) cameras.add(routes[i][1]);
		}
		
		answer = cameras.size();
		System.out.println(answer);

	}

}
