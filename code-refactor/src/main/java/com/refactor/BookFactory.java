package com.refactor;

import java.util.Date;

/**
 * This class is a factory to create a instance of a book based on bookCategory.
 * 
 * @author azharm
 *
 */
public class BookFactory {
	/**
	 * 
	 * @param bookCategory
	 * @param releaseDate
	 * @param id
	 * @param title
	 * @return Book 
	 * This method returns a instance of the book based on book category
	 */
	public Book createBookInstance(String bookCategory, Date releaseDate, String title) {
		Book book = null;

		switch (bookCategory) {
		case "CHILDREN":
			book = new ChildrensBook(title, releaseDate);
			break;

		case "FICTION":
			book = new FictionalBook(title, releaseDate);
			break;
			
		case "NON-FICTION":
			book = new NonFictionalBook(title, releaseDate);
			break;
			
		}
		return book;

	}
}
