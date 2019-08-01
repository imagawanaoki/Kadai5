<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<title> - AllBirthaday - </title>
</head>
<body>
		<!-- ヘッダーの表示 -->
		<%@include file="/JSP/common/header.jsp" %>

<!-- 過去半年全体のおみくじ結果のあ割合表示 -->
<table class = "contain">
<caption class="font">＜過去半年の運勢結果の割合＞</caption>
<tr>
<th>運勢</th><td>割合</td>
</tr>

<c:forEach var="results" items="${result}">
<tr>
<th><c:out value = "${results.key}"></c:out></th>
<td><c:out value = "${results.value}%"></c:out></td>
</tr>
</c:forEach>
</table>



<!-- 本日行った占い結果の割合表示-->
<table class = "contain">

<caption class="font"> ＜本日の運勢結果の割合＞</caption>
<tr>
<th>運勢</th><td>割合</td>
</tr>
<c:forEach var="resultt" items="${resulttoday}">
<tr>
<th><c:out value = "${resultt.key}"></c:out></th>
<td><c:out value = "${resultt.value}%"></c:out></td>
</tr>
</c:forEach>
</table>

<!-- 誕生日入力画面に遷移 -->
<div align = "center">
<div class ="padding">
<html:link forward ="fail">入力画面に戻る</html:link>
</div>
</div>
</body>

</html:html>