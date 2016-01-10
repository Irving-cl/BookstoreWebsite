<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="login.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style>
.tips {
	color: red;
	font-style: italic;
	font-size: 15px;
}
</style>
</head>
<body>
	<hr>
	<table border="0" width="100%">
		<tr align="left">
			<td width="65%" align="center"><img src="imgs/cover.jpg"
				height="500px"></td>
			<td width="35%" align="center">
				<div id="content">
					<form name="form" action="login.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							style="border-collapse: separate; border-spacing: 10px;"
							class="form_table">
							<tr>
								<td valign="middle" align="right"><bean:message
										key="login.page.username" /></td>
								<td valign="middle" align="right"><logic:present
										name="loginFormBean">
										<html:text property="username" name="loginFormBean" />
									</logic:present> <logic:notPresent name="loginFormBean">
										<input type="text" name="username" width="100%">
									</logic:notPresent> <span class="tips"><html:errors property="username" /></span></td>
							</tr>
							<tr>
								<td valign="middle" align="right"><bean:message
										key="login.page.password" /></td>
								<td valign="middle" align="left"><logic:present
										name="loginFormBean">
										<html:password property="password" name="loginFormBean" />
									</logic:present> <logic:notPresent name="loginFormBean">
										<input type="password" name="password" width="100%">
									</logic:notPresent> <span class="tips"><html:errors property="password" /></span></td>
							</tr>
						</table>
						<span class="tips"><html:errors
								property="org.apache.struts.action.GLOBAL_MESSAGE" /></span>
						<p>
							<input type="submit" name="submit" class="button"
								value="<bean:message key='login.page.login' />">
						</p>
						<p>
							<a href="register.do?method=init"> <bean:message
									key="login.page.register" />
							</a>
						</p>
					</form>
					<p>
						<a href="admin.do?method=init"> <bean:message
								key='login.page.admin_entrance' />
						</a>
					</p>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>