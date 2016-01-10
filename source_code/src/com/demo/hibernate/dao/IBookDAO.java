package com.demo.hibernate.dao;

import java.util.List;

import com.demo.hibernate.beans.Book;

public interface IBookDAO {

	public void insertBook(Book book);

	public Book getBook(String bookname);

	public List<Book> getBooks();

	public void deleteBook(String bookname);

}
