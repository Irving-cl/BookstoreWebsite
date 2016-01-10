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
<title><bean:message key="welcome.page.shopping_cart" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<hr>
	<p align="left">
		<font size="5px"><bean:message key="shoppingcart.page.yourcart" /></font>
	</p>
	<table border="0" border="0" cellspacing="1" cellpadding="10"
		bgcolor="#cfcfcf">

		<tr>
			<td bgcolor="#ffffff"><bean:message
					key="shoppingcart.page.bookname" /></td>
			<td bgcolor="#ffffff"><bean:message key="shoppingcart.page.num" /></td>
			<td bgcolor="#ffffff"><bean:message
					key="shoppingcart.page.order_id" /></td>
			<td bgcolor="#ffffff"></td>
			<td bgcolor="#ffffff"></td>
			<td bgcolor="#ffffff"></td>
		</tr>
		<c:forEach var="item" items="${orders}">
			<tr>
				<td bgcolor="#ffffff">${item.bookname }</td>
				<td bgcolor="#ffffff">${item.getNum() }</td>
				<td bgcolor="#ffffff">${item.getId() }</td>
				<td style="text-align: center" bgcolor="#ffffff">
					<button onclick="PayOrder(this)" class="buy_button">
						<bean:message key="shoppingcart.page.pay" />
					</button>
				</td>
				<td style="text-align: center" bgcolor="#ffffff">
					<button onclick="RemoveOrder(this)" class="delete_button">
						<bean:message key="admin.page.remove" />
					</button>
				</td>
				<td style="text-align: center" bgcolor="#ffffff">
					<button onclick="ModifyOrder(this)" class="modify_button">
						<bean:message key="admin.page.modify" />
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="back.do"><bean:message key="admin.page.return" /></a>

	<script type="text/javascript">
		function PayOrder(node) {
			var id = node.parentNode.parentNode.childNodes[5].innerHTML;
			window.location.href = "shoppingcart.do?method=pay&order_id=" + id;
		}
		function RemoveOrder(node) {
			var id = node.parentNode.parentNode.childNodes[5].innerHTML;
			window.location.href = "shoppingcart.do?method=remove_order&order_id="
					+ id;
		}
		function ModifyOrder(node) {
			var id = node.parentNode.parentNode.childNodes[5].innerHTML;
			window.location.href = "modify_order.do?method=init&order_id=" + id;
		}
	</script>
</body>
</html>