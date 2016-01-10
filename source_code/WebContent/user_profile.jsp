<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="user_profile.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<font size="5px"><bean:message key="user_profile.page.myinfo" /></font>
	<form name="form" action="modify_profile.do?method=modify"
		method="post">
		<table border="0" align="center">
			<tr align="left">
				<td colspan="2"><bean:message key="user_profile.page.username" /><%=session.getAttribute("username")%>
				</td>
			</tr>
			<tr align="left">
				<td><bean:message key="user_profile.page.telephone" /></td>
				<td><input type="text" name="telephone"
					value="<%=session.getAttribute("telephone")%>">
			</tr>
			<tr align="left">
				<td><bean:message key="user_profile.page.address" /></td>
				<td><input type="text" name="address"
					value="<%=session.getAttribute("address")%>">
			</tr>
			<tr align="left">
				<td><bean:message key="user_profile.page.description" /></td>
				<td><textarea name="user_info" cols="60" rows="10"><%=session.getAttribute("user_info")%></textarea>
				</td>
			</tr>
			<tr align="left">
				<td colspan="2"><input type="submit" name="submit"
					value="<bean:message key='user_profile.page.submit'/>"></td>
			</tr>
			<tr align="left">
				<td><a href="back.do"><bean:message
							key="user_profile.page.back" /></a></td>
			</tr>
		</table>
	</form>
</body>
</html>