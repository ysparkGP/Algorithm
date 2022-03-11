package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * Trie 응용 문제
 */

public class Test5670 {

	static class Trie{
		TrieNode root;
		
		Trie(){
			root = new TrieNode();
		}
		
		void insert(String str) {
			TrieNode thisNode = root;
			for(int i = 0; i<str.length(); i++) {
				thisNode = thisNode.childNode.computeIfAbsent(str.charAt(i), key -> new TrieNode());
			}
			thisNode.isEnd();
		}
		
		int count(int cnt, TrieNode thisNode, int index, String str) {
			
			/*
			 * 카운트 하는 기준
			 * 1. 첫 번째는 무조건 카운트 해야함
			 * 2. 문자열이 안끝났지만 자식노드를 2개 이상 갖고있을때
			 * 3. 문자열이 끝났음에도 불구하고 자식노드를 갖고있을때
			 */
			
			if(index >= str.length()) return cnt;
			
			char st = str.charAt(index);
			TrieNode nextNode = thisNode.childNode.getOrDefault(st, null);
			
			// 1
			if(index == 0) return count(cnt+1, nextNode, index+1, str);
			
			else {
				// 2
				if(thisNode.childNode.keySet().size() >= 2) return count(cnt+1, nextNode, index+1, str);
				
				// 3
				else if(thisNode.end == true) return count(cnt+1, nextNode, index+1, str);
				
				else return count(cnt, nextNode, index+1, str);
			}

			
		}
	}
	
	static class TrieNode{
		Map<Character, TrieNode> childNode;
		boolean end;
		
		TrieNode(){
			childNode = new HashMap<>();
			end = false;
		}
		
		void isEnd() {
			this.end = true;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Null Pointer Exception 처리
		String str = "";
		while((str  = br.readLine()) != null) {
			int N = Integer.parseInt(str);
			
			String[] strArr = new String[N];
			Trie trie = new Trie();
			for(int i = 0; i<N; i++) {
				String restoreStr = br.readLine();
				strArr[i] = restoreStr;
				trie.insert(restoreStr);
			}
			
			long result = 0;
			for(int i = 0; i<N; i++) {
				long value = (long) trie.count(0, trie.root, 0, strArr[i]);
				result += value;
			}
			
			System.out.println(String.format("%.2f", (double)result/N));
		}
	}

}
