package programmers;

/*
 * dfs
 */

public class Test_DfsBfs4 {
	static boolean[] visit;
	static String result = "J";
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		
		String start = "ICN";
		sb.append("ICN ");
		visit = new boolean[tickets.length];
		dfs(tickets, 0, start);
		
		String[] answer = result.split(" ");
		for(String str : answer) System.out.print(str + " ");
		
	}
	
	static void dfs(String[][] tickets, int cnt, String now) {
		if(cnt == tickets.length) {
			result = result.compareTo(sb.toString()) < 0 ? result: sb.toString();
			return;
		}
		for(int i = 0; i<tickets.length; i++) {
			if(!visit[i] && tickets[i][0].equals(now)) {
				visit[i] = true;
				sb.append(tickets[i][1] + " ");
				dfs(tickets,cnt+1, tickets[i][1]);
				visit[i] = false;
				sb.delete(sb.length()-4, sb.length());
			}
		}
	}

}
