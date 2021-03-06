package com.nku.team1.main;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LongestCollatzTest {

	LongestCollatz lc;

	@Before
	public void setup() {
		lc = new LongestCollatz();
	}

	@Test
	public void findFor1() throws Exception {
		assertThat(lc.findLongest(1), is(equalTo(1)));
	}

	@Test
	public void findFor10() throws Exception {

		assertThat(lc.findLongest(10), is(equalTo(9)));
	}

	@Test
	public void findFor8() throws Exception {
		assertThat(lc.findLongest(8), is(equalTo(7)));
	}

	@Test
	public void findFor1Mill() throws Exception {
		assertThat(lc.findLongest(1000000), is(equalTo(837799)));
	}

	@Test
	public void findFor5Mill() throws Exception {
		// adding start and end time to check that result is received in less
		// than 10 sec
		long start = new Date().getTime();
		int result = lc.findLongest(1000000);
		long end = new Date().getTime();
		assertThat(result, is(equalTo(837799)));
		// 10,000 mils == 10sec
		assertThat(end - start, is(lessThan(10000L)));
	}

	@Test
	public void getSequence10EmptyMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 10
		List<Long> seq = new ArrayList<Long>();
		seq.add(10L);
		seq.add(5L);
		seq.add(16L);
		seq.add(8L);
		seq.add(4L);
		seq.add(2L);
		seq.add(1L);

		// Empty map should cause the getSequence to return 0
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int result = lc.createSequence(10, map);
		assertThat(result, is(equalTo(0)));
		for (int i = 0; i < lc.seq.size(); i++) {
			assertThat(lc.seq.get(i), is(equalTo(seq.get(i))));
		}

	}

	@Test
	public void getSequence4EmptyMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 4
		List<Long> seq = new ArrayList<Long>();
		seq.add(4L);
		seq.add(2L);
		seq.add(1L);
		;
		// Empty map should cause the getSequence to return 0
		Map<Long, Integer> map = new HashMap<Long, Integer>();

		int result = lc.createSequence(4, map);
		assertThat(result, is(equalTo(0)));
		for (int i = 0; i < lc.seq.size(); i++) {
			assertThat(lc.seq.get(i), is(equalTo(seq.get(i))));
		}

	}

	@Test
	public void getSequence10FourInMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 10 and sequence will be truncated at 8 because the length of 4 is
		// already established
		List<Long> seq = new ArrayList<Long>();
		seq.add(10L);
		seq.add(5L);
		seq.add(16L);
		seq.add(8L);

		// Map with 4 and value 3 should cause the getSequence to return 3
		// because 4 is in the sequence for 10
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(4L, 3);
		int result = lc.createSequence(10, map);
		assertThat(result, is(equalTo(3)));
		for (int i = 0; i < lc.seq.size(); i++) {
			assertThat(lc.seq.get(i), is(equalTo(seq.get(i))));
		}
	}

	@Test
	public void getSequence4TwoInMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 4 and sequence will be truncated at 4 because the length of 2 is
		// already established
		List<Long> seq = new ArrayList<Long>();
		seq.add(4L);

		// Map with 2 and value 2 should cause the getSequence to return 2
		// because 2 is in the sequence for 4
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(2L, 2);
		int result = lc.createSequence(4, map);
		assertThat(result, is(equalTo(2)));
		for (int i = 0; i < lc.seq.size(); i++) {
			assertThat(lc.seq.get(i), is(equalTo(seq.get(i))));
		}
	}

	@Test
	public void getNumbersInArrayFor10() throws Exception {
		int[] numbers = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] result = lc.getNumbersInArray(10);

		for (int i = 0; i < result.length; i++) {
			assertThat(result[i], is(equalTo(numbers[i])));
		}
	}

	@Test
	public void getMaxStartFor4() throws Exception {
		int[] numbers = { 4, 3, 2, 1 };
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(4L, 3);
		map.put(3L, 8);
		map.put(2L, 2);
		map.put(1L, 1);

		assertThat(lc.getStartForMaxLength(map, numbers), is(equalTo(3)));
	}

	@Test
	public void getMaxStartWithWrongValues() throws Exception {
		// This test will prove that the getStartForMaxLength will
		// always return the key with the greatest value and it does not do any
		// Collatz calulations
		int[] numbers = { 4, 3, 2, 1 };
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(4L, 3);
		map.put(3L, 8);
		map.put(2L, 2);
		map.put(1L, 10);

		assertThat(lc.getStartForMaxLength(map, numbers), is(equalTo(1)));
	}

	@Test
	public void addSequenceToMap() throws Exception {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int[] numbers = { 4, 3, 2, 1 };
		lc.numbers = numbers;
		// Creating list to use as the seq to be added and setting lc's seq to it
		List<Long> seq = new ArrayList<Long>();
		seq.add(4L);
		seq.add(2L);
		seq.add(1L);
		lc.seq = seq;
		
		lc.addSequenceToMap(map, 4, 0);
		//The add method should empty the sequence
		assertThat(lc.seq.size(), is(equalTo(0)));
		//The add method should mark 2 and 1 as zero because they are in 4's sequence
		assertThat(lc.numbers[2], is(equalTo(0)));
		assertThat(lc.numbers[3], is(equalTo(0)));
		//The add method should not have marked 4 and 3 as zero because they are not in 4's sequence
		assertThat(lc.numbers[0], is(equalTo(4)));
		assertThat(lc.numbers[1], is(equalTo(3)));
		//4,2 and 1 should all be in the map now with values of 3,2 and 1 respectively
		assertNotNull(map.get(4L));
		assertThat(map.get(4L), is(equalTo(3)));
		assertNotNull(map.get(2L));
		assertThat(map.get(2L), is(equalTo(2)));
		assertNotNull(map.get(1L));
		assertThat(map.get(1L), is(equalTo(1)));
		
	}
	
	@Test
	public void addSequenceToMapWithAddedLength() throws Exception {
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		int[] numbers = { 4, 3, 2, 1 };
		lc.numbers = numbers;
		// Creating list to use as the seq to be added and setting lc's seq to it
		List<Long> seq = new ArrayList<Long>();
		seq.add(4L);
		seq.add(2L);
		seq.add(1L);
		lc.seq = seq;
		//Making added length 3 will added 3 to the values put into the map on the previous test.
		lc.addSequenceToMap(map, 4, 3);
		//The add method should empty the sequence
		assertThat(lc.seq.size(), is(equalTo(0)));
		//The add method should mark 2 and 1 as zero because they are in 4's sequence
		assertThat(lc.numbers[2], is(equalTo(0)));
		assertThat(lc.numbers[3], is(equalTo(0)));
		//The add method should not have marked 4 and 3 as zero because they are not in 4's sequence
		assertThat(lc.numbers[0], is(equalTo(4)));
		assertThat(lc.numbers[1], is(equalTo(3)));
		//4,2 and 1 should all be in the map now with values of 3,2 and 1 respectively
		assertNotNull(map.get(4L));
		assertThat(map.get(4L), is(equalTo(3+3)));
		assertNotNull(map.get(2L));
		assertThat(map.get(2L), is(equalTo(2+3)));
		assertNotNull(map.get(1L));
		assertThat(map.get(1L), is(equalTo(1+3)));
		
	}
}
