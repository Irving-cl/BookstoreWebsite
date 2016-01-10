<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="modify_book.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="modify_book.page.title" /></font>
	</p>
	<form name="form" action="modify_book.do?method=modify" method="post">
		<table border="0">
			<tr>
				<td colspan="2" align="left"><bean:message
						key="add_book.page.bookname" />： <input type="text"
					name="bookname" value="<%=session.getAttribute("bookname")%>"
					readonly /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.img_name" /></td>
				<td align="left"><input type="text" name="img_name"
					value="<%=session.getAttribute("img_name")%>"> <html:errors
						property="img_name" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.category" /></td>
				<td align="left"><input type="text" name="category"
					value="<%=session.getAttribute("category")%>"> <html:errors
						property="category" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.price" /></td>
				<td align="left"><input type="text" name="price"
					value="<%=session.getAttribute("price")%>"> <html:errors
						property="price" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.stock_num" /></td>
				<td align="left"><input type="text" name="stock_num"
					value="<%=session.getAttribute("stock_num")%>"> <html:errors
						property="stock_num" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.description" /></td>
				<td><textarea name="description" cols="40" rows="10"><%=session.getAttribute("description")%></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><input type="submit" name="submit"
					class="modify_button"
					value="<bean:message key='modify_book.page.submit'/>"></td>
			</tr>
			<tr>
				<td><html:errors
						property="org.apache.struts.action.GLOBAL_MESSAGE" /></td>
			</tr>
		</table>
		<p align="left">
			<a href="admin.do?method=init"><bean:message
					key="add_book.page.back" /></a>
		</p>
	</form>
</body>
</html>