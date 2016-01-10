<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="modify_order.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="modify_order.page.title" /></font>
	</p>
	<form name="form" action="modify_order.do?method=modify" method="post">
		<table border="0" align="left">
			<tr>
				<td colspan="2" align="left"><bean:message
						key="modify_order.page.id" /> <input name="order_id"
					value="<%=session.getAttribute("id")%>" readonly /></td>
			</tr>
			<tr>
				<td colspan="2" align="left"><bean:message
						key="modify_order.page.bookname" /> <input name="bookname"
					value="<%=session.getAttribute("bookname")%>" readonly /></td>
			</tr>
			<tr>
				<td align="left"><bean:message key="modify_order.page.num" />
					<input type="text" name="num"
					value="<%=session.getAttribute("num")%>"> <html:errors
						property="org.apache.struts.action.GLOBAL_MESSAGE" /></td>
			</tr>
			<tr>
				<td colspan="2" align="left"><input type="submit" name="submit"
					class="modify_button"
					value="<bean:message key='modify_order.page.submit'/>"></td>
			</tr>
			<tr>
				<td align="left"><a href="shoppingcart.do?method=init"><bean:message
							key="modify_order.page.back" /></a></td>
			</tr>
		</table>
	</form>
</body>
</html>