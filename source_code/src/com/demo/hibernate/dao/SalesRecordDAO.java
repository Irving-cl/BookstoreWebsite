package com.demo.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.demo.hibernate.beans.SalesRecord;

public class SalesRecordDAO extends HibernateDaoSupport implements
		ISalesRecordDAO {

	public void insertRecord(SalesRecord record) {
		getHibernateTemplate().saveOrUpdate(record);
	}

	public SalesRecord getRecord(Integer id) {
		return (SalesRecord) getHibernateTemplate().get(SalesRecord.class,
				new Integer(id));
	}

	@SuppressWarnings("unchecked")
	public List<SalesRecord> getRecords() {
		return getHibernateTemplate().find("from SalesRecord");
	}

	@SuppressWarnings("unchecked")
	public List<SalesRecord> getSomeRecords(String bookname, String username) {
		String hql = "";
		List<SalesRecord> list = new ArrayList<SalesRecord>();
		if (bookname.equals("") && username.equals("")) {
			hql = "from SalesRecord";
			list = getHibernateTemplate().find(hql);
		} else if (bookname.equals("") && !username.equals("")) {
			hql = "from SalesRecord where username = ?";
			Object value[] = { username };
			list = getHibernateTemplate().find(hql, value);
		} else if (!bookname.equals("") && username.equals("")) {
			hql = "from SalesRecord where bookname = ?";
			Object value[] = { bookname };
			list = getHibernateTemplate().find(hql, value);
		} else {
			hql = "from SalesRecord where bookname = ? and username = ?";
			Object value[] = { bookname, username };
			list = getHibernateTemplate().find(hql, value);
		}
		return list;
	}

	@Override
	public void deleteRecord(Integer id) {
		Object p = getHibernateTemplate().load(SalesRecord.class,
				new Integer(id));
		getHibernateTemplate().delete(p);
	}
}
