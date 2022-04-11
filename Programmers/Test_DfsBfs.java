package programmers;

/*
 * dfs
 */

public class Test_DfsBfs {

	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		System.out.println(dfs(numbers, 0, target, 0));
	}
	static int dfs(int[] numbers, int num, int target, int index) {
		if(index == numbers.length) {
			if(num == target) return 1;
			else return 0;
		}
		
		else {
			return dfs(numbers, num+numbers[index], target, index+1) + dfs(numbers, num-numbers[index], target, index+1);
		}
	}

}
