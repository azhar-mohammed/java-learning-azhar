
package com.refactor;

import java.io.Serializable;
import java.util.Date;

/**
 * Book : Simple data class representing Book data.
 * 
 * @author chandrashekarv
 *
 */
public abstract class Book implements Serializable {

	private static final long serialVersionUID = -7348792584072115788L;

	private Date releaseDate;
	private long id;
	private String title;
	private int bookCategory;

	public Book(final String title, final int bookCategory, final Date releaseDate) {
		this.title = title;
		this.bookCategory = bookCategory;
		this.releaseDate = releaseDate;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(int bookCategory) {
		this.bookCategory = bookCategory;
	}

	abstract public double fetchPrice(int rentedDays);

	abstract public int getPoints(int rentedDays);
}
