
package com.refactor;

import java.io.Serializable;

/**
 * Represent a customer renting a book.
 * 
 * @author chandrashekarv
 *
 */
public class Rental implements Serializable {

	private static final long serialVersionUID = 1256869448913370768L;

	private Book book;

	private int daysRented;

	public Rental(Book book, int daysRented) {
		super();
		this.book = book;
		this.daysRented = daysRented;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	public double getRentAmount() {
		// determine amounts for each book.
		return getBook().fetchPrice(getDaysRented());
	}

	public int getPoints() {

		return getBook().getPoints(getDaysRented());

		
	}

}
