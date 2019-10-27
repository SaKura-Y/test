<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<h2>
    该活动暂未开始，敬请期待
</h2>
<a href="<%=basePath%>resource/shop/shopping.jsp">返回上一层</a>
</body>
</html>
