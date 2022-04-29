package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Test_KAKAO_BLIND14 {

	public static void main(String[] args) {
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		s = s.replaceAll("[{]", "");
		String[] arr = s.split("[}]");
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				return Integer.compare(str1.length(), str2.length());
			}
		});
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<arr.length; i++) {
			//System.out.println(arr[i].length());
			StringTokenizer st = new StringTokenizer(arr[i], ",");
			while(st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				if(!list.contains(value)) list.add(value);
			}
		}
		int[] answer = new int[list.size()];
		for(int i = 0; i<answer.length; i++) answer[i] = list.get(i);
		
		
		/*Map<String, Integer> map = new HashMap<>();
		 Pattern pattern = Pattern.compile("[0-9]+");
		 Matcher matcher = pattern.matcher(s);
		 while(matcher.find()) {
			 String n = matcher.group();
			 map.put(n, map.getOrDefault(n,0)+1);
		 }
		 int[] answer = new int[map.size()];
		 for(String key: map.keySet()) {
			 answer[map.size() - map.get(key)] = Integer.parseInt(key);
		 }*/
	}

}
