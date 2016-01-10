package com.demo.struts.forms;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

@SuppressWarnings("serial")
public class BookForm extends ActionForm {

	protected String bookname = null;
	protected String img_name = null;
	protected String category = null;
	protected String description = null;
	protected float price;
	protected Integer stock_num;

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock_num() {
		return stock_num;
	}

	public void setStock_num(Integer stock_num) {
		this.stock_num = stock_num;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public ActionErrors validate(ActionMapping argo, HttpServletRequest arg1) {
		ActionErrors errors = new ActionErrors();
		String queryString = arg1.getQueryString();
		if (queryString.equalsIgnoreCase("method=add")
				|| queryString.equalsIgnoreCase("method=modify")) {
			if (bookname == null || bookname.equals("")) {
				errors.add("bookname", new ActionMessage(
						"add_book.error.bookname"));
			}
			if (img_name == null || img_name.equals("")) {
				errors.add("img_name", new ActionMessage(
						"add_book.error.img_name"));
			}
			if (category == null || category.equals("")) {
				errors.add("category", new ActionMessage(
						"add_book.error.category"));
			}
			if (price <= 0) {
				errors.add("price", new ActionMessage("add_book.error.price"));
			}
			if (stock_num <= 0) {
				errors.add("stock_num", new ActionMessage(
						"add_book.error.stock_num"));
			}
		}
		return errors;
	}
}
