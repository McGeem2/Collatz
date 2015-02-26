package com.nku.team1.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nku.team1.exception.CollatzOutOfBoundsException;

public class LongestCollatz {

	protected List<Long> seq;

	/**
	 * Finds the starting point with the longest chain.
	 * It will look at starting points between n and 1
	 * @param n
	 * @return
	 * @throws CollatzOutOfBoundsException will be thrown if n is < 1
	 */
	public int findLongest(int n) {
		int maxLength = 0;
		int maxKey = 0;

		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int[] numbers = getNumbersInArray(n);

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 0)
				continue;
			int addedLength = createSequence(numbers[i], map);
			Iterator<Long> it = seq.iterator();
			boolean isFirst = true;
			while (it.hasNext()) {
				long num = it.next();
				map.put(num, seq.size() + addedLength);
				if (!isFirst && num < n) {
					numbers[(int)(n - num)] = 0;
				}
				it.remove();
				isFirst = false;
			}
		}

		for (int i : numbers) {
			if (i == 0)
				continue;
			if (map.get((long)i) > maxLength) {
				maxLength = map.get((long)i);
				maxKey = i;
			}
		}
		return maxKey;
	}

	/**
	 * This method will create an array of int in the order {n, n-1, n-2, ... 1}
	 * @param n
	 * @return
	 */
	protected int[] getNumbersInArray(int n) {
		int[] numbers = new int[n];
		for (int i = n; i >= 1; i--) {
			numbers[n-i] = i;
		}
		return numbers;
	}

	/**
	 * This method will create the sequence starting at n. The sequence
	 * will be placed in the class member {@link seq}. While creating the sequence the
	 * method will check the given map for a previously derived sequence. If there is a match
	 * it will stop generating the sequence and return. The int that is returned is the 
	 * length that should be added to the size of seq. This represents the length of a previously
	 * derived sequence the is contained in the sequence for n
	 * 
	 * @param n
	 * @param map - Map that contains previously derived sequences. Key is Number that started the sequence 
	 * and value is the length of the sequence
	 * @return - the length to be added to the sequence size
	 */
	protected int createSequence(int n, Map<Long, Integer> map) {
		Collatz c = new Collatz(n);
		seq = new ArrayList<Long>();
		seq.add((long) n);
		Iterator<Long> it = c.iterator();
		while (it.hasNext()) {
			long i = it.next();
			if (map.get(i) != null) {
				return map.get(i);
			}
			seq.add(i);
		}
		return 0;
	}

}
