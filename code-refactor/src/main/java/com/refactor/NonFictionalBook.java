package com.refactor;

import java.util.Date;

/**
 * This class represents a non fictional book
 * @author azharm
 *
 */
public class NonFictionalBook extends Book {

	private static final long serialVersionUID = 1L;

	public NonFictionalBook(final String title,final Date releaseDate) {
		super(title, 2, releaseDate);
	}

	@Override
	public double fetchPrice(int rentedDays) {

		return rentedDays * 3;

	}

	@Override
	public int getPoints(int rentedDays) {
		// TODO Auto-generated method stub
		return 1;
	}
}
