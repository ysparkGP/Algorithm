package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 문자열, 구현
 * 2019 KAKAO BLIND RECRUITMENT
 * 오픈 채팅방
 */

public class Test_KAKAO_BLIND1 {

	public static void main(String[] args) {

		String[] record = {
				"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"
		};
		Map<String, String> map = new HashMap<>();
		List<String> temp = new ArrayList<>();
		List<String> resultList = new ArrayList<>();
		for(int i = 0; i<record.length; i++) {
			StringTokenizer st = new StringTokenizer(record[i]," ");
			String order = st.nextToken();
			
			if(order.equals("Enter")) {
				String id = st.nextToken();
				String nickName = st.nextToken();
				StringBuilder sb = new StringBuilder();
				sb.append("[" + id + "]" + "님이 들어왔습니다.");
				resultList.add(sb.toString());
				map.put(id, nickName);
			}
			else if(order.equals("Leave")) {
				String id = st.nextToken();
				StringBuilder sb = new StringBuilder();
				sb.append("[" + id + "]" + "님이 나갔습니다.");
				resultList.add(sb.toString());
			}
			else {
				String id = st.nextToken();
				String nickName = st.nextToken();
				map.put(id, nickName);
			}
		}
		
		String[] answer = new String[resultList.size()];
		for(int i = 0; i<answer.length; i++) {
			StringBuilder sb = new StringBuilder();
			int index = resultList.get(i).indexOf("]");
			String nickName = map.get(resultList.get(i).substring(1,index));
			String order = resultList.get(i).substring(index+1);
			sb.append(nickName+order);
			answer[i] = sb.toString();
		}
		for(String str : answer) {
			System.out.println(str);
		}
		
	}

}
