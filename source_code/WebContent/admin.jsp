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
<title><bean:message key="admin.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="admin.page.title" /></font>
	</p>
	<table width="100%" border="0">
		<tr>
			<td width="40%" align="left">
				<h2>
					<bean:message key="admin.page.userlist" />
				</h2>
				<table border="0" width="100%" bgcolor="#cfcfcf">
					<tr>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.username" /></td>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.password" /></td>
						<td bgcolor="#ffffff" align="center"></td>
					</tr>

					<c:forEach var="item" items="${users}">
						<tr>
							<td bgcolor="#ffffff" align="center">${item.username }</td>
							<td bgcolor="#ffffff" align="center">${item.getPassword()}</td>
							<td bgcolor="#ffffff" align="center" style="text-align: center">
								<button onclick="RemoveUser(this)" class="delete_button">
									<bean:message key="admin.page.remove" />
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>

				<h2>
					<bean:message key="admin.page.modify_user" />
				</h2>
				<form name="form" action="modify.do?method=modify" method="post">
					<table>
						<tr>
							<td><bean:message key="admin.page.username" /> <input
								type="text" name="username" /></td>
						</tr>
						<tr>
							<td><bean:message key="admin.page.password" /> <input
								type="password" name="password" /></td>
						</tr>
						<tr>
							<td><input type="submit" class="modify_button"
								value="<bean:message key='admin.page.modify_confirm'/>">
								<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
							</td>
						</tr>
					</table>
				</form>
			</td>

			<td width="60%" valign="top" align="center">
				<h2>
					<bean:message key="admin.page.booklist" />
				</h2>
				<table border="0" width="100%" bgcolor="#cfcfcf">
					<tr>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.bookname" /></td>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.img_name" /></td>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.category" /></td>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.price" /></td>
						<td bgcolor="#ffffff" align="center"><bean:message
								key="admin.page.stock_num" /></td>
						<td bgcolor="#ffffff" align="center"></td>
						<td bgcolor="#ffffff" align="center"></td>
					</tr>

					<c:forEach var="item" items="${books}">
						<tr>
							<td bgcolor="#ffffff" align="center">${item.bookname }</td>
							<td bgcolor="#ffffff" align="center">${item.img_name }</td>
							<td bgcolor="#ffffff" align="center">${item.category }</td>
							<td bgcolor="#ffffff" align="center">${item.price }</td>
							<td bgcolor="#ffffff" align="center">${item.stock_num }</td>
							<td bgcolor="#ffffff" align="center" style="text-align: center">
								<button onclick="RemoveBook(this)" class="delete_button">
									<bean:message key="admin.page.remove" />
								</button>
							</td>
							<td bgcolor="#ffffff" align="center" style="text-align: center">
								<button onclick="ModifyBook(this)" class="modify_button">
									<bean:message key="admin.page.modify" />
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
				<p align="center">
					<a href="add_book.do?method=init"> <bean:message
							key="admin.page.add_book" />
					</a>
				</p>
			</td>
		</tr>
		<tr>
			<td colspan="2"><br />
				<h2>
					<bean:message key="admin.page.search_statistics" />
				</h2>
				<form name="form" action="search_record.do" method="post">
					<bean:message key="admin.page.search_category" />
					<select name="category">
						<option value="<bean:message key="everything" />">
							<bean:message key="everything" />
						</option>
						<option value="<bean:message key="computer_science" />">
							<bean:message key="computer_science" />
						</option>
						<option value="<bean:message key="chinese_literature" />">
							<bean:message key="chinese_literature" />
						</option>
					</select>
					<bean:message key="admin.page.search_bookname" />
					<input type="text" name="bookname">
					<bean:message key="admin.page.search_username" />
					<input type="text" name="username"> <input type="submit"
						value="<bean:message key='admin.page.search'/>">
				</form></td>
		</tr>
	</table>

	<a href="admin.do?method=quit"><bean:message
			key="admin.page.return" /></a>

	<script type="text/javascript">
		function RemoveUser(node) {
			var tr1 = node.parentNode.parentNode;
			window.location.href = "admin.do?method=removeUser&username="
					+ tr1.childNodes[1].innerHTML;
		}

		function RemoveBook(node1) {
			var tr2 = node1.parentNode.parentNode;
			window.location.href = "admin.do?method=removeBook&bookname="
					+ tr2.childNodes[1].innerHTML;
		}

		function ModifyBook(node2) {
			var tr3 = node2.parentNode.parentNode;
			window.location.href = "modify_book.do?method=init&bookname="
					+ tr3.childNodes[1].innerHTML;
		}
	</script>

</body>
</html>