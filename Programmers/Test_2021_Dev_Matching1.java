package programmers;

import java.util.HashMap;
import java.util.Map;

/*
 * 2021 Dev-Matching: 웹 백앤드 개발
 * 다단계 칫솔 판매
 * 구현, 그래프
 */

public class Test_2021_Dev_Matching1 {

	static class Staff{
		String name;
		String parent;
		int profit;
		
		public Staff(String name, String parent, int profit) {
			this.name = name;
			this.parent = parent;
			this.profit = profit;
		}
		
		public int payToParent(int amount) {
			profit += (amount - (amount*10)/100);
			return (amount*10) / 100;
		}
	}
	static final int price = 100;
	public static void main(String[] args) {
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referal = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"sam", "emily", "jaimie", "edward"};
		int[] amount = {2, 3, 5, 4};
		
		Map<String, Integer> map = new HashMap<>();
		map.put("-", 0);
		Staff boss = new Staff("-", null, 0);
		Staff[] group = new Staff[enroll.length+1];
		group[0] = boss;
		for(int i = 0; i<enroll.length; i++) {
			group[i+1] = new Staff(enroll[i], referal[i], 0);
			map.put(enroll[i], i+1);
		}
		
		for(int i = 0; i<seller.length; i++) {
			int sellerIndex = map.get(seller[i]);
			int cash = amount[i]*price;
			while(true) {
				cash = group[sellerIndex].payToParent(cash);
				if(cash == 0) break;
				
				String parent = group[sellerIndex].parent;
				sellerIndex = map.get(parent);
				if(parent.equals("-")) {
					group[sellerIndex].profit += cash;
					break;
				}
				
				
			}
		}
		int[] answer = new int[group.length-1];
		for(int i = 1; i<group.length; i++) {
			answer[i-1] = group[i].profit;
		}
		
	}
	

}
