<%@taglib uri="http://www.yanqunsimple.com" prefix="yqs"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

            <yqs:login>
                 ${sessionScope.name},已登录
                 ${name}
            </yqs:login>
</body>
</html>
