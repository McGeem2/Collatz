package com.nku.team1.main;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.nku.team1.exception.CollatzOutOfBoundsException;

public class CollatzTest {
	Collatz collatz0;
	Collatz collatz1;
	Collatz collatz3;
	Collatz collatz9;
	Collatz collatz10;
	Collatz collatz100;
	Collatz collatz1000;
	Collatz collatz10000;
	Collatz collatzNegative;
	int [] test10 = {5,16,8,4,2,1};
	int [] test100 = {50,25,76,38,19,58,29,88,44,22,11,34,17,52,26,13,40,20,10,5,16,8,4,2,1};
	@Before
	public void setup() {
		
		collatzNegative = new Collatz(-5);
		collatz0 = new Collatz(0);
		collatz1 = new Collatz(1);
		collatz3 = new Collatz(3);
		collatz9 = new Collatz(9);
		collatz10 = new Collatz(10);
		collatz100 = new Collatz(100);
		collatz1000 = new Collatz(1000);
		collatz10000 = new Collatz(10000);
	}

	@Test
	public void testHasNext10() throws Exception {

		assertTrue(collatz10.hasNext());

	}

	@Test
	public void testHasNext1() throws Exception {

		assertFalse(collatz1.hasNext());

	}

	@Test(expected = CollatzOutOfBoundsException.class)
	public void testHasNextNegative() throws Exception {

		collatzNegative.hasNext();

	}

	@Test
	public void testNext10() throws Exception {

		assertThat(collatz10.next(), is(equalTo(5L)));

	}

	@Test
	public void testNext1() throws Exception {

		assertThat(collatz100.next(), is(equalTo(50L)));

	}

	@Test
	public void testNext3() throws Exception {

		assertThat(collatz3.next(), is(equalTo(10L)));

	}

	@Test
	public void testNext9() throws Exception {

		assertThat(collatz9.next(), is(equalTo(28L)));

	}

	@Test(expected = CollatzOutOfBoundsException.class)
	public void testNextNegative() throws Exception {

		collatzNegative.next();

	}

	@Test(expected = CollatzOutOfBoundsException.class)
	public void testNextZero() throws Exception {

		collatz0.next();

	}

	@Test
	public void testIterate10() throws Exception {
		int count = 0;
		
		Iterator<Long> it = collatz10.iterator();
		while(it.hasNext()){
			assertThat(it.next(), is(equalTo((long) test10[count])));
			count++;
		}
	}
	@Test
	public void testIterate100() throws Exception {
		int count = 0;
		
		Iterator it = collatz100.iterator();
		while(it.hasNext())
		{
			assertThat(it.next(), is(equalTo((long) test100[count])));
			count++;
		}
	}

}
