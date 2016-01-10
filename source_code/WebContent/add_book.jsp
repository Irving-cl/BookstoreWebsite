<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="add_book.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="add_book.page.title" /></font>
	</p>
	<form name="form" action="add_book.do?method=add" method="post">
		<table border="0">
			<tr>
				<td><bean:message key="add_book.page.bookname" /></td>
				<td align="left"><input type="text" name="bookname"
					width="100%"> <html:errors property="bookname" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.img_name" /></td>
				<td align="left"><input type="text" name="img_name"
					width="100%"> <html:errors property="img_name" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.category" /></td>
				<td align="left"><input type="text" name="category"
					width="100%"> <html:errors property="category" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.price" /></td>
				<td align="left"><input type="text" name="price" width="100%">
					<html:errors property="price" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.stock_num" /></td>
				<td align="left"><input type="text" name="stock_num"
					width="100%"> <html:errors property="stock_num" /></td>
			</tr>
			<tr>
				<td><bean:message key="add_book.page.description" /></td>
				<td><textarea name="description" cols="40" rows="10">
                    </textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="left"><input type="submit" name="submit"
					class="modify_button"
					value="<bean:message key='add_book.page.submit'/>"></td>
			</tr>
			<tr>
				<td><html:errors
						property="org.apache.struts.action.GLOBAL_MESSAGE" /></td>
			</tr>
		</table>
		<p align="center">
			<a href="admin.do?method=init"><bean:message
					key="add_book.page.back" /></a>
		</p>
	</form>
</body>
</html>