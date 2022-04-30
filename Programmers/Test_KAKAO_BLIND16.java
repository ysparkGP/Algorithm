package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 구현, 조합
 * 2019 KAKAO BLIND RECRUITMENT
 * 후보키
 * 유일키와 최소키를 어떻게 잡아야하는 논리 설계가 중요한 문제
 * 최소키는 비트마스킹으로 집합의 부분집합을 구별해주는 방법이 인상적이었음.
 */

public class Test_KAKAO_BLIND16 {

	static ArrayList<ArrayList<Integer>> uniqueList;
	public static void main(String[] args) {
		String[][] relation = { {"a","1","aaa","c","ng"},
				{"a","1","bbb","e","g"},
				{"c","1","aaa","d","ng"},
				{"d","2","bbb","d","ng"}};
		
		uniqueList = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		 // 1. 유니크한 속성 조합 구하기
		dfs(relation, 0, temp);
		 // 2. 유니크한 속성들 조합을 길이순 오름차순으로 정렬
		Collections.sort(uniqueList, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> li1, ArrayList<Integer> li2) {
				return Integer.compare(li1.size(), li2.size());
			}
		});
		
		 // 3. 앞의 속성들로부터 뒤 속성들을 제거(포함되어있다면)
		// String.contains 로 최적화 가능 그러기위해서는 시작부터 다 갈아엎어야함
		for(int i =0; i<uniqueList.size()-1; i++) {
			for(int j = i+1; j<uniqueList.size(); j++) {
				int equal = 0;
				for(int k = 0; k<uniqueList.get(j).size(); k++) {
					if(uniqueList.get(i).contains(uniqueList.get(j).get(k))) equal++;
				}
				if(equal==uniqueList.get(i).size()) {
					uniqueList.remove(j);
					j--;
				}
			}
		}
		int answer = uniqueList.size();
		
	}
	static void dfs(String[][] relation, int index, ArrayList<Integer> uniques) {
		
		if(unique(relation, uniques)) {
			uniqueList.add(uniques);
			return;
		}
		
		if(index >= relation[0].length)
			return;
		
		ArrayList<Integer> addCopyUniques = copyList(uniques);
		addCopyUniques.add(index);
		ArrayList<Integer> notAddCopyUniques = copyList(uniques);
		dfs(relation, index+1, addCopyUniques);
		dfs(relation, index+1, notAddCopyUniques);
	}
	
	static ArrayList<Integer> copyList(ArrayList<Integer> list){
		ArrayList<Integer> copy = new ArrayList<>();
		for(int i = 0; i<list.size(); i++) copy.add(list.get(i));
		return copy;
	}
	
	static boolean unique(String[][] relation, ArrayList<Integer> uniques) {
		if(uniques.size() == 0) return false;
		
		ArrayList<String> attributeValues = new ArrayList<>();
		for(int row = 0; row<relation.length; row++) {
			String str = "";
			for(Integer attributeIndex: uniques) {
				str+=relation[row][attributeIndex];
			}
			if(attributeValues.contains(str)) return false;
			else {
				attributeValues.add(str);
			}
		}
		return true;
	}

}
