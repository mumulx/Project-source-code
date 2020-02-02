<%--
  Created by IntelliJ IDEA.
  User: 26069
  Date: 2020/2/2
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
<form:form commandName="per" action="textForm2">
    <for:checkbox path="sex"/>
    <input type="submit" value="提交">
</form:form>
--%>

<%--
<form:form commandName="per" action="textForm">
    <form:checkbox path="hobbies" value="football"></form:checkbox>
    <form:checkbox path="hobbies" value="basketball"></form:checkbox>
    <form:checkbox path="hobbies" value="pingpang"></form:checkbox>
    <input type="submit" value="提交">
</form:form>
--%>
<%--
<form:form commandName="per" action="textForm">
    <form:checkbox path="other" value="football"></form:checkbox>
    <form:checkbox path="other" value="basketball"></form:checkbox>
    <form:checkbox path="other" value="pingpang"></form:checkbox>
    <input type="submit" value="提交">
</form:form>
--%>


<%--
<form:form commandName="per" action="textForm">
    <form:checkboxes path="hobbies" items="${allHobbies}"/>
    <input type="submit" value="提交">
</form:form>
--%>

<%--
<form:form commandName="per" action="textForm">
   中国：<form:radiobutton path="country" value="China"/>
   美国：<form:radiobutton path="country" value="USE"/>
    <input type="submit" value="提交">
</form:form>
--%>
<%--
<form:form commandName="per" action="textForm">
    <form:radiobuttons path="favouriteBall" items="${allBallMap}" delimiter="、"/>
    <input type="submit" value="提交">
</form:form>

--%>

<%--
<form:form commandName="per" action="textForm">
    <form:select path="favouriteBall" items="${allBallMap}"/>
    <input type="submit" value="提交">
</form:form>
--%>

<form:form commandName="per" action="textForm">
    <form:select path="favouriteBall">
        <form:options items="${allBallMap}"></form:options>
       <%-- <form:option value="football">足球11</form:option>
        <form:option value="basketball">篮球11</form:option>
        <form:option value="pingpang">乒乓球11</form:option>
        <form:option value="aaa">bbb11</form:option>--%>
    </form:select>
    <input type="submit" value="提交">
</form:form>



</body>
</html>
