package com.nku.team1.main;

import java.util.Iterator;

import com.nku.team1.exception.CollatzOutOfBoundsException;

public class Collatz implements Iterator<Long>, Iterable<Long>{
	
	protected long current;
	
	public Collatz(int start)
	{
		current = start;
	}

	@Override
	public boolean hasNext() {
		if(current > 1)
		{
			return true;
		}
		if(current == 1)
		{
			return false;
		}
		throw new CollatzOutOfBoundsException();
	}

	@Override
	public Long next() {
		if(current <=1)
		{
			throw new CollatzOutOfBoundsException();
		}
		if(current % 2 ==0)
		{
			current = current/2;
		}else{

			current = current*3+1;
		}
		return current;
	}

	@Override
	public Iterator<Long> iterator() {
		return this;
	}
}