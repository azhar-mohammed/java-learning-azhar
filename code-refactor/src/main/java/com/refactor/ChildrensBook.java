package com.refactor;

import java.util.Date;

/**
 * This class represents a childrens book
 * 
 * @author azharm
 *
 */
public class ChildrensBook extends Book {

	private static final long serialVersionUID = 1L;

	public ChildrensBook(final String title, final Date releaseDate) {

		super(title, 3, releaseDate);
	}

	@Override
	public double fetchPrice(int rentedDays) {

		double thisAmount = 0;

		thisAmount += 1.5;

		if (rentedDays > 3)
			thisAmount += (rentedDays - 3) * 2;

		return thisAmount;
	}

	@Override
	public int getPoints(int rentedDays) {

		return 1;
	}

}
