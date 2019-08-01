<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page isELIgnored="false" %>

				<!-- おみくじ結果を画面表示 -->
<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
				<!-- ヘッダーの表示 -->
		<%@include file="/JSP/common/header.jsp" %>

	<div align= "center"><h2>おみくじ結果</h2></div>

				<!-- おみくじの結果を表示 Unseiクラスのフィールド名を使う -->

				<div class="table-scroll">
				<div align= "center">
		<table class = "size">

		<tr>
		<th>運勢 </th>
		<td> ${omikuji.unsei} </></td>
		</tr>
		<tr>
		<th>学問</th>
		<td>${omikuji.gakumon}</td>
		</tr>
		<tr>
		<th>願い事 </th>
		<td>${omikuji.negaigoto}</td>
		</tr>
		<tr>
		<th>商い</th>
		<td>${omikuji.akinai}</td>
		</tr>

		</table>
		</div>
		</div>
	<div align ="center">
					<html:form action ="/Path" method = "get"><html:hidden property = "uranai_date"  value = "${today}" />
	<html:hidden property = "birthday"  value = "${birth}" /><input type = "submit" value = "誕生日の結果リストを表示"/></html:form>

					<html:form action ="/PathAll" method = "get"><html:hidden property = "today" value = "${today}" />
	<input type = "submit"  value = "本日の占い結果と全体の占い結果の割合を表示する"/></html:form></div>

</body>
</html:html>