package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * Trie + dfs
 * 
 * dfs로 맵을 돌면서 문자열들을 하나씩 조합한다.
 * 조합된 문자열을 Trie에 비교해보면서 결과를 도출한다.
 */

public class Test9202{
	
	static int[] cx = {0,1,1,1,0,-1,-1,-1};
	static int[] cy = {-1,-1,0,1,1,1,0,-1};
	
	static class Trie{
		TrieNode root;
		Trie(){
			this.root = new TrieNode();
		}
		void insert(String str) {
			TrieNode thisNode = root;
			for(int i = 0; i<str.length(); i++) {
				thisNode = thisNode.childNode.computeIfAbsent(str.charAt(i), key -> new TrieNode());
			}
			thisNode.isEnd();
		}
		
		void print(TrieNode thisNode) {
			Set<Character> set = thisNode.childNode.keySet();
			Iterator<Character> iter = set.iterator();
			
			if(thisNode.end == true) System.out.println();
			
			while(iter.hasNext()) {
				Character st = iter.next();
				System.out.print(st);
				print(thisNode.childNode.get(st));
			}
		}
		
		boolean contains(String str, boolean lastCheck) {
			
			TrieNode trieNode = root;
			for(int i = 0; i<str.length(); i++) {
				if(trieNode.childNode.containsKey(str.charAt(i)))
					trieNode = trieNode.childNode.get(str.charAt(i));
				else return false;
			}
			
			if(lastCheck && !trieNode.end) return false;
			else if(lastCheck  && trieNode.end) return true;
			else return true;
		}
		
	}
	static class TrieNode{
		Map<Character, TrieNode> childNode;
		boolean end;
		TrieNode(){
			this.childNode = new TreeMap<>();
			this.end = false;
		}
		void isEnd() {
			this.end = true;
		}
	}
	
	static boolean[][] visit;
	static Trie trie;
	static Set<String> strSet;
	static List<String> strList;
	static char[][] boggle;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		trie = new Trie();
		for(int i = 0; i<N; i++) {
			trie.insert(br.readLine());
		}
		br.readLine();
		int M = Integer.parseInt(br.readLine());
		boggle = new char[4][4];
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<4; j++) {
				String str = br.readLine();
				if(str.isBlank()) {
					j--;
					continue;
				}
				
				for(int k = 0; k<4; k++) {
					boggle[j][k] = str.charAt(k);
				}
			}
			
			visit = new boolean[4][4];
			strSet = new HashSet<>();
			for(int y = 0; y<4; y++) {
				
				for(int x = 0; x<4; x++) {
					if(trie.root.childNode.containsKey(boggle[y][x])) {
						visit[y][x] = true;
						dfs(boggle[y][x]+"", y,x);
						visit[y][x] = false;
					}
				}
			}
			
			strList = new ArrayList<>(strSet);
			StringBuilder sb = makeResult();
			System.out.println(sb.toString());
		}
		//trie.print(trie.root);
	}
	
	static void dfs(String thisStr, int y, int x) {
		
		if(trie.contains(thisStr, true)) { // 조립이 완성된 문자열에 대해서 트라이에 속한 문자열이 맞는지 판단
			strSet.add(thisStr); // set을 이용하여 문자열 중복 방지
		}
		
		for(int i = 0; i<8; i++) {
			int dy = y+cy[i];
			int dx = x+cx[i];
			if(!validCheck(dy,dx)) continue;
			if(visit[dy][dx]) continue;
			if(trie.contains(thisStr+boggle[dy][dx], false)) { // 조립한 문자열들에 대해서 더 나아가도 되는지(트라이에 속해있는 문자열인지)
				visit[dy][dx] = true;
				dfs(thisStr+boggle[dy][dx],dy,dx);
				visit[dy][dx] = false;
			}
		}
		
	}
	
	static StringBuilder makeResult() {
		StringBuilder sb = new StringBuilder();
		Collections.sort(strList, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) { // 문자열 정렬 -> 문자열 사이즈가 큰 순으로, 같다면 사전순으로
				if(str1.length() ==  str2.length())
					return str1.compareTo(str2);
				return str2.length() - str1.length();
			}
		});
		int score = calcScore();
		sb.append(score + " ");
		sb.append(strList.get(0) +  " ");
		sb.append(strList.size());
		return sb;
	}
	
	static int calcScore() {
		int score = 0;
		
		for(String str : strList) {
			if(str.length()<=2)  continue;
			else if(str.length() > 2 && str.length() <= 4) score+=1;
			else if(str.length() == 5) score+=2;
			else if(str.length() == 6) score+=3;
			else if(str.length() == 7) score+=5;
			else score+=11;
		}
		
		return score;
	}
	
	static boolean validCheck(int y, int x) {
		if(y<0 || x<0 || y>=4 || x>=4) return false;
		return true;
	}
}
