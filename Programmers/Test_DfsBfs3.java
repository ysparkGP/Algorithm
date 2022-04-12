package programmers;

/*
 * dfs
 */

public class Test_DfsBfs3 {

	static boolean[] visit;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot","dot","dog","lot","log","cog"};
		visit = new boolean[words.length];
		dfs(words, begin, target, 0);
		System.out.println(answer == Integer.MAX_VALUE? 0: answer);
	}
	static void dfs(String[] words, String now, String target, int cnt) {
		System.out.println(now+", " +cnt);
		if(now.equals(target)) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for(int i = 0; i<words.length; i++) {
			// 조건
			if(visit[i]) continue;
			int check = now.length();
			for(int j = 0; j<words[i].length(); j++) {
				if(now.charAt(j) == words[i].charAt(j)) check--;
			}
			if(check == 1) {
				visit[i] = true;
				dfs(words, words[i], target, cnt+1);
				visit[i] = false;
			}
		}
	}

}
