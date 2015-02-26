package com.nku.team1.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LongestCollatz {

	protected List<Long> seq;

	public int findLongest(int n) {
		int maxLength = 0;
		int maxKey = 0;

		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int[] numbers = getNumbersInArray(n);

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 0)
				continue;
			int addedLength = getSequence(numbers[i], map);
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

	protected int[] getNumbersInArray(int n) {
		int[] numbers = new int[n];
		for (int i = n; i >= 1; i--) {
			numbers[n-i] = i;
		}
		return numbers;
	}

	protected int getSequence(int n, Map<Long, Integer> map) {
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
