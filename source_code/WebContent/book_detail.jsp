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
<title><bean:message key="book_detail.page.title" /></title>
</head>
<body>
	<hr>
	<table border="0" width="80%" align="center">
		<tr align="left">
			<td rowspan="6" width="45%" align="left"><img
				src="imgs/<%=session.getAttribute("img_name")%>" height="300px">
			</td>
			<td width="55" align="left"><%=session.getAttribute("bookname")%>
			</td>
		</tr>
		<tr>
			<td align="left"><bean:message key="book_detail.page.category" />
				<%=session.getAttribute("category")%></td>
		</tr>
		<tr>
			<td align="left"><textarea cols="100" rows="10" readonly><%=session.getAttribute("description")%></textarea>
			</td>
		</tr>
		<tr>
			<td align="left"><bean:message key="book_detail.page.price" />
				<%=session.getAttribute("price")%></td>
		</tr>
		<tr>
			<td align="left"><bean:message key="book_detail.page.stock_num" />
				<%=session.getAttribute("stock_num")%></td>
		</tr>
		<tr>
			<td align="left">
				<form name="form" action="buybook.do?method=make_order"
					method="post">
					<input type="text" name="num"> <input type="submit"
						name="submit" value="<bean:message key='book_detail.page.buy' />">
					<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
				</form>
			</td>
		</tr>
	</table>
	<a href="back.do"><bean:message key="admin.page.return" /></a>
</body>
</html>