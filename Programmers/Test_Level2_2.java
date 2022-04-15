package programmers;

public class Test_Level2_2 {
	static int[] answer = {-1};
	static int n;
	static int maxDiff = 0;
	public static void main(String[] args) {
		n = 5;
		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
		int[] lion = new int[11];
		dfs(0, lion, info);
		
	}
	static void dfs(int cnt, int[] lion, int[] info) {
		if(cnt == n) {
			int diff = calDiff(lion, info);
			if(maxDiff <= diff) {
				if(answer.length == 11) {
					StringBuffer original = new StringBuffer();
					StringBuffer temp = new StringBuffer();
					for(int i = 0; i<=10; i++) {
						original.append(answer[i] - 65);
						temp.append(lion[i] - 65);
					}
					if(original.compareTo(temp) > 0)
						answer = lion.clone();
				}
				else answer = lion.clone();
				maxDiff = diff;
			}
			return;
		}
		for(int i = 0; i<=10; i++) {
			lion[i]++;
			dfs(cnt+1, lion, info);
			lion[i]--;
		}
	}
	static int calDiff(int[] lion, int[] info) {
		int aScore = 0; // 어피치 스코어
		int bScore = 0; // 라이온 스코어
		
		for(int i = 0; i<=10; i++) {
			if(lion[i] == 0 && info[i] == 0) continue;
			if(lion[i] > info[i]) bScore+= (10-i);
			else aScore += (10-i);
		}
		return bScore - aScore <= 0 ? -1 : bScore-aScore;
	}

}
