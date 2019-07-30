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
<title>- Path -</title>
</head>
<body>

				<!-- ヘッダーの表示 -->
		<%@include file="/JSP/common/header.jsp" %>
<div align = "center"><h2>入力された誕生日の過去半年の結果表示</h2>
</div>

<div align="center">
<table class="rate">


<tr>
<th>占い日</th><th>運勢</th><th>学問</th><th>願い事</th><th>商い</th>
</tr>

<c:forEach var="result" items="${resultbirth}">
<tr>
<td>${result.key}</td>
<c:forEach var="item" items = "${result.value}">

<td>${item}</td>

</c:forEach>
</tr>

</c:forEach>
</table>
</div>
		<!-- 誕生日入力画面に遷移 -->
			<div align ="center">
<html:link forward ="fail">入力画面に戻る</html:link>
</div>
<%@include file="/JSP/common/footer.jsp" %>

</body>
</html:html>