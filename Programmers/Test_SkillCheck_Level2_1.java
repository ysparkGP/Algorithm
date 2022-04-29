package programmers;

import java.util.LinkedList;

public class Test_SkillCheck_Level2_1 {

	static LinkedList<Character> list;
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = {".BACDE+.", "C+BADF.", ".AE-CB", "B.DA_"};
		/*
		int cnt = 0;
		for(int i = 0; i<skill_trees.length; i++) {
			init(skill);
			boolean tf = true;
			for(int j = 0; j<skill_trees[i].length(); j++) {
				if(list.size() == 0) continue;
				//System.out.print(skill_trees[i].charAt(j));
				if(list.contains(skill_trees[i].charAt(j)) && list.get(0).equals(skill_trees[i].charAt(j))) {
					list.remove(0);
				}
				else if(list.contains(skill_trees[i].charAt(j)) && !list.get(0).equals(skill_trees[i].charAt(j))) {
					tf = false;
					break;
				}
			}
			if(tf) cnt++;
			//System.out.println(cnt);
		}*/
		
		int answer = 0;
		for(String str : skill_trees) {
			System.out.println(str + "----");
			String temp = str.replaceAll("[^"+skill+"]|^[.]|[.]$", "");
			System.out.println(temp);
		}
		
		
	}
	static void init(String skill) {
		list = new LinkedList<>();
		for(int i = 0; i<skill.length(); i++) list.add(skill.charAt(i));
	}

}
