package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/*
 * 2019 카카오 개발자 겨울 인턴십
 * 불량 사용자
 * dfs, 비트마스킹, 구현, 정규식
 * 조합을 구한다던지, 순서가 상관없는 문제들은 비트마스킹이 유용
 */

public class Test_KAKAO_BLIND31 {

	static Set<String> set;
	static Set<Integer> bitSet;
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		
		set = new HashSet<>();
		bitSet = new HashSet<>();
		ArrayList<String> list = new ArrayList<>();
		boolean[] visit = new boolean[user_id.length];
		
		dfs(user_id, banned_id, 0, list, visit);
		int answer = set.size();
		
		//bitMasking(0, user_id, banned_id, 0);
		//System.out.println(bitSet.size());
	}
	static void dfs(String[] user_id, String[] banned_id, int depth, ArrayList<String> list, boolean[] visit) {
		if(depth == banned_id.length) {
			
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String str1, String str2) {
					return str1.compareTo(str2);
				}
			});
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<list.size(); i++) {
				sb.append(list.get(i));
			}
			if(!set.contains(sb.toString())) set.add(sb.toString());
			return;
		}
		
		for(int i = 0; i<user_id.length; i++) {
			if(!visit[i]) {
				visit[i] = true;
				if(userIsBanned(user_id[i], banned_id[depth])) {
					list.add(user_id[i]);
					dfs(user_id, banned_id, depth+1, list, visit);
					//System.out.println(list.remove(depth));
					list.remove(user_id[i]);
				}
				visit[i] = false;
			}
		}
	}
	static boolean userIsBanned(String userId, String bannedId) {
		
		if(!(userId.length() == bannedId.length())) {
			return false;
		}
		String tempUser = "";
		String tempBan = "";
		for(int i = 0; i<userId.length(); i++) {
			if(bannedId.charAt(i) == '*') continue;
			tempUser += userId.charAt(i);
			tempBan += bannedId.charAt(i);
		}
		
		if(tempBan.equals(tempUser)) return true;
		
		return false;
	}
	
	static void bitMasking(int depth, String[] user_id, String[] banned_id, int bit) {
		if(depth == banned_id.length) {
			bitSet.add(bit);
			return;
		}
		
		String reg = banned_id[depth].replace("*", "[a-z0-9]");
		for(int i = 0; i<user_id.length; i++) {
			if(user_id[i].matches(reg) && !(((bit >> i) & 1) == 1)) {
				bitMasking(depth+1, user_id, banned_id, (bit | 1<<i));
			}
		}
	}

}
