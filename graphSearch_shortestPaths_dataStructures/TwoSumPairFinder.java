import java.util.HashSet;
import java.util.Set;

import utils.DataLoader;

public class TwoSumPairFinder {
	/**
	 * Function which counts distinct target sum t values in a given range [low,
	 * high] for distinct pairs of numbers in a given set.
	 * 
	 * @param set  - Set of numbers
	 * @param low  - Lower range of two number sum
	 * @param high - Higher range of two number sum
	 * @return - Distinct sum count in given range
	 */
	public static int twoSumPairInRange(Set<Long> set, Long low, Long high) {
		Set<Long> count = new HashSet<>();
		for (Long x : set) {
			for (long i = low; i <= high; i++) {
				Long y = i - x;
				if (x < y && set.contains(y)) {
					count.add(i);
				}
			}
		}
		return count.size();
	}

	public static void main(String[] args) {
		Set<Long> set = DataLoader.loadSet("data/algo1-programming_prob-2sum.txt");
		System.out.println("Total distinct numbers - " + set.size());
		System.out.print("TWo sum pair count in range - ");
		System.out.println(TwoSumPairFinder.twoSumPairInRange(set, -10000L, 10000L));
	}
}