package com.nku.team1.main;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
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
	public void getSequence10EmptyMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 10
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(10);
		seq.add(5);
		seq.add(16);
		seq.add(8);
		seq.add(4);
		seq.add(2);
		seq.add(1);

		//Empty map should cause the getSequence to return 0
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		int result = lc.getSequence(10, map);
		assertThat(result, is(equalTo(0)));
		for (int i = 0; i < lc.seq.size(); i++) 
		{
			assertThat(lc.seq.get(i), is(equalTo(lc.seq.get(i))));
		}

	}
	
	@Test
	public void getSequence4EmptyMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 4
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(4);
		seq.add(2);
		seq.add(1);
		//Empty map should cause the getSequence to return 0
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		
		int result = lc.getSequence(4, map);
		assertThat(result, is(equalTo(0)));
		for (int i = 0; i < lc.seq.size(); i++) 
		{
			assertThat(lc.seq.get(i), is(equalTo(lc.seq.get(i))));
		}

	}
	
	@Test
	public void getSequence10FourInMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 10 and sequence will be truncated at 8 because the length of 4 is already established
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(10);
		seq.add(5);
		seq.add(16);
		seq.add(8);

		//Map with 4 and value 3 should cause the getSequence to return 3 because 4 is in the sequence for 10
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		map.put(4L, 3);	
		int result = lc.getSequence(10, map);
		assertThat(result, is(equalTo(3)));
		for (int i = 0; i < lc.seq.size(); i++) 
		{
			assertThat(lc.seq.get(i), is(equalTo(lc.seq.get(i))));
		}
	}
	
	@Test
	public void getSequence4TwoInMap() throws Exception {
		// Creating list to compare to This should be the resulting sequence for
		// 10 and sequence will be truncated at 8 because the length of 4 is already established
		List<Integer> seq = new ArrayList<Integer>();
		seq.add(10);
		seq.add(5);
		seq.add(16);
		seq.add(8);

		//Map with 2 and value 2 should cause the getSequence to return 2 because 2 is in the sequence for 4
		Map<Long,Integer> map = new HashMap<Long,Integer>();
		map.put(2L, 2);	
		int result = lc.getSequence(4, map);
		assertThat(result, is(equalTo(2)));
		for (int i = 0; i < lc.seq.size(); i++) 
		{
			assertThat(lc.seq.get(i), is(equalTo(lc.seq.get(i))));
		}
	}
	
	@Test
	public void getNumbersInArrayFor10() throws Exception {
		int[] numbers = {10,9,8,7,6,5,4,3,2,1};
		int[] result = lc.getNumbersInArray(10);
	
		for (int i = 0; i < result.length; i++) 
		{
			assertThat(result[i], is(equalTo(numbers[i])));
		}
	}
	
}
