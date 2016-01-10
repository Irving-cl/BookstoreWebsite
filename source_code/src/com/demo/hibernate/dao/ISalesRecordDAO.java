package com.demo.hibernate.dao;

import java.util.List;

import com.demo.hibernate.beans.SalesRecord;

public interface ISalesRecordDAO {

	public void insertRecord(SalesRecord record);

	public SalesRecord getRecord(Integer id);

	public List<SalesRecord> getRecords();

	public void deleteRecord(Integer id);
}
