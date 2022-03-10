package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * Trie
 */

public class Test14725 {

	static StringBuilder sb;
	
	static class Trie{
		TrieNode root;
		Trie(){
			root = new TrieNode();
		}
		
		void insert(String[] str) {
			
			TrieNode thisNode = root;
			for(int i = 0; i<str.length; i++) {
				thisNode = thisNode.childNode.computeIfAbsent(str[i], key-> new TrieNode());
			}
		}
		
		void print(TrieNode thisNode, int floor) {
			Set<String> set = thisNode.childNode.keySet();
			Iterator<String> iter = set.iterator();
			
			while(iter.hasNext()) {
				String str = iter.next();
				
				for(int i = 0; i<floor; i++){
					sb.append("--");
				}
				sb.append(str+"\n");
				print(thisNode.childNode.get(str),floor+1);
			}
		}
	}
	static class TrieNode{
		Map<String, TrieNode> childNode;
		boolean isOver;
		
		TrieNode(){
			childNode = new TreeMap<>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Trie trie = new Trie();
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			String[] strArr = new String[m];
			for(int j = 0; j<m; j++) {
				strArr[j] = st.nextToken();
			}
			trie.insert(strArr);
		}
		sb = new StringBuilder();
		trie.print(trie.root, 0);
		System.out.println(sb.toString());
	}

}
