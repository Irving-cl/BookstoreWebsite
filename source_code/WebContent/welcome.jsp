<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="welcome.page.title" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<form name="form1" action="logout.do" method="post">
		<table width="100%" border="0">
			<tr>
				<td width="20%" align="left"><font size="5px"> <bean:message
							key="welcome.page.username" /><%=(String) session.getAttribute("username")%>
				</font></td>
				<td width="80%" align="right"><input type="submit"
					name="submit" value="<bean:message key='welcome.page.logout' />">
				</td>
			</tr>
		</table>
	</form>
	<table border="0" width="100%" height="100%">
		<tr valign="top">
			<td width="25%">
				<table width="100%" height="100%" border="0"
					style="border-spacing: 30px;">
					<tr valign="middle">
						<td width="100%">
							<div align="center">
								<a href="shoppingcart.do?method=init" style="font-size: 20px;">
									<bean:message key="welcome.page.shopping_cart" />
								</a>
							</div>
						</td>
					</tr>
					<tr valign="middle">
						<td width="100%"><a href="modify_profile.do?method=init"
							style="font-size: 20px;"> <bean:message
									key="welcome.page.user_profile" />
						</a></td>
					</tr>
				</table>
			</td>
			<td width="75%" align="left">
				<h2>
					<bean:message key="welcome.page.search_books" />
				</h2>
				<form name="form" action="search_book.do" method="post">
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
					<input type="text" name="bookname"> <input type="submit"
						value="<bean:message key='admin.page.search'/>">
				</form> <html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" />
				<table border="0" cellspacing="1" cellpadding="10" bgcolor="#cfcfcf"
					width="100%">
					<tr>
						<td bgcolor="#ffffff"></td>
						<td bgcolor="#ffffff"><bean:message
								key="welcome.page.bookname" /></td>
						<td bgcolor="#ffffff"><bean:message key="welcome.page.price" /></td>
						<td bgcolor="#ffffff"><bean:message key="welcome.page.stock" /></td>
						<td bgcolor="#ffffff"></td>
					</tr>

					<c:forEach var="item" items="${books}">
						<tr>
							<td bgcolor="#ffffff" align="center"><img
								src="imgs/${item.getImg_name() }" width="105"></td>
							<td bgcolor="#ffffff" style="color: #0000ff; font-weight: bold;">
								${item.bookname }</td>
							<td bgcolor="#ffffff" style="color: #ff0000;">
								${item.getPrice()}</td>
							<td bgcolor="#ffffff" style="color: #666666;">
								${item.getStock_num()}</td>
							<td bgcolor="#ffffff" style="text-align: center">
								<button onclick="BookDetail(this)" class="buy_button">
									<bean:message key="welcome.page.buy" />
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		function BookDetail(node) {
			var bookname = new String(
					node.parentNode.parentNode.childNodes[3].innerHTML);
			window.location.href = "buybook.do?method=detail&bookname="
					+ bookname;
		}
	</script>
</body>
</html>