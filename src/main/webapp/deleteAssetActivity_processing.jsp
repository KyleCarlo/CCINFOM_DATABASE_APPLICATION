<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 16/04/2023
  Time: 8:41 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Asset Activity</title>
</head>
<body>
<jsp:useBean id="Activity" class="actsmgt.AssetAct" scope="session"/>
<%
    String delete_activity = request.getParameter("delete_activity");
    String[] sep = delete_activity.split(",", 2);
    Activity.asset_id = Integer.valueOf(sep[0]);
    Activity.activity_date = sep[1];

    int result = Activity.deleteAssetActivity();

    if (result == 1){
%>
        <h1>Asset Activity Successfully Deleted!</h1>
<%
        } else {
%>
        <h1>Asset Activity Deletion Failed!</h1>
<%
    }
%>
</body>
</html>
