package com.demo.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.hibernate.beans.Book;

public class BookDAO extends HibernateDaoSupport implements IBookDAO {

	public void insertBook(Book book) {
		getHibernateTemplate().saveOrUpdate(book);
	}

	public Book getBook(String bookname) {
		return (Book) getHibernateTemplate().get(Book.class, bookname);
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {
		return getHibernateTemplate().find("from Book");
	}

	@SuppressWarnings("unchecked")
	public List<Book> getSomeBooks(String category) {
		Object[] value = { category };
		String hql = "from Book where category = ?";
		return getHibernateTemplate().find(hql, value);
	}

	public void deleteBook(String bookname) {
		Object p = getHibernateTemplate().load(Book.class, bookname);
		getHibernateTemplate().delete(p);
	}

	public boolean isExist(final String bookname) {
		List<?> list = (List<?>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<?> result = session.createCriteria(Book.class)
								.add(Restrictions.eq("bookname", bookname))
								.list();
						return result;
					}
				});
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
