<%--
  Created by IntelliJ IDEA.
  User: Kyle Carlo C. Lasala
  Date: 16/04/2023
  Time: 10:24 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete Asset Activity</title>
    <link rel="stylesheet" type="text/css" href="styles/success_style.css"/>
</head>
<body>
    <jsp:useBean id="Activity" class="actsmgt.AssetAct" scope="session"/>
    <%
        String activity = request.getParameter("asset_id");
        String[] sep = activity.split(",", 2);
        int asset_id = Integer.valueOf(sep[0]);
        String activity_date = sep[1];
        String actual_start = request.getParameter("actual_start");
        String actual_end = request.getParameter("actual_end");
        String cost = request.getParameter("cost");
        if (cost == ""){
            Activity.cost = -1.0;
        } else {
            Activity.cost = Double.valueOf(cost);
        }
        Activity.asset_id = asset_id;
        Activity.activity_date = activity_date;
        Activity.act_start = actual_start;
        Activity.act_end = actual_end;
        int result = Activity.completeAssetActivity();

        if (result == 1){
    %>
            <H1>Activity Successfully Completed</H1>
    <%
        } else {
    %>
            <H1>Activity Completion Failed</H1>
    <%
        }
    %>
    <form action="index.html">
        <input type="submit" value="Return to Menu">
    </form>
</body>
</html>
