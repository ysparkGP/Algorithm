package programmers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Hash2 {
	public static void main(String[] args) throws IOException{
		boolean answer = true;
		String[] phone = {"12","123","1235","567","88"};
		
		Arrays.sort(phone);
		
		for(int i = 0; i<phone.length - 1; i++) {
			int len = Math.min(phone[i].length(), phone[i+1].length());
			if(phone[i].substring(0,len).equals(phone[i+1].substring(0,len))) {
				answer = false;
				break;
			}
		}
		
		//System.out.println(answer);
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i<phone.length; i++)
			map.put(phone[i], i);
		
		for(int i = 0; i<phone.length; i++) {
			for(int j = 1; j<phone[i].length(); j++) {
				
				System.out.println(phone[i] + " , " + phone[i].substring(0,j));
				if(map.containsKey(phone[i].substring(0,j))) {
					System.out.println(false);
				}
			}
				
		}
		System.out.print(true);
		
		/*Arrays.sort(phone, new Comparator<String>() {
			@Override
			public int compare(String p1, String p2) {
				
				if(p1.length() == p2.length()) {
					return p1.compareTo(p2);
				}
				
				return p1.length() - p2.length();
			}
		});
		
		
		
		LinkedHashMap<Integer, String> hash = new LinkedHashMap<>();
		
		for(int i = 0; i<phone.length; i++) {
			hash.put(i, phone[i]);
		}
		
		loop: for(int i = 0; i<hash.size()-1; i++) {
			for(int j = i+1; j<hash.size(); j++) {
				if(hash.get(j).substring(0,phone[i].length()).contains(phone[i])) {
					answer = false;
					break loop;
				}
				
			}
		}
		*/
		
		
	}
}
