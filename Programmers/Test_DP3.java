package programmers;

/*
 * DP
 */
public class Test_DP3 {

	public static void main(String[] args) {
		int[] money = {1,2,3,4,5,6,9};
		int[] dp1 = new int[money.length]; // 처음 집 포함, 마지막 집 제외
		int[] dp2 = new int[money.length]; // 처음 집 제외, 마지막 집 포함... 2번째 집부터 털어도 상관없음
		/*dp1[0] = money[0];
		dp1[1] = Math.max(dp1[0], money[1]);
		dp2[money.length-1] = money[money.length-1];
		dp2[money.length-2] = Math.max(dp2[money.length-1], money[money.length-2]);
		for(int i = 2, j = money.length-3; i<money.length-1 && j>0; i++, j--) {	
			dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
			dp2[j] = Math.max(dp2[j+1], dp2[j+2] + money[j]);
		}
		
		int answer = Math.max(dp1[money.length-2], dp2[1]);
		*/
		dp1[0] = money[0];
		dp1[1] = Math.max(dp1[0], money[1]);
		dp2[0] = money[1];
		dp2[1] = Math.max(dp2[0], money[2]);
		for(int i = 2, j = 3; i<money.length; i++, j++) {
			if(j>=money.length) j = 0;
			dp1[i] = Math.max(dp1[i-1], dp1[i-2] + money[i]);
			dp2[i] = Math.max(dp2[i-1], dp2[i-2] + money[j]);
		}
		System.out.println(dp1[money.length-2] +", " + dp2[money.length-2]);
		
	}

}
