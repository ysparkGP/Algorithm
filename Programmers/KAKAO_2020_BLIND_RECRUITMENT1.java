package programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class KAKAO_2020_BLIND_RECRUITMENT1 {

	public static void main(String[] args) throws IOException{
		String[] id_list = {"con", "ryan"}; 
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"}; 
		int k = 3;
		
		/*Map<String, Set<String>> map = new HashMap<>(); //신고당한사람: 산고한 자들 셋
		for(String rep : report) {
			String[] arr = rep.split(" ");
			Set<String> set = map.getOrDefault(arr[1], new HashSet<>());
			set.add(arr[0]);
			map.put(arr[1], set);
		}
		
		Map<String, Integer> countMap = new LinkedHashMap<>();
		for(String id: id_list)
			countMap.put(id, 0);
		
		for(Map.Entry<String, Set<String>> entry : map.entrySet()) {
			if(entry.getValue().size() >= k) {
				for(String value : entry.getValue()) {
					countMap.put(value, countMap.getOrDefault(value, 0) + 1);
				}
			}
		}
		
		return countMap.values().stream().mapToInt(Integer::intValue).toArray();*/
	
		List<String> idList = new ArrayList<>(Arrays.asList(id_list));
		Set<String> set = new HashSet<>(Arrays.asList(report));
		String[] newReport = new String[set.size()];
		set.toArray(newReport);
		
		int[][] reportCount = new int[id_list.length][id_list.length];
		
		for(int i = 0; i<newReport.length; i++) {
			StringTokenizer st = new StringTokenizer(newReport[i]," ");
			String reporter = st.nextToken(); // 신고자
			String victim = st.nextToken(); // 신고당한자
			reportCount[idList.indexOf(reporter)][idList.indexOf(victim)]+=1;
		}
		
		boolean[] suspend = new boolean[id_list.length];
		for(int col = 0; col < id_list.length; col++) {
			int count = 0;
			for(int row = 0; row < id_list.length; row++) {
				count+=reportCount[row][col];
			}
			if(count >= k)
				suspend[col] = true;
		}
		
		int[] answer = new int[id_list.length];
		for(int i = 0; i<id_list.length; i++) {
			for(int j = 0; j<id_list.length; j++) {
				if(reportCount[i][j] == 1 && suspend[j]) {
					answer[i]++;
				}
			}
		}
		
	}

}
