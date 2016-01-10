<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="search_record.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="search_record.page.title" /></font>
	</p>
	<table border="0" cellspacing="1" cellpadding="10" bgcolor="#cfcfcf">
		<tr>
			<td bgcolor="#ffffff"><bean:message key="search_record.page.id" /></td>
			<td bgcolor="#ffffff"><bean:message
					key="search_record.page.bookname" /></td>
			<td bgcolor="#ffffff"><bean:message key="search_record.page.num" /></td>
			<td bgcolor="#ffffff"><bean:message
					key="search_record.page.username" /></td>
			<td bgcolor="#ffffff"><bean:message
					key="search_record.page.time" /></td>
		</tr>

		<c:forEach var="item" items="${records}">
			<tr>
				<td bgcolor="#ffffff">${item.id }</td>
				<td bgcolor="#ffffff">${item.bookname }</td>
				<td bgcolor="#ffffff">${item.num }</td>
				<td bgcolor="#ffffff">${item.username }</td>
				<td bgcolor="#ffffff">${item.date }</td>
			</tr>
		</c:forEach>
	</table>
	<p align="left">
		<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
	</p>
	<a href="admin.do?method=init"><bean:message
			key="add_book.page.back" /></a>
</body>
</html>