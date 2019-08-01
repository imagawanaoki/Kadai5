<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<!-- 誕生日入力画面 -->
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<title>誕生日入力画面</title>
</head>
<body background="torii.jpg">
	<!-- ヘッダーの表示 -->
<%@include file="/JSP/common/header.jsp" %>

<h3>誕生日を入力してください</h3>

<html:messages id="msg" message="false">
    <%-- filter属性をfalseにすることでメッセージにHTMLタグを利用できます。trueにするとHTMLタグがあった場合置換文字（<、>など）に変換します。 --%>
    <bean:write name="msg" ignore="true" filter="false"/>
</html:messages>

 <br>
   <html:form action ="/InputDisplay" method = "get"><html:text property = "birthday"/>
	<input type = "submit" value = "おみくじを引く">
   </html:form>
 <br>

</body>
</html:html>
