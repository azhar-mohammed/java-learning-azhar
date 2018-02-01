package com.refactor;

import java.util.Date;

/**
 * This class represents a fictional book.
 * 
 * @author azharm
 *
 */
public class FictionalBook extends Book {

	private static final long serialVersionUID = 1L;

	public FictionalBook(final String title, final Date releaseDate) {

		super(title, 1, releaseDate);
	}

	@Override
	public double fetchPrice(int rentedDays) {

		double thisAmount = 0;

		thisAmount += 2;

		if (rentedDays > 2)
			thisAmount += (rentedDays - 2) * 1.5;

		return thisAmount;
	}

	@Override
	public int getPoints(int rentedDays) {

		if (rentedDays > 2)
			return 2;

		return 1;
	}

}
