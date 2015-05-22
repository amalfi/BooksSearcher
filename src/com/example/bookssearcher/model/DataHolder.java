package com.example.bookssearcher.model;

import java.util.List;

public class DataHolder {
	private static List<Book> data;
	private static Book selectedBook;

	public static Book getSelectedBook() {
		return selectedBook;
	}

	public static void setSelectedBook(Book selectedBook) {
		DataHolder.selectedBook = selectedBook;
	}

	public static List<Book> getBookList() {
		return data;
	}

	public static void setBookList(List<Book> booklist) {
		data = booklist;
	}

}