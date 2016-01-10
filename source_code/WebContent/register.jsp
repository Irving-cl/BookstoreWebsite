<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="register.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<font size="5px"><bean:message key="register.page.new_user" /></font>
	<form name="form" action="register.do?method=register" method="post">
		<table border="0" align="center">
			<tr>
				<td><bean:message key="register.page.username" /></td>
				<td><input type="text" name="username"> <html:errors
						property="username" /></td>
			</tr>
			<tr>
				<td><bean:message key="register.page.password1" /></td>
				<td><input type="password" name="password1"> <html:errors
						property="password1" /></td>
			</tr>
			<tr>
				<td><bean:message key="register.page.password2" /></td>
				<td><input type="password" name="password2"> <html:errors
						property="password2" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					name="submit" value="<bean:message key='register.page.register'/>"></td>
			</tr>
		</table>
		<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
	</form>
	<p align="center">
		<a href="index.do"><bean:message key="register.page.back" /></a>
	</p>
</body>
</html>